var createError = require('http-errors');
var express = require('express');
var path = require('path');// for parsing file and directory path
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var catalogRouter = require('./routes/catalog');  //Import routes for "catalog" area of site

var indexRouter = require('./routes/index');// these two import mouduls from related "routes"(URL path)
var usersRouter = require('./routes/users');



var app = express(); //create an application object

///////////////In this part we are connecting to MangoDB//////////
//Require Mongoose & Set up mongoose connection
var mongoose = require('mongoose');

//Set up default mongoose connection
var mongoDB = 'mongodb+srv://mitra:Mitra19**@cluster0.xiqc1.mongodb.net/local_library?retryWrites=true&w=majority';
mongoose.connect(mongoDB, { useNewUrlParser: true , useUnifiedTopology: true});

//Get the default connection
var db = mongoose.connection;

//Bind connection to error event (to get notification of connection errors)
db.on('error', console.error.bind(console, 'MongoDB connection error:'));

//Author model:

// view engine setup to set whete the templates will be stored.
//app.set(name, value) is used to assign the setting value to value. you can use any name you want but only some
// name can be used to configure the behaviour of the server
//path.join() return the sequence of the path by sequence as an string 

//we assign the "views" to /views
app.set('views', path.join(__dirname, 'views'));

// we assign "view engine" to pug
app.set('view engine', 'pug');

//app.use(path, callback)  is used to mount the specified middleware function(s) 
//at the path which is being specified. It is mostly used to set up middleware 
//for your application.
//
//path: It is the path for which the middleware function is being called. 
//It can be a string representing a path or path pattern or regular expression pattern to match the paths.

//callback: It is a middleware function or a series/array of middleware functions.

//here we add middleWare libraries into the request handling chain
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/catalog', catalogRouter);  // Add catalog routes to middleware chain.

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
