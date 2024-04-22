import React, { useState } from 'react';
import { createRoot } from 'react-dom/client';
import { Route, Link, BrowserRouter as Router, Routes, Navigate } from 'react-router-dom';
import { Navbar, Nav, Button, Container} from 'react-bootstrap';
import Home from './components/Home';
import Login from './components/authorization/Login'
import NotFound from './components/NotFound';
import {logout} from './services/auth';
import Tasks from './components/tasks/tasks';
import AddTask from './components/tasks/AddTask';
import EditTask from './components/tasks/EditTaask';

const App = () => {

    const [selectedTasks, setSelectedTasks] = useState({})

    const jwt = window.localStorage['jwt'];

    if(jwt){
        return (
        <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                    <Nav.Link as={Link} to="/tasks">
                        TASKS
                    </Nav.Link>
                    <Button onClick={()=>logout()}>Logout</Button>:
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                <Routes>
                    <Route path="/" element={<Home/>} />
                    <Route path="/login" element={<Navigate replace to='/'/>} />
                    <Route path="/tasks" element={<Tasks callback={(task)=>setSelectedTasks(task)}/>} />
                    <Route path="/tasks/add" element={<AddTask/>} />
                    <Route path="/tasks/edit/" element={<EditTask selectedTask={selectedTasks}/>} />
                    <Route path="*" element={<NotFound/>} />
                </Routes>
            </Container>
            </Router>
        </>
    );
    }else{
        return( 
        <>
            <Router>
            <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                    <Nav.Link as={Link} to="/tasks">
                        TASKS
                    </Nav.Link>
                    <Nav.Link as={Link} to="/login">
                        Login
                    </Nav.Link>
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                <Routes>
                    <Route path="/" element={<Home/>} />
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/tasks" element={<Tasks/>} />
                    <Route path="*" element={<Navigate replace to = "/login"/>}/>
                </Routes>
                </Container>
            </Router>
        </>);
    }
}

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App/>,
)