import React, {Component} from 'react';

import {Card, Button, Form} from 'react-bootstrap';

import axios from 'axios';
import { useNavigate } from "react-router-dom";

 class Invoice extends Component {

        constructor(props){
            super(props);
            this.state = this.initialState;
            this.invoiceChange = this.invoiceChange.bind(this);
            this.submitInvoice = this.submitInvoice.bind(this);
        }

       initialState= {
            invId: '', name: '', computers: '', exchangeRate: '', invPriceInPln: '', invPriceInUsd: '', invDate:'',
       }

       resetInvoice = () => {
            this.setState(()=> this.initialState);
       };

        updateInvoice = event => {
            event.preventDefault();

            const invoice = {
               invId: this.state.invId,
               name: this.state.name,
               computers: this.state.computers,
               exchangeRate:this.state.exchangeRate,
               invPriceInPln: this.state.invPriceInPln,
               invPriceInUsd: this.state.invPriceInUsd,
               invDate: this.state.invDate
            };

            axios.put('http://localhost:8080/invoices', invoice)
            .then(response => {
                this.setState({"show": true});
            });
            this.setState(this.initialState);
        }

       submitInvoice = event => {
           event.preventDefault();

           const invoice = {
               invId: this.state.invId,
               name: this.state.name,
               computers: this.state.computers,
               exchangeRate:this.state.exchangeRate,
               invPriceInPln: this.state.invPriceInPln,
               invPriceInUsd: this.state.invPriceInUsd,
               invDate: this.state.invDate
           };

           axios.post("http://localhost:8080/invoices", invoice).then((response) => {
                   this.setState(this.initialState);
                   alert("Invoice Saved Successfully");
           });
       };

       invoiceChange = event => {
            this.setState({
                [event.target.name]:event.target.value
            });
       }

       render(){
            const{invId, name, computers, exchangeRate, invPriceInPln, invPriceInUsd, invDate} = this.state;

       return (
               <Card className={"border border-dark bg-dark text-white"}>
                  <Card.Header> Add Invoice </Card.Header>
                  <Card.Body>
                        <Form onReset={this.resetInvoice} onSubmit={this.submitInvoice} id="computerFormId">
                           <Form.Group controlId="formGridCpuId">
                             <Form.Label>Number</Form.Label>
                             <Form.Control required autoComplete="off"
                                 type="id"
                                 name='invId'
                                 value={invId}
                                 onChange={this.invoiceChange}
                                 className={"bg-dark text-white"}
                                 placeholder="cpuId" />
                           </Form.Group>
                          <Form.Group controlId="formGridName">
                            <Form.Label>Name</Form.Label>
                            <Form.Control required autoComplete="off"
                                type="text"
                                name='name'
                                value={name}
                                onChange={this.invoiceChange}
                                className={"bg-dark text-white"}
                                placeholder="Enter name" />
                          </Form.Group>
                          <Form.Group controlId="formGridPriceInPLN">
                            <Form.Label>Price In USD</Form.Label>
                            <Form.Control required autoComplete="off"
                                type="text"
                                name='invPriceInPln'
                                value={invPriceInPln}
                                onChange={this.invoiceChange}
                                className={"bg-dark text-white"}
                                placeholder="Enter price in PLN" />
                          </Form.Group>
                          <Form.Group controlId="invPriceInUsd">
                             <Form.Label>Price In USD</Form.Label>
                             <Form.Control required autoComplete="off"
                                 type="text"
                                 name='invPriceInUsd'
                                 value={invPriceInUsd}
                                 onChange={this.invoiceChange}
                                 className={"bg-dark text-white"}
                                 placeholder="Enter price in USD" />
                           </Form.Group>
                           <Form.Group controlId="formGridExchangeRate">
                              <Form.Label>Exchange Rate</Form.Label>
                              <Form.Control required autoComplete="off"
                                  type="number"
                                  name='exchangeRate'
                                  value={exchangeRate}
                                  onChange={this.invoiceChange}
                                  className={"bg-dark text-white"}
                                  placeholder="exchangeRate" />
                            </Form.Group>
                            <Form.Group controlId="formGridExchangeRate">
                               <Form.Label>Exchange Rate</Form.Label>
                               <Form.Control required autoComplete="off"
                                   type="number"
                                   name='invDate'
                                   value={invDate}
                                   onChange={this.invoiceChange}
                                   className={"bg-dark text-white"}
                                   placeholder="Date of exchange" />
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

export default Invoice;
