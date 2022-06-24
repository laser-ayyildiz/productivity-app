const nodeCron = require("node-cron");
const redis = require("./../redis/redis-client")
const {hgetAll, remove} = require("../redis/redis-client");
const {log} = require("debug");
const nodemailer = require("nodemailer");
const taskHandler= require("./task-handler");
const userHandler= require("./user-handler");

const firstJob = () => {
    // "0 0 */3 * * *"  //for every hour;
    nodeCron.schedule("*/10 * * * * *", async function mailScheduler() {
        const whereDate = getTomorrowEpoch();
        const todos = await hgetAll();
        for (const data of todos) {
            let parsedData = JSON.parse(data);
            console.log("deadline: ", parsedData.deadline)
            console.log("query: ", whereDate)


            if (parsedData.deadline < whereDate) {
                const task = await taskHandler.getTask(parsedData.id).then(data => data.data);
                const user = await userHandler.getUser(parsedData.userId).then(data => data.data);
                const mailOptions = {
                    from: "notification@productivityApp.com",
                    to: user.email, // list of receivers
                    subject: `todo deadline bitiyor haberin olsun. Todo: title: ${task.title}`, // Subject line
                    html: `<b>todo deadline bitiyor haberin olsun. Todo: title: ${task.title},
                     description: ${task.description},
                     createdDate: ${task.createdDate}</b>`, // Subject line // plain text body // html body
                };
                await mailSender(mailOptions).then(() => {
                    remove(parsedData.id);
                }).catch((err) => {
                    console.log("mail gönderilemedi: ", err);
                });
            }
        }
        console.log("çalıştım şu saatte: ", new Date().toLocaleString());
    });
}

const transport = nodemailer.createTransport({
    host: "smtp.mailtrap.io",
    port: 2525,
    auth: {
        user: "937e3f2f2e8130",
        pass: "e9de344984e84b"
    }
});

const mailSender = async (mailOptions) => {
    await transport.sendMail(mailOptions, (error, info) => {
        if (error) {
            return console.log(error);
        }
        console.log('Message %s sent: %s', info.messageId);
    });
}

const getTomorrowEpoch = () => {
    let today = new Date();
    let tomorrow = new Date();
    tomorrow.setDate(today.getDate() + 1);
    return Math.floor(tomorrow.getTime());
}

module.exports = firstJob