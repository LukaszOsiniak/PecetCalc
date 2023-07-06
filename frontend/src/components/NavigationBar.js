import React from 'react';

import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom'

class NavigationBar extends React.Component {
       render(){
       return (
       <Navbar bg="dark" data-bs-theme="dark">
            <Link to={""} className="navbar-brand">
                    Ooo
            </Link>
                 <Nav className="mr-auto">
                   <Link to="n[pm" className="nav-link"> Home </Link>
                   <Link to="add" className="nav-link"> Add Computer </Link>
                   <Link to="computers" className="nav-link"> Computer List </Link>
                 </Nav>
       </Navbar>
       );
       }
}

export default NavigationBar;