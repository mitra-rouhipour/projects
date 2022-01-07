import React, { useEffect, useState } from "react";

function Clothes(props) {
  //add a useState for the editing the price
  const [isEditing, setEditing] = useState(false);

  //add a useState hook for editing the price
  const [newPrice, setNewPrice] = useState('');

  function handleChange(e) {
    setNewPrice(e.target.value);
  }

  function handleSubmit(e) {
    e.preventDefault();
    props.editPrice(props.id, newPrice);
    setNewPrice("");
    setEditing(false);
  }
  const editingTemplate = (
    <form className="stack-small" onSubmit={handleSubmit}>
      <div className="form-group">
        <label className="todo-label" htmlFor={props.id}>
          New price for {props.name}
        </label>
        <input
          id={props.id}
          className="todo-text"
          type="text"
          value={newPrice}
          onChange={handleChange}
        />
      </div>
      <div className="btn-group">
      <button
        type="button"
        className="btn todo-cancel"
        onClick={() => setEditing(false)}
      >
        Cancel
        <span className="visually-hidden">change the price of {props.name}</span>
      </button>
        <button type="submit" className="btn btn__primary todo-edit">
          Save
          <span className="visually-hidden">new price for {props.name}</span>
        </button>
      </div>
    </form>
  );
  const viewTemplate = (
    <div className="stack-small">
      <div>
          <label className="todo-label" htmlFor={props.id}>
              Name : {props.name}<br/>
              Category : {props.category}<br/>
              Size: {props.size}<br/>
              Price: ${props.price}<br/>
              Color: {props.color}
          </label>
        </div>
        <div className="btn-group">
        <button type="button" className="btn" onClick={() => setEditing(true)}>
          Edit <span className="visually-hidden">{props.name}</span>
        </button>
          <button
            type="button"
            className="btn btn__danger"
            onClick={() => props.deleteCloth(props.id)}
          >
            Delete <span className="visually-hidden">{props.name}</span>
          </button>
        </div>
    </div>
  );

  return <li className="todo">{isEditing ? editingTemplate : viewTemplate}</li>;


}

export default Clothes;