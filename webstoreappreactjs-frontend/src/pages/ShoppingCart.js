import React, { useState } from "react";
import {useSelector, useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';
import axios from "axios";
import { Main } from "./Main";

export const ShoppingCart = ({title}) => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const itemQuantity = useSelector(state=>state.itemQuantity);
    const itemLines = useSelector(state=>state.itemLines);
    const totalAmount = useSelector(state=>state.totalAmount);
    const [productFilter, setProductFilter] = useState('');
    const [productList, setProductList] = useState([]);
    const [searchMessage, setSearchMessage] = useState('');
    React.useEffect(()=>{
      loadProducts();
    }, []);
    
    const client = axios.create({
      baseURL: "http://localhost:8080/api/products"
    })

    const loadProducts = () => {
      const products = client.get()
      .then((response) => {
          // console.log(response.data.products);
          // setProductList
          setProductList(response.data.products);
      }).catch(function (error){
        alert(error.message);
      })
    }

    const searchProduct = (e) =>{
          client.get("/search?name=" + productFilter)
          .then((response)=>{
              //map data to array 
              let array = new Array(response.data);
              console.log(array[0].products);
              setProductList(array[0].products);
              setSearchMessage("");
              if(array[0].products.length===0){
                setSearchMessage(<a style={{color:"red"}}>No Result</a>);
              }
          }).catch(function (error){
            alert(error.message);
        })
    }
    
    const addToCart = (e) =>{
      let item = productList.filter(item => item.productNumber === e.target.value);
      // console.log(item);
      if(item[0].numberInStock!==0){
        let item1 = {
          productNumber :  item[0].productNumber,
          name : item[0].name,
          price : item[0].price,
          quantity : 1
        };
        // console.log(item1);
        let stock = item[0].numberInStock;
        dispatch({type: 'addcart', item: item1, stock: stock});
      } else {
        alert('Can not add to cart Due to No stock');
      }
    }

    const makeReview = (e) => {
      console.log(e.target.value);
      navigate("/productreview", {state:{productNumber: e.target.value, name: e.target.name}});
    }

    const goToCartInfo = () => {
      navigate("/cart");
    }

    let shoppingcart = (
        <div>
            <Main title={title}/>
            <h3>cart: (itemQuantity: <span id="itemQuantityShoppingID">{itemQuantity}</span> , totalAmount: <a id="totalAmountShoppingID">{totalAmount}</a> $)&nbsp;
            <button id="goToCartShoppingID" onClick={goToCartInfo}>GoToCartInfo</button>
            </h3>
            <hr/>
            <div>
                ProductName
                <input
                    type="text"
                    placeholder="name"
                    id="inputProductNameID"
                    value={productFilter}
                    onChange={e=>setProductFilter(e.target.value)}
                />
                <button id="searchShoppingBtnID" onClick={searchProduct}>Search</button>
                {searchMessage}
            </div>
            <br/>
            <table border={1}>
                <tbody>
                    <tr>
                        <th>ProductNumber</th>
                        <th>name</th>
                        <th>price</th>
                        <th>description</th>
                        <th>numberInStock</th>
                    </tr>
                    {productList.map(product=>{
                      let addId = product.productNumber + "_addID";
                      let makeReviewId = product.productNumber + "_makeReviewID";
                      let numberInStockId = product.productNumber + "_numberInStockID";
                      return (
                        <tr key={product.productNumber}>
                            <td>{product.productNumber}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.description}</td>
                            <td id={numberInStockId}>{product.numberInStock}</td>
                            <td><button id={addId} onClick={addToCart} value={product.productNumber} >AddToCart</button></td>
                            <td><button id={makeReviewId} onClick={makeReview} value={product.productNumber} name={product.name}>MakeReiew</button></td>
                        </tr>
                    )})}
                </tbody>
            </table>
        </div>
    );
    return shoppingcart;
}