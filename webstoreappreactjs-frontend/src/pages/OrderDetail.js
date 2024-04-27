import React, {useState, useEffect} from "react";
import {useLocation} from 'react-router-dom';
import axios from "axios";
import { Main } from "./Main";

export const OrderDetail = ({title}) => {
    const location = useLocation();
    const initdata = {
      "orderId": "",
      "status": "",
      "date": "",
      "totalAmount": 0,
      "personalInfo": {
          "name": "",
          "email": "",
          "phone": "",
          "street": "",
          "city": "",
          "zip": ""
      },
      "paymentInfo": {
          "creditCardType": "",
          "number": "",
          "validDate": "",
          "validationCode": ""
      },
      "orderItemList": [
          {
              "productNumber": "",
              "name": "",
              "quantity": 0,
              "price": 0
          },
          {
              "productNumber": "0",
              "name": "",
              "quantity": 0,
              "price": 0
          }
      ]
  }
    const [order, setOrder] = useState(initdata);
    const orderId = location.state.orderId;
    React.useEffect(()=>{
      loadOrder();
    },[])

    const client = axios.create({
      baseURL:"http://localhost:8080/api/orders"
    })

    const loadOrder = async() => {
      const order = client.get("/" + orderId)
      .then((response)=>{
        console.log(response.data);
        setOrder(response.data);
      })
    }

    const MoveToOtherStatus = (status) => {
      client.post("http://localhost:8080/api/orders/" + orderId, {status:status})
      .then((response)=>{
        console.log(response);
        alert(`Updated the Status ${status} successfully!`);
        loadOrder();
      })
    }
    let orderdetail = (
        <div>
            <Main title={title}/>
            <table border={1}>
                <tbody>
                    <tr>
                        <td>OrderId: </td>
                        <td>{order.orderId}</td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td id="statusTextID">{order.status}</td>
                    </tr>
                    <tr>
                        <td>Date: </td>
                        <td>{order.date}</td>
                    </tr>
                    <tr>
                        <td>totalAmount: </td>
                        <td>{order.totalAmount}</td>
                    </tr>
                    <tr>
                    </tr>
                </tbody>
            </table>
            <br/>
            <div>
                Change Status:
                <button id="moveToShipBtnID" onClick={()=>MoveToOtherStatus("SHIPPED")}>MoveToSHIPPED</button>
                &nbsp;
                <button id="moveToDeliveredBtnID" onClick={()=>MoveToOtherStatus("DELIVERED")}>MoveToDELIVERED</button>
            </div>

            <div>
              <h3>Personal Information</h3>
                <table border={1}>
                  <tbody>
                    <tr><td>name</td><td>email</td><td>phone</td><td>street</td><td>city</td><td>zip</td></tr>
                    <tr>
                      <td>{order.personalInfo.name}</td>
                      <td>{order.personalInfo.email}</td>
                      <td>{order.personalInfo.phone}</td>
                      <td>{order.personalInfo.street}</td>
                      <td>{order.personalInfo.city}</td>
                      <td>{order.personalInfo.zip}</td>
                    </tr>
                  </tbody>
                </table>
            </div>
            <div>
              <h3>Payment Information</h3>
                <table border={1}>
                  <tbody>
                    <tr><td>creditCardType</td><td>number</td><td>validDate</td><td>validationCode</td></tr>
                    <tr>
                      <td>{order.paymentInfo.creditCardType}</td>
                      <td>{order.paymentInfo.number}</td>
                      <td>{order.paymentInfo.validDate}</td>
                      <td>{order.paymentInfo.validationCode}</td>
                    </tr>
                  </tbody>
                </table>
            </div>

            <div>
              <h3>order Item List</h3>
                <table border={1}>
                  <tbody>
                    <tr><td>productNumber</td><td>name</td><td>quantity</td><td>price</td></tr>
                    {order.orderItemList.map(item=>(
                      <tr key={item.productNumber}>
                        <td>{item.productNumber}</td>
                        <td>{item.name}</td>
                        <td>{item.quantity}</td>
                        <td>{item.price}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
            </div>
        </div>
    );
    return orderdetail;
} 