import React, {Component} from 'react';

import { Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';

export default class ComputerList extends Component {

        constructor(props){
            super(props);
            this.state = {
                computers:[]
            };
        }

        componentDidMount(){
             this.findAllComputers();
        }

        findAllComputers(){
                    axios.get("http://localhost:8080/computers")
                        .then(response  => response.data)
                        .then((data) => {
                              this.setState({computers: data});
                        });
        }

       render(){
             return (
               <Card className={"border border-dark bg-dark text-white"}>
                  <Card.Header> Computer List </Card.Header>
                  <Card.Body>
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
                               {
                                    this.state.computers.length === 0 ?
                                    <tr align ="center">
                                        <td colSpan="6"> No Computers Avaliable.</td>
                                    </tr> :
                                    this.state.computers.map((computer) => (
                                    <tr key ={computer.id}>
                                        <td>{computer.cpuId} </td>
                                        <td>{computer.name} </td>
                                        <td>{computer.priceInUSD} </td>
                                        <td>{computer.priceInPLN} </td>
                                        <td>{computer.exchangeRate} </td>
                                        <td>{computer.invoice} </td>
                                        <td>
                                            <ButtonGroup>
                                                <Button size="sm" variant="outline-primary"> Edit </Button> {''}
                                                <Button size="sm" variant="outline-danger"> Delete </Button> {''}
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
