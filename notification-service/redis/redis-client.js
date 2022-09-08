const redis = require('redis');
const env = require('dotenv')
const client = redis.createClient({
    host: process.env.REDIS_HOST,
    port: process.env.REDIS_PORT,
    password: process.env.REDIS_PASSWORD,
});
client.connect()
    .then(r => console.log("bağlandım"))
    .catch(() => console.log("bağlanamıyorum"));
console.log("asdadsasdasd", client)
const {promisify} = require('util');
const getAsync = promisify(client.get).bind(client);

client.on('error', err => {
    console.log('Error ' + err);
});

async function saveWithTtl(key, value, ttlSeconds = 60) {
    return await setAsyncEx(key, ttlSeconds, JSON.stringify(value));
}

//db.hset('key', ...Object.entries({a: 'a', b: 'b'}), (err) => {
//   // ...
// });
const writeEvent = async (data = {}) => {
    console.log("data ", data)
    await client.hSet('user', data.id, JSON.stringify(data), err => {
        console.log(err);
    }).catch((err) => {
        console.log(err);
    });
}

async function get(key) {
    const jsonString = await getAsync(key);

    if (jsonString) {
        return JSON.parse(jsonString);
    }
}

const hgetAll = async () => {
    const data = await client.hGetAll("user")
    return Object.values(data);
}

const remove = async (key) => {
    await client.del(key);
}

module.exports = {
    saveWithTtl,
    get,
    writeEvent,
    hgetAll,
    remove
}

