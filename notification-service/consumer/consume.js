const {Kafka} = require("kafkajs")
const {redisClient, writeEvent} = require("../redis/redis-client")

const clientId = process.env.CLIENTID
const brokers = [process.env.BOOTSTRAPSERVER]
const topic = process.env.TOPICNAME

const kafka = new Kafka({clientId, brokers})

const consumer = kafka.consumer({groupId: clientId})
//TODO add redis connecttion
const consume = async () => {
    console.log('redisClient',)
    await consumer.connect()
    await consumer.subscribe({topic})
    await consumer.run({
        eachMessage: ({message}) => {
            console.log("wtf ",message.toString())
            console.log("wtfvalue ",message.value.toString())
            console.log(`received message: ${message.value}`)
            writeEvent(JSON.parse(message.value))
        },
    })
}

module.exports = consume