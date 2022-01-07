var express = require('express');//get express moudule
var router = express.Router();//use express.router object

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

router.get('/cool', function(req,res){
  res.send('You\'r so cool')
});
module.exports = router;
