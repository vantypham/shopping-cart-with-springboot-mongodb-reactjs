import React, { useState, useEffect } from 'react'
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import {useForm} from 'react-hook-form';
import { Main } from './Main';

export const Products = ({title}) => {
    const navigate = useNavigate();
    const cleanproduct = {productNumber:"", name:"", price:"",description:"",numberInStock:"",reviewList:[]};
    const [product, setProduct] = useState(cleanproduct);
    const [productFilter, setProductFilter] = useState('');
    const [productList, setProductList] = useState([]);
    const [searchMessage, setSearchMessage] = useState('');
    const [msg, setMsg] = useState('');

    //validate react-hook-form
    const {register, handleSubmit, formState: {errors}} = useForm();
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
    const searchProduct = () =>{
        const isExistsProduct = productList.filter(e=>e.productNumber===productFilter);
        if(isExistsProduct.length !=0){
            client.get("/" + productFilter)
            .then((response)=>{
                //map data to array 
                let array = new Array(response.data);
                // console.log(array);
                setProductList(array);
                setSearchMessage("");
            }).catch(function (error){
                alert(error.message);
            })
        } else {
            setSearchMessage(<a style={{color:"red"}}>This Product Number is incorrect, show all</a>);
            loadProducts();
        }
    }

    // const addProduct = (product) => {
    //     client.post("http://localhost:8080/api/products", product)
    //     .then((response) =>{
    //         console.log("added product " + response.data.productNumber );
    //         setMsg("Added successfully!");
    //         loadProducts();
    //     });
    // }

    const removeProduct = (e) => {
        console.log(e.target.value);
        client.delete("/" + e.target.value)
        .then((response)=>{
            console.log("removed product " + response.headers);
            setMsg("Removed successfully!");
            loadProducts();
            setProduct(cleanproduct);
        }).catch(function (error){
            alert(error.message);
        });
    }

    const onSubmit = (product) => {
        console.log(product);
        console.log("onSubmit");
        if (product){
            //use for test list
            setProductList(productList.concat(product));
            //used when call ApI
            console.log("call the server");
            console.log(product);
            //addProduct(product);
            client.post("http://localhost:8080/api/products", product)
                .then((response) =>{
                    console.log("added product " + response.data.productNumber );
                    setMsg("Added successfully!");
                    loadProducts();
                    setProduct(cleanproduct);
                }).catch(function (error){
                    alert(error.message);
                });
        }
        

    }

    const handleFieldChange = (e) =>{
        setProduct({...product,[e.target.name]:e.target.value});
    }

    const handleViewDetail = (e) => {
        // console.log(e.target);
        const product = productList.filter(pro=>pro.productNumber===e.target.value)
        console.log(product);
        //navigate to detail page
        navigate('/productdetail', {state: {productNumber:e.target.value, product: product}});
    }

    let products = (
        <div>
            <Main title={title}/>
            <div>
                Filter by ProductNumber 
                <input
                    type='text' id="searchTextID"
                    placeholder='ex: P1234567'
                    value={productFilter}
                    onChange={e=>setProductFilter(e.target.value)}
                />
                <button id="searchBtnID" onClick={searchProduct}>Search</button>
                {searchMessage}
            </div>
            
            <br/>
            <div>
                <table border={1}>
                    <tbody>
                        <tr><th>Product Number</th><th>Name</th><th>Price</th><th>Description</th><th>numberInStock</th></tr>
                    {productList.map(product => {
                        let removeId = product.productNumber + "_removeID";
                        let detailId = product.productNumber + "_detailID";
                        return (<tr key={product.productNumber}>
                            <td>{product.productNumber}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.description}</td>
                            <td>{product.numberInStock}</td>
                            <td><button id={removeId} onClick={removeProduct} value={product.productNumber}>Remove</button></td>
                            <td><button id={detailId} onClick={handleViewDetail} value={product.productNumber}>View Detail</button></td>
                        </tr>
                    )})}
                    </tbody>
                </table>

                <h1>Add a new Product</h1>
                <p id="messageID" className="success_msg">{msg}</p>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <table>
                        <tbody>
                            <tr>
                                <td>Product Number</td>
                                <td><input
                                    type='text'
                                    placeholder='productNumber'
                                    name='productNumber'
                                    // value={product.productNumber}
                                    onChange={handleFieldChange}
                                    {...register("productNumber",{
                                        required: "Product Number is required.",
                                        minLength:{
                                            value:8,
                                            message: "Product Number should at-least 8 characters."
                                        }
                                    })}
                                />
                                </td>
                            <td>{errors.productNumber && (<p style={{color: "red"}}>{errors.productNumber.message}</p>)}</td>
                            </tr>
                            <tr>
                                <td>Product Name</td>
                                <td><input
                                    type='text'
                                    placeholder='product Name'
                                    name='name'
                                    // value={product.name}
                                    onChange={handleFieldChange}
                                    {...register("name",{
                                        required: "Product Name is required."
                                    })}
                                />
                                </td>
                            <td>{errors.name && (<p style={{color: "red"}}>{errors.name.message}</p>)}</td>
                            </tr>
                            <tr>
                                <td>Product Price</td>
                                <td><input
                                    type='text'
                                    placeholder='price'
                                    name='price'
                                    // value={product.price}
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
                                <td>Product Description</td>
                                <td><input
                                    type='text'
                                    placeholder='description'
                                    id="descriptionID"
                                    name='description'
                                    // value={product.description}
                                    onChange={handleFieldChange}
                                    {...register("description",{
                                        required: "Product Name is required."
                                    })}
                                />
                                </td>
                            <td>{errors.description && (<p style={{color: "red"}}>{errors.description.message}</p>)}</td>
                            </tr>
                            <tr>
                                <td>Number In Stock</td>
                                <td><input
                                    type='text'
                                    placeholder='Number in Stock'
                                    name='numberInStock'
                                    // value={product.numberInStock}
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
                            <tr>
                                <td></td>
                                <td><button id="addBtnID" type='submit'>Add Product</button></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                
            </div>
        </div>
    );
    return products;
}