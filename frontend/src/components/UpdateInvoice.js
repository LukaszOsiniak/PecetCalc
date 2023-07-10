import React,{useState,useEffect} from 'react'
import axios from 'axios';
import {useParams} from "react-router-dom";
function UpdateInvoice() {
    const {id} = useParams();

    useEffect(()=>{
        getInvoiceById();
    },[])
    const [invoice, setInvoice] = useState({
         invId: '', name: '', computers: '', exchangeRate: '', invPriceInPln: '', invPriceInUsd: '', invDate:'',
    });
    const {invId, name, computers, exchangeRate, invPriceInPln, invPriceInUsd, invDate  } = invoice;
    const onInputChange = e =>{
        setInvoice({...invoice,[e.target.name]:e.target.value})
    }

    const FormHandle = e =>{
        e.preventDefault();
        updateDataToServer(invoice)
    }
    const updateDataToServer=(data) =>{
        axios.put("http://localhost:8080/invoices/"+ id,data).then(
           (response)=>{
                   alert("Invoice Updated Successfully");
            },(error)=>{
                    alert("Operation failed");
            }
        );
    };

    const getInvoiceById= async e =>{
        const invoicesInfo = await axios.get("http://localhost:8080/invoices/" + id);
        setInvoice(invoicesInfo.data);
    }

    return (
        <div>
            <div className="container">
            <div className="border border-dark bg-dark text-white">
                <div class="jumbotron">
                    <h4 >Update Invoice</h4>
                    <div>
                    <form onSubmit={e => FormHandle(e)}>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Number</label>
                            <input type="text" class="form-control" name="invId"   placeholder="Enter Here" value={invId} onChange={(e) =>onInputChange(e)} />
                        </div>
                        <div class="form-group">
                             <label for="exampleInputEmail1">Name</label>
                             <input type="text" class="form-control" name="name"   placeholder="Enter Here" value={name} onChange={(e) =>onInputChange(e)} />
                         </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Computers</label>
                            <input type="text" class="form-control" name="computers"   placeholder="Enter Here" value={computers} onChange={(e) =>onInputChange(e)} />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Exchange Rate </label>
                            <input type="text" class="form-control" name="exchangeRate"   placeholder="Enter Here" value={exchangeRate} onChange={(e) =>onInputChange(e)} />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Price In PLN</label>
                            <input type="text" class="form-control" name="invPriceInPln"   placeholder="Enter Here" value={invPriceInPln} onChange={(e) =>onInputChange(e)} />
                        </div>
                        <div class="form-group">
                             <label for="exampleInputEmail1">Price In USD</label>
                             <input type="text" class="form-control" name="invPriceInUsd"   placeholder="Enter Here" value={invPriceInUsd} onChange={(e) =>onInputChange(e)} />
                         </div>
                         <div class="form-group">
                              <label for="exampleInputEmail1">Invoice Date</label>
                              <input type="text" class="form-control" name="invDate"   placeholder="Enter Here" value={invDate} onChange={(e) =>onInputChange(e)} />
                          </div>
                        <div className="container text-center">
                        <button type="submit" class="btn btn-outline-success my-2 text-center mr-2">Update Invoice</button>
                        <button type="reset" class="btn btn-outline-primary text-center mr-2">Clear Invoice</button>
                        </div>
                    </form>
                </div>
            </div>
            </div>
        </div>
        </div>
    )
}
export default UpdateInvoice