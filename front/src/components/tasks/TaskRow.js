import React, { useState } from "react";
import {Button} from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import CinemaAxios from "../../apis/CinemaAxios";

const TaskRow = (props) => {

  const [tasks, setTasks] = useState([])

  var navigate = useNavigate()

    const goToEdit = (task) => {
      console.log(props);
      props.editCallback(task);
      navigate('/tasks/edit');
    }

    const deleteTask = (taskId) => {
        CinemaAxios.delete('/zadaci/' + taskId)
        .then(res => {
            // handle success
            console.log(res);
            alert('Task was deleted successfully!');
            window.location.reload();
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    const changeStateHandler = () => {
      props.changeStateCallback(props.task.id);
    }

  const isAdmin = localStorage.getItem('role') === 'ROLE_ADMIN'; // Provera da li je korisnik admin



    return (
        <tr key={props.task.id} >
          <td>{props.task.ime}</td>
          <td>{props.task.zaduzeni}</td>
          <td>{props.task.bodovi}</td>
          <td>{props.task.stanjeIme}</td>
          <td>{props.task.sprintIme}</td>
          <td>
              {isAdmin && (<Button disabled={props.task.stateId === 3} variant="info" onClick={changeStateHandler}>Next stanje</Button>)}
          </td>
          <td>
              {isAdmin && (
                <Button variant="warning" onClick={() => goToEdit(props.task)}>Izmeni</Button>
              )}
          </td>
          <td>
            {isAdmin && ( // Prikazujemo dugme samo ako je korisnik admin
                      <Button variant="danger" onClick={() => deleteTask(props.patient.id)}>Obrisi</Button>
                  )}
            </td>
        </tr>
      )

}

export default TaskRow;
