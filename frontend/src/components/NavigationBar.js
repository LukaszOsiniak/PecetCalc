import React from 'react';

import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom'

class NavigationBar extends React.Component {
       render(){
       return (
       <Navbar bg="dark" data-bs-theme="dark">
            <Link to={""} className="navbar-brand">
            </Link>
                 <Nav className="mr-auto">
                   <Link to="" className="nav-link"> Home </Link>
                   <Link to="add" className="nav-link"> Add Computer </Link>
                   <Link to="computers" className="nav-link"> Computer List </Link>
                   <Link to="create" className="nav-link"> Add Invoice </Link>
                   <Link to="invoices" className="nav-link"> Invoice List </Link>
                 </Nav>
       </Navbar>
       );
       }
}

export default NavigationBar;