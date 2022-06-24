const axios = require('axios')


const config = {
    method: 'post',
    url: 'http://localhost:8080/todo-service',
}

exports.axiosClient = () => {
    return axios.create(config)
}

exports.getTask = (id) => {

    return axios.get(`${config.url}/task/${id}`, {
        headers: {
            "Authorization": `Bearer ${process.env.ADMIN_TOKEN}`
        }
    })
}
