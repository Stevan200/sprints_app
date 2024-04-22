import React, { useState, useEffect, useCallback } from 'react';
import { Row, Col, Form, Button } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import CinemaAxios from '../../apis/CinemaAxios';

const AddTask = () => {

    var task = {
        name: '',
        employee: '',
        points: 0,
        state: null,
        sprint: null,
    }

    const [newTask, setNewTask] = useState(task);
    const [state, setState] = useState([])
    const [sprint, setSprint] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        getState()
        getSprint()
    }, [])

    const getState = useCallback(() => {
        CinemaAxios.get("/stanja")
        .then(res => {
            console.log(res.data)
            setState(res.data)
        })
        .catch(error => {
            console.log(error)
            alert('Error while fetching movies')
        })
    }, [])

    const getSprint = useCallback(() => {
        CinemaAxios.get("/sprintovi")
        .then(res => {
            console.log(res.data)
            setSprint(res.data)
        })
        .catch(error => {
            console.log(error)
            alert('Error while fetching movies')
        })
    }, [])

    const create = () => {
        var params = {
            ime: newTask.name,
            zaduzeni: newTask.employee,
            bodovi: newTask.points,
            stanjeId: newTask.state,
            sprintId: newTask.sprint
        };
    
        CinemaAxios.post("/zadaci", params)
          .then((res) => {
            // handle success
            console.log(res);
            alert("Task was added successfully!");
            navigate("/tasks");
          })
          .catch((error) => {
            // handle error
            console.log(error);
            alert("Error occured please try again!");
          });
      }

      const onNameChange = (event) => {
        const value = event.target.value;
        setNewTask({...newTask, name: value});
    };

    const onEmployeeChange = (event) => {
        const value = event.target.value;
        setNewTask({...newTask, employee: value});
    };

    const onPointsChange = (event) => {
        const value = event.target.value;
        setNewTask({...newTask, points: value});
    };

    const onStateChange = (event) => {
        const value = event.target.value;
        setNewTask({...newTask, state: value});
    };

    const onSprintChange = (event) => {
        const value = event.target.value;
        setNewTask({...newTask, sprint: value});
    };

    const renderState = () => {
        return state.map((state)=><option key={state.id} value={state.id}>{state.ime}</option>)
    }

    const renderSprint = () => {
        return sprint.map((sprint)=><option key={sprint.id} value={sprint.id}>{sprint.ime}</option>)
    }

    return (
        <>
            <Row>
                <Col></Col>
                <Col xs={12} sm={10} md={8}>
                    <h1>Add Task</h1>
                    <Form>
                        <Form.Group>
                            <Form.Label>Ime  </Form.Label>
                            <Form.Control id="sName" name="name" onChange={(e) => onNameChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Zaduzeni </Form.Label>
                            <Form.Control  id="sEmployee" name="employee" onChange={(e) => onEmployeeChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Bodovi </Form.Label>
                            <Form.Control type="number" step=".01" id="sPoints" name="points" onChange={(e) => onPointsChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Stanje</Form.Label>
                            <Form.Control id="sState" as="select" name="state" onChange={(e) => onStateChange(e)}>
                                <option></option>
                                {renderState()}
                            </Form.Control><br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Sprint</Form.Label>
                            <Form.Control id="sSprint" as="select" name="sprint" onChange={(e) => onSprintChange(e)}>
                                <option></option>
                                {renderSprint()}
                            </Form.Control><br />
                        </Form.Group>
                        <Button style={{ marginTop: "25px" }} onClick={create}>Add</Button>
                    </Form>
                </Col>
                <Col></Col>
            </Row>
        </>
    )

}

export default AddTask

