import React, {Component} from 'react';

import {Card, Button, Form} from 'react-bootstrap';

import axios from 'axios';
import { useNavigate } from "react-router-dom";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faPlus,
} from "@fortawesome/free-solid-svg-icons";


 class Computer extends Component {

        constructor(props){
            super(props);
            this.state = this.initialState;
            this.computerChange = this.computerChange.bind(this);
            this.submitComputer = this.submitComputer.bind(this);

        }

       initialState= {
            cpuId: '', name: '', priceInUSD: '', priceInPLN: '', exchangeRate: '', accDate:""
       }

       resetComputer = () => {
            this.setState(()=> this.initialState);
       };

        updateComputer = event => {
            event.preventDefault();

            const computer = {
               cpuId: this.state.cpuId,
               name: this.state.name,
                priceInUSD: this.state.priceInUSD,
                priceInPLN:this.state.priceInPLN,
                exchangeRate: this.state.exchangeRate,
                accDate: this.state.accDate
            };

            axios.put('http://localhost:8080/computers', computer)
            .then(response => {
                this.setState({"show": true});
            });
            this.setState(this.initialState);
        }

       submitComputer = event => {
           event.preventDefault();

           const computer = {
                cpuId: this.state.cpuId,
                name: this.state.name,
                priceInUSD: this.state.priceInUSD,
                priceInPLN: this.state.priceInPLN,
                exchangeRate: this.state.exchangeRate,
                accDate: this.state.accDate,
           };

           axios.post("http://localhost:8080/computers", computer).then((response) => {
                   this.setState(this.initialState);
                   alert("Computer Saved Successfully");

           });
       };

       computerChange = event => {
            this.setState({
                [event.target.name]:event.target.value
            });
       }

       render(){
            const{cpuId, name, priceInUSD, priceInPLN, exchangeRate, invoice, accDate} = this.state;

       return (
               <Card className={"border border-dark bg-dark text-white"}>
                  <Card.Header> <FontAwesomeIcon icon={faPlus} /> Add Computer </Card.Header>
                  <Card.Body>
                        <Form onReset={this.resetComputer} onSubmit={this.submitComputer} id="computerFormId">
                           <Form.Group controlId="formGridCpuId">
                             <Form.Label>Number</Form.Label>
                             <Form.Control required autoComplete="off"
                                 type="id"
                                 name='cpuId'
                                 value={cpuId}
                                 onChange={this.computerChange}
                                 className={"bg-dark text-white"}
                                 placeholder="cpuId" />
                           </Form.Group>
                          <Form.Group controlId="formGridName">
                            <Form.Label>Name</Form.Label>
                            <Form.Control required autoComplete="off"
                                type="text"
                                name='name'
                                value={name}
                                onChange={this.computerChange}
                                className={"bg-dark text-white"}
                                placeholder="Enter name" />
                          </Form.Group>
                          <Form.Group controlId="formGridPriceInUSD">
                            <Form.Label>Price In USD</Form.Label>
                            <Form.Control required autoComplete="off"
                                type="text"
                                name='priceInUSD'
                                value={priceInUSD}
                                onChange={this.computerChange}
                                className={"bg-dark text-white"}
                                placeholder="priceInUSD" />
                          </Form.Group>
                          <Form.Group controlId="priceInPLN">
                             <Form.Label>Price In PLN</Form.Label>
                             <Form.Control required autoComplete="off"
                                 type="text"
                                 name='priceInPLN'
                                 value={priceInPLN}
                                 onChange={this.computerChange}
                                 className={"bg-dark text-white"}
                                 placeholder="priceInPLN" />
                           </Form.Group>
                           <Form.Group controlId="formGridExchangeRate">
                              <Form.Label>Exchange Rate</Form.Label>
                              <Form.Control required autoComplete="off"
                                  type="number"
                                  name='exchangeRate'
                                  value={exchangeRate}
                                  onChange={this.computerChange}
                                  className={"bg-dark text-white"}
                                  placeholder="exchangeRate" />
                            </Form.Group>
                            <Form.Group controlId="formGridExchangeRate">
                            <Form.Label>Accounting Date</Form.Label>
                            <Form.Control required autoComplete="off"
                                type="text"
                                name='accDate'
                                value={accDate}
                                onChange={this.computerChange}
                                className={"bg-dark text-white"}
                                placeholder="accDate" />
                          </Form.Group>
                          <Button size="sm" variant="success" type="submit">
                            Submit
                          </Button> {' '}
                          <Button size="sm" variant="info" type="reset">
                            Reset
                          </Button>
                        </Form>
                  </Card.Body>
               </Card>
       );
       }
}

export default Computer;
