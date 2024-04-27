import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import {Products, ProductDetail, Orders, ShoppingCart, Cart, 
  Main, OrderDetail, CheckoutPersonalInfo, CheckoutPayment, CheckoutDetails, ProductReview} from './pages'

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route exact path='/' element={<Main title={"Welcome to AZ shop"}/>}/>
          <Route path='/products' element={<Products title={"Welcome to Products Page"}/>}/>
          <Route path='/productdetail' element={<ProductDetail title={"Welcome to ProductDetail Page"}/>}/>
          <Route path='/productreview' element={<ProductReview title={"Welcome to ProductReview Page"}/>}/>
          <Route path='/orders' element={<Orders title={"Welcome to Orders Page"}/>}/>
          <Route path='/orderdetail' element={<OrderDetail title={"Welcome to OrderDetail Page"}/>}/>
          <Route path='/shoppings' element={<ShoppingCart title={"Welcome to Shopping Page"}/>}/>
          <Route path='/cart' element={<Cart title={"Welcome to Cart Page"}/>}/>
          <Route path='/checkout/personalinfo' element={<CheckoutPersonalInfo title={"Welcome to Checkout Personal Infor Page"}/>}/>
          <Route path='/checkout/payment' element={<CheckoutPayment title={"Welcome to Checkout Payment Page"}/>}/>
          <Route path='/checkout/details' element={<CheckoutDetails title={"Welcome to Checkout Details Page"}/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
