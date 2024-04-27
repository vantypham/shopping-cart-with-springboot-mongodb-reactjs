import React from "react";
import {useNavigate} from 'react-router-dom';
import '../App.css';

export const Main = ({title}) => {
    let navigate = useNavigate();
    
    const navigateToProducts = () => {
        navigate('/products');
    }

    const navigateToOrders = () => {
        navigate('/orders');
    }

    const navigatetoShopping = () => {
        navigate('/shoppings');
    }
    let main = (
        <div className="App">
            <div>
                <br/>
                <button id="menuProductID" onClick={navigateToProducts}>Products Management</button>
                <button id="menuOrderID" onClick={navigateToOrders}>Orders Management</button>
                <button id="menuShoppingID"  onClick={navigatetoShopping}>ShoppingCart</button>
                <h1>{title}</h1>
                <hr/>
            </div>
        </div>
    );
    return main;
}