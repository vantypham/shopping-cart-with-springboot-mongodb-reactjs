import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from 'axios';
import {useForm} from 'react-hook-form';
import { Main } from "./Main";

export const ProductReview = ({title}) => {
    const navigate = useNavigate();
    const location = useLocation();
    const productNumber = location.state.productNumber;
    const name = location.state.name;
    const [review, setReview] = useState({});

    //validate react-hook-form
    const {register, handleSubmit, formState: {errors}} = useForm();

    const handleFieldChange = (e) =>{
        setReview({...review,[e.target.name]:e.target.value});
    }
    const onSubmit = (review) => {
        // console.log(review);
        console.log("onSubmit");
        //used when call ApI
        console.log("call the server");
        addReview(review);
    }

    const client = axios.create({
        baseURL: "http://localhost:8080/api/products"
    })
    const addReview = (review) => {
        console.log(review);
        client.post("http://localhost:8080/api/products/" + productNumber, review)
        .then((response) =>{
            console.log("added review " + response );
            navigate('/shoppings');
        });
    }
    return(
        <div>
            <Main title={title}/>
            <h3>Make a review for {name} Product</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <table>
                    <tbody>
                        <tr>
                            <td>Username</td>
                            <td><input
                                type='text'
                                placeholder='username'
                                name='username'
                                onChange={handleFieldChange}
                                {...register("username",{
                                    required: "username is required.",
                                    minLength:{
                                        value:2,
                                        message: "username should at-least 2 characters."
                                    }
                                })}
                            />
                            </td>
                        <td>{errors.username && (<p style={{color: "red"}}>{errors.username.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Message</td>
                            <td><input
                                type='text'
                                placeholder='message'
                                name='message'
                                onChange={handleFieldChange}
                                {...register("message",{
                                    required: "message is required.",
                                    minLength:{
                                        value:5,
                                        message: "message should at-least 5 char"
                                    }
                                })}
                            />
                            </td>
                        <td>{errors.message && (<p style={{color: "red"}}>{errors.message.message}</p>)}</td>
                        </tr>
                        <tr>
                        <td>rate</td>
                                <td><select
                                        type='text'
                                        placeholder='rate'
                                        name='rate'
                                        onChange={handleFieldChange}
                                        {...register("rate",{
                                            required: "rate is required.",
                                        })}
                                    >
                                        <option>5 - EXCELLENT</option>    
                                        <option>4 - GOOD</option>    
                                        <option>3 - NORMAL</option>    
                                        <option>2 - BAD</option>    
                                        <option>1 - POOR</option>    
                                    </select>
                                </td>
                            <td>{errors.rate && (<p style={{color: "red"}}>{errors.rate.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button id="addReviewBtnID" type='submit'>Add Review</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    );
}