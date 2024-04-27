import {configureStore} from '@reduxjs/toolkit';

const cartReducer = (state = {itemQuantity: 0, itemLines:[], totalAmount:0}, action) => {
    if(action.type==='addcart'){
        let existItem = state.itemLines.filter(item=>item.productNumber === action.item.productNumber);
        let filterItem = state.itemLines.filter(item=>item.productNumber !== action.item.productNumber);
        if(existItem.length!==0){
            //check numberInstock and current value in cart
            if(existItem[0].quantity < action.stock){
                let newItem = {productNumber:existItem[0].productNumber,
                    name:existItem[0].name,price: existItem[0].price, quantity: existItem[0].quantity + 1};
                state = {
                    itemQuantity: state.itemQuantity + 1,
                    itemLines: filterItem.concat(newItem),
                    totalAmount: (10*state.totalAmount + action.item.price*10)/10
                }
            } else {
                alert('Can not add to cart Due to No stock');
            }
        } else {
            state = {
                itemQuantity: state.itemQuantity + 1,
                itemLines: state.itemLines.concat(action.item),
                totalAmount: (10*state.totalAmount + action.item.price*10)/10
            }
        }
    }
    if(action.type==='removeitem'){
        let filterItem = state.itemLines.filter(item=>item.productNumber !== action.item.productNumber);
        state = {
            itemQuantity: state.itemQuantity -action.item.quantity,
            itemLines: filterItem,
            totalAmount: (10*state.totalAmount - (action.item.price*action.item.quantity)*10)/10
        }
    }
    if(action.type==='removecart'){
        state = {
            itemQuantity: 0,
            itemLines: [],
            totalAmount: 0
        }
    }
    return state;
}

const store = configureStore({
    reducer: cartReducer
});

export default store;