import React from 'react';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'

import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Computer from './components/Computer';
import ComputerList from './components/ComputerList';

function App() {
    const marginTop = {
    marginTop: "20px"
    }
  return (

    <Router>
        <NavigationBar/>
        <Container>
          <Row>
                <Col lg={12} style={marginTop}>
                    <Routes>
                        <Route path= "" exact element={<Welcome/>}/>
                        <Route path= "/add" exact element={<Computer/>}/>
                        <Route path= "/computers" exact element={<ComputerList/>}/>
                    </Routes>
                </Col>
          </Row>
        </Container>
    </Router>
  );
}

export default App;
