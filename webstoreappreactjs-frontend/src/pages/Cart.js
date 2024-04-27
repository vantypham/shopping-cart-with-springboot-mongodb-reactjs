import React from "react";
import {useDispatch, useSelector} from 'react-redux';
import { useNavigate } from 'react-router-dom';
import {Main} from './Main';

export const Cart = ({title}) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const itemQuantity = useSelector(state=>state.itemQuantity);
    const itemLines = useSelector(state=>state.itemLines);
    const totalAmount = useSelector(state=>state.totalAmount);
    const onCheckout = () => {
        if(itemLines.length ===0){
            alert("No item to checkout, navigate to shoppingCart");
            navigate('/shoppings');
        } else {
            navigate('/checkout/personalinfo');
        }
    }
    const removeItem = (e) =>{
        let item = itemLines.filter(item => item.productNumber === e.target.value);
        let item1 = {
          productNumber :  item[0].productNumber,
          name : item[0].name,
          price : item[0].price,
          quantity : item[0].quantity
        };
        // console.log(item1);
        dispatch({type: 'removeitem', item: item1});
      }
    let cartpage = (
        <div>
            <Main title={title}/>
            <h2>cart: (itemQuantity: <a id="itemQuantityCartID">{itemQuantity}</a> , totalAmount: <a id="totalAmountCartID">{totalAmount}</a> )&nbsp;</h2>
            <table border={1}>
              <tbody>
                <tr>
                    <th>ProductNumber</th>
                    <th>name</th>
                    <th>quantity</th>
                    <th>unit price</th>
                </tr>
                {itemLines.map(item=>{
                  let removeId = item.productNumber + "_removeCartID";
                  return (
                    <tr key={item.productNumber}>
                        <td>{item.productNumber}</td>
                        <td>{item.name}</td>
                        <td>{item.quantity}</td>
                        <td>{item.price}</td>
                        <td><button id={removeId} onClick={removeItem} value={item.productNumber}>Remove</button></td>
                    </tr>
                )})}
              </tbody>
            </table>
            <br/>
            <button id="checkoutBtnID" onClick={onCheckout}>Checkout</button>
        </div>
    );
    return cartpage;
}