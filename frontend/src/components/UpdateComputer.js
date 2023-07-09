import React,{useState,useEffect} from 'react'
import axios from 'axios';
import {useParams} from "react-router-dom";
function UpdateComputer() {
    const {id} = useParams(); // getting url id
    const URL = `http://localhost:8080/computers/${id}`;

    useEffect(()=>{
        getComputerById();
    },[])
    const [computer, setComputer] = useState({
         cpuId: '', name: '', priceInUSD: '', priceInPln: '', exchangeRate: ''
    });
    const {cpuId, name, priceInUSD, priceInPln, exchangeRate } = computer;
    const onInputChange = e =>{
        setComputer({...computer,[e.target.name]:e.target.value})
    }

    const FormHandle = e =>{
        e.preventDefault();
        updateDataToServer(computer)
    }
    const updateDataToServer=(data) =>{
        axios.put(URL,data).then(
           (response)=>{
                   alert("Computer Updated Successfully");
            },(error)=>{
                    alert("Operation failed");
            }
        );
    };

    const getComputerById= async e =>{
        const computersInfo = await axios.get(URL);
        alert(JSON.stringify(computersInfo))
        setComputer(computersInfo.data);
    }

    return (
        <div>
            <div className="container">
            <div className="w-75 mx-auto shadow p-5 mt-2 bg-light">
                <div class="jumbotron">
                    <h1 class="display-4 text-center">Update Computer</h1>
                    <div>
                    <form onSubmit={e => FormHandle(e)}>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Number</label>
                            <input type="text" class="form-control" name="cpuId"   placeholder="Enter Here" value={cpuId} onChange={(e) =>onInputChange(e)} />
                        </div>
                        <div class="form-group">
                             <label for="exampleInputEmail1">Name</label>
                             <input type="text" class="form-control" name="name"   placeholder="Enter Here" value={name} onChange={(e) =>onInputChange(e)} />
                         </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Price In USD</label>
                            <input type="text" class="form-control" name="priceInUSD"   placeholder="Enter Here" value={priceInUSD} onChange={(e) =>onInputChange(e)} />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Price In PLN</label>
                            <input type="text" class="form-control" name="priceInPln"   placeholder="Enter Here" value={priceInPln} onChange={(e) =>onInputChange(e)} />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Exchange Rate</label>
                            <input type="text" class="form-control" name="exchangeRate"   placeholder="Enter Here" value={exchangeRate} onChange={(e) =>onInputChange(e)} />
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