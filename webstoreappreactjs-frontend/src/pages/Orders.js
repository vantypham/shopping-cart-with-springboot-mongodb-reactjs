import React, { useState, useEffect } from "react";
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import { Main } from "./Main";

export const Orders = ({title}) =>{
    const navigate = useNavigate();
    //initial list of order
    const [orderList, setOrderList] = useState([]);
    const [status, setStatus] = useState('All');

    const client = axios.create({
        baseURL:"http://localhost:8080/api/orders"
    })

    React.useEffect(()=>{
        loadOrders();
    }, [])

    const loadOrders = ()=>{
        const orders = client.get()
        .then((response)=>{
            // console.log(response.data.orderList);
            setOrderList(response.data.orderList)
        }).catch(function (error){
            alert(error.message);
        })
    }
    const searchOrder = (value)=>{
        const orders = client.get("/search?status="+value)
        .then((response)=>{
            // console.log(response.data.orderList);
            setOrderList(response.data.orderList);
        }).catch(function (error){
            alert(error.message);
        })
    }
    const searchStatus = () => {
        if(status==="PLACED"){
            searchOrder(status);
        } else if(status==="SHIPPED"){
            searchOrder(status);
        } else if(status==="DELIVERED"){
            searchOrder(status);
        } else {
            loadOrders();
        }
    }

    const viewDetail = (e) => {
        navigate('/orderdetail', {state:{orderId: e.target.value}});
    }
    let Orders = (
        <div>
            <Main title={title}/>
            <div>
                Search by Status: 
                <select 
                    type="text"
                    name="status"
                    id="selectStatusID"
                    value={status}
                    onChange={e=>setStatus(e.target.value)} 
                >
                    <option>All</option>
                    <option>PLACED</option>
                    <option>SHIPPED</option>
                    <option>DELIVERED</option>
                </select>
                <button id="searchBtnID" onClick={searchStatus}>Search</button>
            </div>
            <br/>
            <table border={1}>
                <tbody>
                    <tr>
                        <th>OrderID</th><th>Date</th><th>Status</th><th>Total Amount</th>
                    </tr>
                    {orderList.map((order) => { 
                        let viewDetailID = order.orderId+ "_detailID";
                        return (
                        <tr key={order.orderId}>
                            <td>{order.orderId}</td>
                            <td>{order.date}</td>
                            <td>{order.status}</td>
                            <td>{order.totalAmount}</td>
                            <td><button id={viewDetailID} onClick={viewDetail} value={order.orderId}>View Detail</button></td>
                        </tr>);})
                    }
                </tbody>
            </table>
        </div>
    );
    return Orders;
}