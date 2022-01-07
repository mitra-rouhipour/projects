import {Route,Switch} from 'react-router-dom';
import React, { useEffect,useState } from "react";
import Home from './components/Home';
import About from './components/About';
import Navbar from './components/Navbar';
import Error from './components/Error';
import Documentation from './components/Documentation';

import {useSelector, useDispatch} from 'react-redux';
import{increment, decrement} from './action';



function App(props) {
  return(
    <div className='todoapp stack-large'>
      <Navbar/>
      <Switch>
        <Route path="/" component={Home} exact/>
        <Route path="/about" component={About} />
        <Route path="/Documentation" component={Documentation} />
        <Route component={Error} />
      </Switch>
    </div>
  );
}

export default App;
