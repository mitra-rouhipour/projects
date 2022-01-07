const loggedReducer =(state = false, action)=>{
    switch(action.type){
        case 'Sign-in':
            return !state;
        default : 
            return state;
    }
};
export default loggedReducer;