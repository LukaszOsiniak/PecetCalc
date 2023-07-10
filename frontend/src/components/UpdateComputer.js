import React,{useState,useEffect} from 'react'
import axios from 'axios';
import {useParams} from "react-router-dom";
function UpdateComputer() {
    const {id} = useParams();

    useEffect(()=>{
        getComputerById();
    },[])
    const [computer, setComputer] = useState({
         cpuId: '', name: '', priceInUSD: '', priceInPLN: '', exchangeRate: '', accDate:''
    });
    const {cpuId, name, priceInUSD, priceInPLN, exchangeRate, accDate } = computer;
    const onInputChange = e =>{
        setComputer({...computer,[e.target.name]:e.target.value})
    }

    const FormHandle = e =>{
        e.preventDefault();
        updateDataToServer(computer)
    }
    const updateDataToServer=(data) =>{
        axios.put("http://localhost:8080/computers/"+ id,data).then(
           (response)=>{
                   alert("Computer Updated Successfully");
            },(error)=>{
                    alert("Operation failed");
            }
        );
    };

    const getComputerById= async e =>{
        const computersInfo = await axios.get("http://localhost:8080/computers/"+id);
        setComputer(computersInfo.data);
    }

    return (
        <div>
            <div className="container">
            <div className="border border-dark bg-dark text-white">
                <div class="jumbotron">
                    <h4 >Update Computer</h4>
                    <div>
                    <form onSubmit={e => FormHandle(e)}>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Number</label>
                            <input type="text" class="form-control" name="cpuId"   placeholder="Enter Here" value={cpuId} onChange={(e) =>onInputChange(e)} required/>
                        </div>
                        <div class="form-group">
                             <label for="exampleInputEmail1">Name</label>
                             <input type="text" class="form-control" name="name"   placeholder="Enter Here" value={name} onChange={(e) =>onInputChange(e)} required/>
                         </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Price In USD</label>
                            <input type="text" class="form-control" name="priceInUSD"   placeholder="Enter Here" value={priceInUSD} onChange={(e) =>onInputChange(e)} required/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Price In PLN</label>
                            <input type="text" class="form-control" name="priceInPLN"   placeholder="Enter Here" value={priceInPLN} onChange={(e) =>onInputChange(e)} required/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Exchange Rate</label>
                            <input type="text" class="form-control" name="exchangeRate"   placeholder="Enter Here" value={exchangeRate} onChange={(e) =>onInputChange(e)} required/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Accounting Date</label>
                            <input type="text" class="form-control" name="accDate"   placeholder="Enter Here" value={accDate} onChange={(e) =>onInputChange(e)} required/>
                        </div>
                        <div className="container text-center">
                        <button type="submit" class="btn btn-outline-success my-2 text-center mr-2">Update Computer</button>
                        <button type="reset" class="btn btn-outline-primary text-center mr-2">Clear Computer</button>
                        </div>
                    </form>
                </div>
            </div>
            </div>
        </div>
        </div>
    )
}
export default UpdateComputer