import React, { useEffect, useState } from "react";
import {Button, Col, Form, Row, Table} from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import CinemaAxios from "../../apis/CinemaAxios";
import TaskRow from "./TaskRow";

const Tasks = (props) => {

  const empty_search = {
    taskName: "",
    sprintId: ""
}

    const [totalPages, setTotalPages] = useState(0)
    const [pageNo, setPageNo] = useState(0)
    const [tasks, setTasks] = useState([])
    const [search, setSearch] = useState(empty_search)
    const [sprint, setSprint] = useState([])
    const [state, setState] = useState([])
    const [showSearch, setShowSearch] = useState(false)
    const navigate = useNavigate()

    useEffect(()=>{
        getTasks(0)
        getSprint()
        getState()
      },[])

      const getTasks = (newPageNo) => {    
        let config = {
          params : {
                pageNo: newPageNo,
                ime: search.taskName,
                sprintId: search.sprintId
          }
        }
          CinemaAxios.get("/zadaci", config)
          .then(result => {
            setPageNo(newPageNo)
            setTotalPages(result.headers['total-pages'])
            setTasks(result.data)
          })
          .catch(error => {
            console.log(error)
            alert("Error while fetching lines")
          })
      }

      const getSprint = () => {
        CinemaAxios.get("/sprintovi")
        .then((resp) => {
            setSprint(resp.data)
        })
        .catch((err=>{console.log(err)}))
    }

    const getState = () => {
        CinemaAxios.get("/stanja")
        .then((resp) => {
            setState(resp.data)
        })
        .catch((err=>{console.log(err)}))
    }

    const renderTasks = () => {
      return tasks.map((task) => {
        return <TaskRow key={task.id} task={task} editCallback={props.callback} changeStateCallback={handleChangeState}></TaskRow>
      });
    }

  const onInputChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;

    setSearch({
        ...search,
        [name]: value,
    });
};

const isAdmin = localStorage.getItem('role') === 'ROLE_ADMIN'; // Provera da li je korisnik admin

const handleChangeState = (taskId) => {
  CinemaAxios.post(`/zadaci/${taskId}/change_state`)
    .then((ret) => {
      const updatedTasks = tasks.map(task => {
        if (task.id === taskId) {
          return ret.data; // Assuming ret.data is the updated task
        }
        return task;
      });
      setTasks(updatedTasks); // Update the tasks state in the parent component
    }).catch(() => {
      alert("Nije moguće promeniti stanje.");
    });
}

  const goToAdd = () => {
    navigate("/tasks/add");
  }

  const renderSearchForm = () => {
    return (
        <>
        <Form style={{ width: "99%" }}>
            <Row><Col>
                <Form.Group>
                    <Form.Label>Ime zadatka</Form.Label>
                    <Form.Control
                        name="taskName"
                        as="input"
                        type="text"
                        onChange={(e) => onInputChange(e)}></Form.Control>
                </Form.Group>
            </Col></Row>

            <Row>
                <Col>
                <Form.Group>
                        <Form.Label>Sprint</Form.Label>
                        <Form.Control as="select" name="sprintId" onChange={(e)=>onInputChange(e)}>
                            <option value=""></option>
                            {sprint.map((sprint)=>{
                                return(
                                    <option key={sprint.id} value={sprint.id}>{sprint.ime}</option>
                                );
                            })}
                        </Form.Control>
                    </Form.Group>
                </Col>
            </Row>
        </Form>
        <Row><Col>
            <Button className="mt-3" onClick={() => getTasks()}>Search</Button>
        </Col></Row>
        </>
    );
}

    return (
        <div>
          <h1>Tasks</h1>
  
          <div>
            <div>
                <label>
                <input type="checkbox" onChange={()=>setShowSearch(!showSearch)}/>
                    Show Search
                </label>
            </div>
            <Row hidden={!showSearch} >
                {renderSearchForm()}
            </Row>
            <br/>
            <div >
              {
                isAdmin && (
                  <Button onClick={() => goToAdd()}> ADD </Button>
                )
              }
            <Button style={{ float: 'right' }} disabled={pageNo==totalPages-1} onClick={()=>getTasks(pageNo+1)}>Next</Button>
            <Button style={{ float: 'right' }} disabled={pageNo===0} 
                    onClick={()=>getTasks(pageNo-1)}
                    className="mr-3">Prev</Button>
            </div>
            <br/>
            <Table id="tasks-table">
              <thead>
                <tr>
                  <th>Ime </th>
                  <th>Zaduzeni</th>
                  <th>Bodovi  </th>
                  <th>Stanje </th>
                  <th>Sprint </th>
                </tr>
              </thead>
              <tbody>
                  {renderTasks()}
              </tbody>
            </Table>
          </div>
        </div>
      );
}

export default Tasks;
