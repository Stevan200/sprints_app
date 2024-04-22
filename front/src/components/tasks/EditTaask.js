import React, { useState, useEffect, useCallback } from 'react';
import CinemaAxios from './../../apis/CinemaAxios';
import { Col, Row, Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const EditTask = (props) => {

    const [states, setStates] = useState([])
    const [sprints, setSprints] = useState([])

    useEffect(() => {
        getState()
        getSprint()
    }, [])

    const getState = useCallback(() => {
        CinemaAxios.get("/stanja")
        .then(res => {
            console.log(res.data)
            setStates(res.data)
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
            setSprints(res.data)
        })
        .catch(error => {
            console.log(error)
            alert('Error while fetching movies')
        })
    }, [])

    const stateSelectionChanged = (e) => {
        let stateId = e.target.value;
        let state = states.find((state) => state.id == stateId);

        setTask(task => {
            return {
                ...task,
                taskStateId: state.id,
                stateName: state ? state.ime : null,

            };
        });
        //isInputValid();
    }

    const sprintSelectionChanged = (e) => {
        let sprintId = e.target.value;
        let sprint = sprints.find((sprint) => sprint.id == sprintId);

        setTask(task => {
            return {
                ...task,
                taskSprintId: sprint.id,
                sprintName: sprint ? sprint.ime : null,

            };
        });
        //isInputValid();
    }

    const [task, setTask] = useState({
        taskId: props.selectedTask.id,
        taskName: props.selectedTask.ime,
        taskEmployee: props.selectedTask.zaduzeni,
        taskPoints: props.selectedTask.bodovi,

        taskStateId: props.selectedTask.stanjeId,
        taskStateName: props.selectedTask.stanjeIme,

        taskSprintId: props.selectedTask.sprintId,
        taskSprintName: props.selectedTask.sprintIme

    })

    const navigate = useNavigate();

    const onInputChange = event => {
        const { name, value } = event.target;

        setTask((task) => {
            var new_task = { ...task }
            new_task[name] = value
            return new_task
        });
    }

    const edit = () => {
        const params = {
            'id': task.taskId,
            'ime': task.taskName,
            'zaduzeni': task.taskEmployee,
            'bodovi': task.taskPoints,

            'stanjeId': task.taskStateId,
            'stanjeIme': task.taskStateName,

            'sprintId': task.taskSprintId,
            'sprintIme': task.taskSprintName

        };

        console.log("state details:")
        console.log(JSON.stringify(params, null, 2))

        CinemaAxios.put('/zadaci/' + task.taskId, params)
            .then(res => {
                // handle success
                console.log(res);
                alert('Task was edited successfully!');
                navigate('/tasks');
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    const renderState = () => {
        return states.map((state) => <option key={state.id} value={state.id}>{state.ime}</option>)
    }

    const renderSprint = () => {
        return sprints.map((sprint) => <option key={sprint.id} value={sprint.id}>{sprint.ime}</option>)
    }

    return (
        <Col>
            <Row><h1>Edit Task</h1></Row>
            <Row>
                <Form>
                    <Form.Group>
                        <Form.Label htmlFor="name">Ime</Form.Label>
                        <Form.Control name="taskName" type="text" value={task.taskName} onChange={(e) => onInputChange(e)} />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label htmlFor="employee">Zaduzeni </Form.Label>
                        <Form.Control name="taskEmployee" type="text" value={task.taskEmployee} onChange={(e) => onInputChange(e)} />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label htmlFor="points">Bodovi </Form.Label>
                        <Form.Control name="taskPoints" type="number" value={task.taskPoints} onChange={(e) => onInputChange(e)} />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Stanje</Form.Label>
                        <Form.Control id="tTaskStateName" as="select" name="taskStateName" value={task.taskStateId} onChange={(e) => stateSelectionChanged(e)}>
                            <option></option>
                            {renderState()}
                        </Form.Control><br />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Sprint</Form.Label>
                        <Form.Control id="tTaskSprintName" as="select" name="taskSprintName" value={task.taskSprintId} onChange={(e) => sprintSelectionChanged(e)}>
                            <option></option>
                            {renderSprint()}
                        </Form.Control><br />
                    </Form.Group>
                </Form>
            </Row>
            <Button className="button button-navy" onClick={() => edit()}>Edit</Button>
        </Col>
    );

}

export default EditTask;