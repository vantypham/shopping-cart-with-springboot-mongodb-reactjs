import React,{useState} from "react";
import {useLocation, useNavigate} from 'react-router-dom';
import {useForm} from 'react-hook-form';
import { Main } from "./Main";

export const CheckoutPayment = ({title}) => {
    const navigate = useNavigate();
    const location = useLocation();
    const personalInfo = location.state.personalInfo;
    const [paymentInfo, setPaymentInfo] = useState({});
    //validate react-hook-form
    const {register, handleSubmit, formState: {errors}} = useForm();

    const onSubmit = (paymentInfo) => {
        console.log(paymentInfo);
        console.log("onSubmit");
        navigate('/checkout/details',{state:{personalInfo:personalInfo, paymentInfo: paymentInfo}});
    }

    const handleFieldChange = (e) =>{
        setPaymentInfo({...paymentInfo,[e.target.name]:e.target.value});
    }
    return (
        <div>
            <Main title={title}/>
            <form onSubmit={handleSubmit(onSubmit)}>
                <table>
                    <tbody>
                        <tr>
                            <td>CreditCardType</td>
                            <td><select
                                    type='text'
                                    placeholder='creditCardType'
                                    id='creditCardTypeCheckoutID'
                                    name='creditCardType'
                                    onChange={handleFieldChange}
                                    {...register("creditCardType",{
                                        required: "creditCardType is required.",
                                    })}
                                >
                                    <option>Visa</option>    
                                    <option>MasterCard</option>    
                                </select>
                            </td>
                        <td>{errors.creditCardType && (<p style={{color: "red"}}>{errors.creditCardType.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Number</td>
                            <td><input
                                type='text'
                                placeholder='number'
                                name='number'
                                id='creditCardNumberCheckoutID'
                                onChange={handleFieldChange}
                                {...register("number",{
                                    required: "number is required.",
                                    pattern:{
                                        value: /^[0-9]{1,}$/,
                                        message: "please input number"
                                    },
                                    minLength:{
                                        value:5,
                                        message: "number should at-least 5 number"
                                    }
                                })}
                            />
                            </td>
                        <td>{errors.number && (<p style={{color: "red"}}>{errors.number.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>Valid Date</td>
                            <td><input
                                type='text'
                                placeholder='validDate'
                                id='validDateCheckoutID'
                                name='validDate'
                                onChange={handleFieldChange}
                                {...register("validDate",{
                                    required: "validDate is required.",
                                })}
                            />
                            </td>
                        <td>{errors.validDate && (<p style={{color: "red"}}>{errors.validDate.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td>validation Code</td>
                            <td><input
                                type='text'
                                placeholder='validationCode'
                                id='validationCodeCheckoutID'
                                name='validationCode'
                                onChange={handleFieldChange}
                                {...register("validationCode",{
                                    required: "validationCode is required.",
                                })}
                            />
                            </td>
                        <td>{errors.validationCode && (<p style={{color: "red"}}>{errors.validationCode.message}</p>)}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button id='processDetailBtnID' type='submit'>Process Final Details</button></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    );
}