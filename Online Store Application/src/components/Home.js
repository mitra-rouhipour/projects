import {useSelector, useDispatch} from 'react-redux';
import{increment, decrement} from '../action';
import { nanoid } from "nanoid";
import React, { useState, useEffect } from "react";
import Form from "./Form";
import Clothes from './Clothes';

function Home(props) {
  //redux section
  const counter = useSelector(state => state.counter);
  const isLogged = useSelector(state => state.isLogged);
  const dispatch =useDispatch();// 


  //local storage management
  useEffect(()=>{
    localStorage.setItem('clothesItems',JSON.stringify(lists));
  }
  );

  function handleClearLocalStorage(){
    localStorage.removeItem('clothesItems');
    setLists([]);
  }
  
  useEffect(()=>{
    const list = localStorage.getItem('clothesItems');
    if(list) {
      setLists(JSON.parse(list));
    }
  }, []);
  
  useEffect(()=>{
    localStorage.setItem('clothesItems',JSON.stringify(lists));
  }
  );

  function editPrice(id, newPrice) {
    const editedPriceList = lists.map(item => {
    // if this task has the same ID as the edited objecr
      if (id === item.id) {
        //
        return {...item, price: newPrice}
      }
      return item;
    });
    setLists(editedPriceList);
  }

  function deleteCloth(id){
    const remainingClothes = lists.filter(cloth => id !== cloth.id);
    setLists (remainingClothes);
  }

  function addCloth(name,size,price,color,category,image){
    const newCloth = { id: "todo-" + nanoid(), name: name, size: size, 
    price: price, color: color, category:category, image: image };
    setLists([...lists, newCloth]);
  }
  const[lists,setLists] = useState([]);


  const clothesList = lists.map(item => (
    <Clothes 
    name={item.name} 
    size={item.size}
    price={item.price}
    color = {item.color} 
    category={item.category}
    id={item.id} 
    key={item.id}
    deleteCloth = {deleteCloth}
    editPrice = {editPrice}/>
  ));

  return(
        

    <div className="todoapp stack-large">
          <h1>Clothes</h1>
          <Form addCloth={addCloth}/>
          <h2 id="list-heading">
            List Of Clothes
          </h2>
          <ul
            className="todo-list stack-large stack-exception"
            aria-labelledby="list-heading"
          >
              {clothesList}
    
          </ul>
          <div>
          <button
            type="button"
            className="btn"
            onClick= {handleClearLocalStorage}
          >
            Clear All Tasks <span className="visually-hidden">{props.name}</span>
          </button>
          </div>
          <div>
            <h1>Counter{counter}</h1>
            <button
            type="button"
            className="btn btn__danger"
            onClick={() => dispatch(increment())}
          >
            Increment <span className="visually-hidden">{props.name}</span>
          </button>

          <button
            type="button"
            className="btn btn__danger"
            onClick={() => dispatch(decrement())}
          >
            Decrement <span className="visually-hidden">{props.name}</span>
          </button>
          
          <h1>{isLogged ? <h3> Valuable information I should not see</h3> : ''}</h1> 
          </div>


        </div>
      );  

}

export default Home;
