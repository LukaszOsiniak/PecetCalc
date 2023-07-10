import React, {Component} from 'react';

import { Card, Table, ButtonGroup, Button, InputGroup, FormControl} from 'react-bootstrap';
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';
import dayjs from "dayjs";

export default class InvoiceList extends Component {

        constructor(props){
            super(props);
            this.state = {
                invoices:[],
                search:'',
                currentPage:1,
                recordPerPage:3,
            };
        }

        componentDidMount(){
             this.getInvoicesByPagination(this.state.currentPage);
        }

        findAllInvoices(){
           axios.get('http://localhost:8080/invoices')
               .then(response  => response.data)
               .then((data) => {
                     this.setState({invoices: data});
               });
        }

         getInvoicesByPagination(currentPage){
             currentPage=currentPage-1;
             axios.get('http://localhost:8080/invoices')
             .then(response => response.data).then((data) =>{
                  this.setState({invoices:data.content})
                })};

        searchBox= (e) => {
            this.setState({
                [e.target.name]: e.target.value,
            });
           };

        searchInvoice=(currentPage)=> {
            currentPage=currentPage-1;
            axios.get('http://localhost:8080/invoices/search/'+this.state.search)
            .then(response => response.data).then((data) => {
                 this.setState({invoices:data.content});
               });
        };

        updateInvoice = event => {
             event.preventDefault();
             const invoice = {
             }
        }

        resetInvoice = (currentPage) => {
            this.setState({"search":''});
            this.getInvoicesByPagination(this.state.currentPage);
        }

        deleteInvoice = (invId) => {
            axios.delete("http://localhost:8080/invoices/" + invId)
            .then((response) => {
                    alert("Invoice Deleted Successfully");
                    this.setState({
                        invoices: this.state.invoices.filter(invoices => invoices.invId !== invId)
                    });
                }, (error) => {
                    alert("Operation Failed");
                }
            );
        };

       render(){
                const {invoices, currentPage, totalPages,recordPerPage, search} = this.state;

             return (
               <Card className={"border border-dark bg-dark text-white"}>
                  <Card.Header> Invoices List </Card.Header>
                  <Card.Body>
                  <div className="form-group mb-2">
                      <input type="text" className="form-control" name="search" size="50"  placeholder="Search Invoices Names Here" value={search}  onChange={this.searchBox}/>
                      <button type="button" name="search" className="btn btn-info my-2 text-center mr-2" onClick={this.searchInvoice}>Search</button>
                      <button type="reset" className="btn btn-secondary text-center ml-5" style={{marginLeft:'10px'}} onClick={this.resetInvoice}>Clear</button>
                  </div>
                     <Table sorted={this.state.sortOptions} bordered hover striped variant = "dark">
                         <thead>
                                <tr>
                                  <th>Number</th>
                                  <th>Name</th>
                                  <th>Computers</th>
                                  <th>Exchange Rate</th>
                                  <th>Price In PLN</th>
                                  <th>Price In USD</th>
                                  <th>Invoice Date</th>
                                </tr>
                              </thead>
                              <tbody>
                               {
                                    this.state.invoices.length === 0 ?
                                    <tr align ="center">
                                        <td colSpan="6"> No Invoices Available.</td>
                                    </tr> :
                                    this.state.invoices.map((invoice) => (
                                    <tr key ={invoice.invId}>
                                        <td>{invoice.invId} </td>
                                        <td>{invoice.name} </td>
                                        <td>
                                        {invoice.computers.map(computer => {
                                        return (
                                        <div key={computer.cpuId}>
                                            <p>{computer.name}</p>
                                         </div>
                                            )})}
                                         </td>
                                        <td>{invoice.exchangeRate === null ? invoice.exchangeRate : invoice.exchangeRate.toFixed(2)} </td>
                                        <td>{invoice.invPriceInPln === null ? invoice.invPriceInPln : invoice.invPriceInPln.toFixed(2)} </td>
                                        <td>{invoice.invPriceInUsd === null ? invoice.invPriceInUsd : invoice.invPriceInUsd.toFixed(2)} </td>
                                        <td>{dayjs(invoice.invDate).format("MM/DD/YYYY")} </td>
                                        <td>
                                            <ButtonGroup>
                                                <Link to={`/invoices/${invoice.invId}`} className="btn btn-sm btn-outline-primary">Edit</Link>
                                                <button className="btn btn-outline-danger" onClick={() => { this.deleteInvoice(invoice.invId) }}>Delete</button> {''}
                                            </ButtonGroup>
                                         </td>
                                     </tr>
                                    ))
                                }
                              </tbody>
                          </Table>
                     </Card.Body>
                  </Card>
             );
             }
}
