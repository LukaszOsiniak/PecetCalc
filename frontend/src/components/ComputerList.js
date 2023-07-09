import React, {Component} from 'react';

import {  Card, Table, ButtonGroup, Button, InputGroup, FormControl} from 'react-bootstrap';
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';

export default class ComputerList extends Component {

        constructor(props){
            super(props);
            this.state = {
                computers:[],
                search:'',
                currentPage:1,
                recordPerPage:3,
            };
        }


        componentDidMount(){
             this.getComputersByPagination(this.state.currentPage);
        }

        findAllComputers(){
           axios.get('http://localhost:8080/computers')
               .then(response  => response.data)
               .then((data) => {
                     this.setState({computers: data});
               });
        }

         getComputersByPagination(currentPage){
             currentPage=currentPage-1;
             axios.get('http://localhost:8080/computers')
             .then(response => response.data).then((data) =>{
                  this.setState({computers:data.content
                 })
                })};

        searchBox= (e) => {
            this.setState({
                [e.target.name]: e.target.value,
            });
           };

        searchComputer=(currentPage)=> {
            currentPage=currentPage-1;
            axios.get('http://localhost:8080/computers/'+this.state.search)
            .then(response => response.data).then((data) => {
                 this.setState({computers:data.content});
               });
        };

        updateComputer = event => {
             event.preventDefault();
             const computer = {
             }
        }

        resetComputer = (currentPage)=>{
            this.setState({"search":''});
            this.getComputersByPagination(this.state.currentPage);
            }

        deleteComputer = (cpuId) => {
            axios.delete("http://localhost:8080/computers/" + cpuId)
            .then((response) => {
                    alert("Record Deleted Successfully");
                    this.setState({
                        computers: this.state.computers.filter(computer => computer.cpuId !== cpuId)
                    });
                }, (error) => {
                    alert("Operation Failed Here");
                }
            );
        };

       render(){
                const {computers, currentPage, totalPages,recordPerPage,search} = this.state;

             return (
               <Card className={"border border-dark bg-dark text-white"}>
                  <Card.Header> Computer List </Card.Header>
                  <Card.Body>
                  <div className="form-group mb-2">
                      <input type="text" className="form-control" name="search" size="50"  placeholder="Search Computer Names Here" value={search}  onChange={this.searchBox}/>
                      <button type="button" name="search" className="btn btn-info my-2 text-center mr-2" onClick={this.searchComputer}>Search</button>
                      <button type="reset" className="btn btn-secondary text-center ml-5" style={{marginLeft:'10px'}} onClick={this.resetComputer}>Clear</button>
                  </div>
                     <Table bordered hover striped variant = "dark">
                         <thead>
                                <tr>
                                  <th>Number</th>
                                  <th>Name</th>
                                  <th>Price In USD</th>
                                  <th>Price In PLN</th>
                                  <th>Exchange Rate</th>
                                  <th>Invoice</th>
                                </tr>
                         </thead>
                         <tbody>
                          {    this.state.computers.length === 0 ?
                               <tr align ="center">
                                   <td colSpan="6"> No Computers Available.</td>
                               </tr> :
                               this.state.computers.map((computer) => (
                               <tr key ={computer.cpuId}>
                                   <td>{computer.cpuId} </td>
                                   <td>{computer.name} </td>
                                   <td>{computer.priceInUSD} </td>
                                   <td>{computer.priceInPLN} </td>
                                   <td>{computer.exchangeRate} </td>
                                   <td>{computer.invoice} </td>
                                   <td>
                                       <ButtonGroup>
                                           <Link to={"" + computer.cpuId} className="btn btn-sm btn-outline-primary"> Edit </Link>{" "}
                                           <Link to={`/computers/${computer.cpuId}`} className="btn btn-outline-dark">Edit</Link>
                                           <button className="btn btn-outline-danger" onClick={() => { this.deleteComputer(computer.cpuId) }}>Delete</button> {''}
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
