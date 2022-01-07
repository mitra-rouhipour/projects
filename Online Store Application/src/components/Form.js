import React, {useState} from "react";

//import { Button,FormContro,FormGroup,FormLabel } from 'react-bootstrap';
//import 'bootstrap/dist/css/bootstrap.min.css'

function Form(props){
  const [name, setName]= useState("");
  const [size, setSize]= useState("");
  const [price, setPrice]= useState("");
  const [color, setColor]= useState("");
  const [category, setCategory]= useState("");
  const [image, setImage]= useState(null);

  function handleSubmit(e){
    e.preventDefault();
    props.addCloth(name,size,price,color,image);

    setName("");
    setSize("");
    setColor("");
    setPrice("");
    setCategory("");
    setImage(null);
  }

  function handleChange(e){
    setName(e.target.value);
  }
  function sizeChange(e){
    setSize(e.target.value);
  }

  function priceChange(e){
    setPrice(e.target.value);
  }

  function colorChange(e){
    setColor(e.target.value);
  }

  function categoryChange(e){
    setCategory(e.target.value);
  }


    return(
        <form onSubmit={handleSubmit}> 
        <h2 className="label-wrapper">
          <label htmlFor="new-todo-input" className="label__lg">
            Insert the information of the new item.
          </label>
        </h2>
        <label htmlFor="new-todo-input" className="label__lg">
            Name
        </label><br/>
        <input style = {{width : "200px", height: "50px"}}
          type="text"
          id="new-todo-input"
          className="input input__lg"
          name="text"
          autoComplete="off"
          value={name}
          onChange={handleChange}
        />
        <label htmlFor="new-todo-input" className="label__lg" style = {{ align: "left"}}>
            Size
        </label><br/>
        <input style = {{width : "200px", height: "50px"}}
          type="text"
          id="new-todo-input"
          className="input input__lg"
          name="text"
          autoComplete="off"
          value={size}
          onChange={sizeChange}
        />

        <label htmlFor="new-todo-input" className="label__lg" >
            Price
        </label><br/>
        <input style = {{width : "200px", height: "50px"}}
          type="text"
          id="new-todo-input"
          className="input input__lg"
          name="text"
          autoComplete="off"
          value={price}
          onChange={priceChange}
        />

        <label htmlFor="new-todo-input" className="label__lg">
            Color
        </label><br/>
        <input style = {{width : "200px", height: "50px"}}
          type="text"
          id="new-todo-input"
          className="input input__lg"
          name="text"
          autoComplete="off"
          value={color}
          onChange={colorChange}
        />  
        <label htmlFor="new-todo-input" className="label__lg">Choose a category</label>
          <select id="" name="category" value={category} onChange={categoryChange}>
            <option value="pants">Pants</option>
            <option value="skirts">Skirts</option>
            <option value="tops">Tops</option>
          </select><br/><br/><br/>

        <br/>

        <button type="submit" className="btn btn__primary btn__lg">
          Submit
        </button>
        
      </form>
   
      
    );
}

export default Form;