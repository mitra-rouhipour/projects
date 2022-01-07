import React from "react";
import { Link } from "react-router-dom";

function Navbar(){
    return(
        <nav>
            <Link to="/">Home</Link>
            <Link to="/About">About</Link>
            <Link to="/Documentation">Documentation</Link>
        </nav>
    );
}
export default Navbar;