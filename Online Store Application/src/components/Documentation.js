function Documentation(props){
    return(
        <div>
            <h1>Title Of Application: </h1><br/>
            <h2>Cloth Store</h2>
            <h5> This is a front-end application using react libraries to add a 
                new product into the store base on its name, price, category, size 
                , and color. The main feauture of this application is a form to inser 
                the new data and add a product into the store by using Submit button.
                 Also each product is edditable by price by clicking Edit button and 
                also the client can delete the product by click on Delete button.
                Furtheremore there are two buttons, for increment and decrement the 
                counter by using redux as global variables.Also, the Clear All Task 
                button can clear the local storage. And finally there is a navBar by 
                using Routing on top of the application.</h5>
            <h3>The main features implemented in this app include:</h3>
            <ol>
                <li>Created Seven Components in React app and props between local variables and components</li>
                <li>Implemented event handlersfor interactivity by adding, deleting and editing an item</li>
                <li>Maked use of localStorage to persist some state</li>
                <li>Used global state by using Redux for implementing Increment and Decrement Buttons</li>
                <li>Created client-site routing for a single page</li>
                <li>Deployed the web app :https://m-cloth-store.netlify.app/</li>
            </ol>
            <h5>Note: This app  has a seperate folder to support its back-end using Node and Express packages.
                 The other parts has done in back-end side of the web application are:
            </h5>
            <ol>
                <li>Implemented a REST API using Express with the following with 4 GET, 1, POST,1 DELETE requests </li>
                <li>Connected the front-end React app to your Express backend using fetch to make HTTP requests.</li>
                <li>Maked use of localStorage to persist some state</li>
        
            </ol>

            
        </div>   
    );   
}

export default Documentation;