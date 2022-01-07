import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from 'react-router-dom';
import {createStore} from 'redux'
import allReducer from './reducers'
import {Provider} from 'react-redux';

const store = createStore(allReducer);

/*const ClothesData = [
  {name: "jean", category:"pants", size:"40", price:"120", color:"Blue", image:"1.jpg", id:1},
  {name: "long skirt", category:"skirts", size:"32", price:"50", color:"Green", image:"2.jpg", id:2},
  {name: "blouse", category:"tops", size:"30", price:"70", color:"Red", image:"3.jpg", id:3}
]*/

ReactDOM.render(
  <Provider store= {store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
    </Provider>
  ,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
