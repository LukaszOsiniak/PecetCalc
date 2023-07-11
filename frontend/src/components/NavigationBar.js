import React from 'react';

import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faFileInvoice,
    faHouse,
    faPlus,
    faComputer
} from "@fortawesome/free-solid-svg-icons";

class NavigationBar extends React.Component {
       render(){
       return (
       <Navbar bg="dark" data-bs-theme="dark">
            <Link to={""} className="navbar-brand">
            </Link>
                 <Nav className="mr-auto">
                   <Link to="" className="nav-link">  <FontAwesomeIcon icon={faHouse} /> Home </Link>
                   <Link to="add" className="nav-link">  <FontAwesomeIcon icon={faPlus} /> Add Computer </Link>
                   <Link to="computers" className="nav-link">  <FontAwesomeIcon icon={faComputer} /> Computer List </Link>
                   <Link to="create" className="nav-link"> <FontAwesomeIcon icon={faPlus} /> Add Invoice </Link>
                   <Link to="invoices" className="nav-link">  <FontAwesomeIcon icon={faFileInvoice} /> Invoice List </Link>
                 </Nav>
       </Navbar>
       );
       }
}

export default NavigationBar;