import React, { useEffect, useState } from "react";
import {useLocation} from 'react-router-dom';
import axios from 'axios';
import {useForm} from 'react-hook-form';
import { Main } from "./Main";
import '../App.css';

export const ProductDetail = ({title}) => {
    const location = useLocation();
    const currentProductNumber = location.state.productNumber;
    const initProduct = location.state.product;
    const client = axios.create({
        baseURL:"http://localhost:8080/api/products"
    });

    const [initialProduct, setInitialProduct] = useState({productNumber: currentProductNumber, 
        name: initProduct[0].name, 
        price: initProduct[0].price, 
        description: initProduct[0].description, 
        numberInStock: initProduct[0].numberInStock,
        reviewList: initProduct[0].reviewList
    });
    const [currentProduct, setCurrentProduct] = useState(initialProduct);
    const [msg, setMsg] = useState('');
    //validate react-hook-form
    const {register, handleSubmit, formState: {errors}} = useForm(
        {
            defaultValues: {
                productNumber: currentProductNumber,
                name: initProduct[0].name,
                price: initProduct[0].price,
                productNumber: initProduct[0].productNumber,
                numberInStock: initProduct[0].numberInStock,
                description: initProduct[0].description,
                reviewList: initProduct[0].reviewList
            }
        }
    );

    React.useEffect(()=>{
        loadProduct();
    },[]);
    //load product detail from backend
    const loadProduct = () => {
        client.get("/"+currentProductNumber)
        .then((response)=>{
            // console.log(response.data);
            setCurrentProduct(response.data);
            setInitialProduct(response.data);
        })
    }

    //update product detail from backend
    const handleFieldChange = (e) => {
        setCurrentProduct({...currentProduct,[e.target.name]:e.target.value});

    }

    //update function
    const onSubmit = (currentProduct) => {
        console.log("onSubmit update");
        currentProduct.productNumber = currentProductNumber;
        client.put("/" + currentProductNumber,currentProduct)
        .then((response)=>{
            console.log(response);
            setMsg("Updated successfully!");
            loadProduct();
        })
    }
    let productdetail = (
        <div>
            <Main title={title}/>
            <form onSubmit={handleSubmit(onSubmit)}>
                <table id="tableOneID">
                    <tbody>
                        <tr>
                            <td>Product Number:</td>
                            <td id="productNumberTextID">{initialProduct.productNumber}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Product Name:</td>
                            <td><input
                                type="text"
                                // value={initialProduct.name}
                                name="name"
                                onChange={handleFieldChange}
                                {...register("name",{
                                    required: "Product Name is required."
                                })}
                            />
                            </td>
                        <td>{errors.name && (<p style={{color: "red"}}>{errors.name.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Price:</td>
                            <td><input
                                type="text"
                                // value={initialProduct.price}
                                name="price"
                                onChange={handleFieldChange}
                                {...register("price",{
                                    required: "price is required.",
                                    pattern:{
                                        value: /^[0-9]+([,\.][0-9]+)?$/,
                                        message: "please input number"
                                    },
                                    min:{
                                        value:1,
                                        message: "price should at-least 1"
                                    }
                                })}
                            />
                            </td>
                        <td>{errors.price && (<p style={{color: "red"}}>{errors.price.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td><input
                                type="text"
                                // value={initialProduct.description}
                                name="description"
                                onChange={handleFieldChange}
                                {...register("description",{
                                    required: "Product Name is required."
                                })}
                            />
                            </td>
                        <td>{errors.description && (<p style={{color: "red"}}>{errors.description.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Number In Stock:</td>
                            <td><input
                                type="text"
                                // value={initialProduct.numberInStock}
                                name="numberInStock"
                                onChange={handleFieldChange}
                                {...register("numberInStock",{
                                    required: "numberInStock is required.",
                                    pattern:{
                                        value: /^[0-9]{1,}$/,
                                        message: "please input number"
                                    },
                                    min:{
                                        value:0,
                                        message: "numberInStock should at-least 0"
                                    }
                                })}
                            />
                            </td>
                        <td>{errors.numberInStock && (<p style={{color: "red"}}>{errors.numberInStock.message}</p>)}</td>
                        </tr>
                    </tbody>
                </table>
                <br/>
                <button id="updateBtnID" type="submit">Update</button>
                <p id="messageID" className="success_msg">{msg}</p>
                <hr/>
                <h4>Review List</h4>
                <table id="tableTwoID">
                    <tbody>
                        {initialProduct.reviewList.map((review)=>
                            (
                            <tr key={review.id}>
                                <td>Id: {review.id} <br/> 
                                &nbsp;&nbsp;&nbsp;&nbsp;username: {review.username} <br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;message: {review.message} <br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;review: {review.rate} <br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;date: {review.date} <br/> <br/>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </form>
        </div>
    );
    return productdetail;
}