const {Kafka} = require("kafkajs")

const clientId = process.env.CLIENTID
const brokers = [process.env.BOOTSTRAPSERVER]
const topic = process.env.TOPICNAME

const kafka = new Kafka({clientId, brokers})

const consumer = kafka.consumer({groupId: clientId})
//TODO add redis connecttion
const consume = async () => {

    await consumer.connect()
    await consumer.subscribe({topic})
    await consumer.run({
        eachMessage: ({message}) => {
            console.log(`received message: ${message.value}`)
        },
    })
}

module.exports = consume