const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
require('dotenv').config()

const consume = require("./consumer/consume")
const job = require("./scheduled-tasks/mail-sender")
job();

var app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'pulic')));

consume().catch((err) => {
    console.error("error in consumer: ", err)
})

module.exports = app;
