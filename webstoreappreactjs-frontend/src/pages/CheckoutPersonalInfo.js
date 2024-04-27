import React,{useState} from "react";
import {useNavigate} from 'react-router-dom';
import {useForm} from 'react-hook-form';
import { Main } from "./Main";

export const CheckoutPersonalInfo = ({title}) => {
    const navigate = useNavigate();
    const [personalInfo, setPersonalInfo] = useState({});
    //validate react-hook-form
    const {register, handleSubmit, formState: {errors}} = useForm();

    const onSubmit = (personalInfo) => {
        console.log(personalInfo);
        console.log("onSubmit");
        navigate('/checkout/payment',{state:{personalInfo:personalInfo}});
    }

    const handleFieldChange = (e) =>{
        setPersonalInfo({...personalInfo,[e.target.name]:e.target.value});
    }
    return (
        <div>
            <Main title={title}/>
            <form onSubmit={handleSubmit(onSubmit)}>
                <table>
                    <tbody>
                        <tr>
                            <td>Name</td>
                            <td><input
                                type='text'
                                placeholder='name'
                                id='nameCheckoutID'
                                name='name'
                                onChange={handleFieldChange}
                                {...register("name",{
                                    required: "name is required.",
                                    minLength:{
                                        value:3,
                                        message: "Name should at-least 3 characters."
                                    }
                                })}
                            />
                            </td>
                        <td>{errors.name && (<p style={{color: "red"}}>{errors.name.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><input
                                type='text'
                                placeholder='email'
                                id='emailCheckoutID'
                                name='email'
                                onChange={handleFieldChange}
                                {...register("email",{
                                    required: "email is required."
                                })}
                            />
                            </td>
                        <td>{errors.email && (<p style={{color: "red"}}>{errors.email.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Phone</td>
                            <td><input
                                type='text'
                                placeholder='phone'
                                id='phoneCheckoutID'
                                name='phone'
                                onChange={handleFieldChange}
                                {...register("phone",{
                                    required: "phone is required.",
                                    min:{
                                        value:9,
                                        message: "price should at-least 9 numbers"
                                    }
                                })}
                            />
                            </td>
                        <td>{errors.phone && (<p style={{color: "red"}}>{errors.phone.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Street</td>
                            <td><input
                                type='text'
                                placeholder='street'
                                id='streetCheckoutID'
                                name='street'
                                onChange={handleFieldChange}
                                {...register("street",{
                                    required: "street is required."
                                })}
                            />
                            </td>
                        <td>{errors.street && (<p style={{color: "red"}}>{errors.street.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td><input
                                type='text'
                                placeholder='city'
                                id='cityCheckoutID'
                                name='city'
                                onChange={handleFieldChange}
                                {...register("city",{
                                    required: "city is required."
                                })}
                            />
                            </td>
                        <td>{errors.city && (<p style={{color: "red"}}>{errors.city.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Zip</td>
                            <td><input
                                type='text'
                                placeholder='zip'
                                id='zipCheckoutID'
                                name='zip'
                                onChange={handleFieldChange}
                                {...register("zip",{
                                    required: "zipcode is required.",
                                    pattern:{
                                        value: /^[0-9]{1,}$/,
                                        message: "please input number"
                                    },
                                    minLength:{
                                        value:5,
                                        message: "zip should at-least 5 number"
                                    }
                                })}
                            />
                            </td>
                        <td>{errors.zip && (<p style={{color: "red"}}>{errors.zip.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button id='processPaymentBtnID' type='submit'>Process Payment</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    );
}