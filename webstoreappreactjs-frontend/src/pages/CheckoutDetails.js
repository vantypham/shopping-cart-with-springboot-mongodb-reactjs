import React from "react";
import {useNavigate, useLocation} from 'react-router-dom';
import {useForm} from 'react-hook-form';
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";
import { Main } from "./Main";

export const CheckoutDetails = ({title}) => {
    const navigate = useNavigate();
    const location = useLocation();
    const dispatch = useDispatch();
    const personalInfo = location.state.personalInfo;
    const paymentInfo = location.state.paymentInfo;
    const totalAmount = useSelector(state=>state.totalAmount);
    const orderItemList = useSelector(state=>state.itemLines);
    const order = {totalAmount: totalAmount, 
                    personalInfo: personalInfo,
                    paymentInfo: paymentInfo,
                    orderItemList: orderItemList}
    const client = axios.create({
        baseURL: "http://localhost:8080/api/orders"
    })

    const addOrder = () => {
        console.log(order);
        client.post("http://localhost:8080/api/orders", order)
        .then((response) =>{
            console.log("added order " + response.data );
            dispatch({type: 'removecart', item: [], totalAmount:0, itemQuantity:0});
            alert('The order has been stored to database!');
            navigate('/orders');
        });
    }

    return (
        <div>
            <Main title={title}/>
            <table >
                <tbody>
                    <tr>
                        <td>TotalAmount: </td>
                        <td id="totalAmountDetailTxtID">{totalAmount}</td>
                        <td>$</td>
                    </tr>
                    <tr>
                    </tr>
                </tbody>
            </table>

            <div>
              <h3>Personal Information</h3>
                <table border={1}>
                  <tbody>
                    <tr><td>name</td><td>email</td><td>phone</td><td>street</td><td>city</td><td>zip</td></tr>
                    <tr>
                      <td id="nameDetailTxtID">{personalInfo.name}</td>
                      <td id="emailDetailTxtID">{personalInfo.email}</td>
                      <td id="phoneDetailTxtID">{personalInfo.phone}</td>
                      <td id="streetDetailTxtID">{personalInfo.street}</td>
                      <td id="cityDetailTxtID">{personalInfo.city}</td>
                      <td id="zipDetailTxtID">{personalInfo.zip}</td>
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
                      <td id="creditCardTypeDetailTxtID">{paymentInfo.creditCardType}</td>
                      <td id="numberDetailTxtID">{paymentInfo.number}</td>
                      <td id="validDateDetailTxtID">{paymentInfo.validDate}</td>
                      <td id="validationCodeDetailTxtID">{paymentInfo.validationCode}</td>
                    </tr>
                  </tbody>
                </table>
            </div>

            <div>
              <h3>order Item List</h3>
                <table border={1}>
                  <tbody>
                    <tr><td>productNumber</td><td>name</td><td>quantity</td><td>price</td></tr>
                    {orderItemList.map(item=>{
                      let productNumberId = item.productNumber + "_productNumberDetailID";
                      let nameId = item.name + "_nameDetailID";
                      let quantityId = item.quantity + "_quantityDetailID";
                      let priceId = item.price + "_priceDetailID";
                      return (
                        <tr key={item.productNumber}>
                          <td id={productNumberId}>{item.productNumber}</td>
                          <td id={nameId}>{item.name}</td>
                          <td id={quantityId}>{item.quantity}</td>
                          <td id={priceId}>{item.price}</td>
                        </tr>
                    )})}
                  </tbody>
                </table>
            </div>
            <br/>
            <button id="confirmOrderID" onClick={addOrder}>Confirm Order</button>
        </div>
    );
}