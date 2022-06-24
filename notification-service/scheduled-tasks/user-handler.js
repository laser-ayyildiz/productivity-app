const axios = require('axios')
const config = {
    method: 'post',
    url: 'http://localhost:8080/user-service',
}

exports.axiosClient = () => {
    return axios.create(config)
}

exports.getUser = (id) => {

    return axios.get(`${config.url}/user/${id}`, {
        headers: {
            "Authorization": `Bearer ${process.env.ADMIN_TOKEN}`
        }
    })
}
