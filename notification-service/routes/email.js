var express = require('express');
var router = express.Router();
var mailHandler = require('../scheduled-tasks/mail-sender')

/* GET users listing. */
router.post('/mail', mailHandler.sendMail)


module.exports = router;
