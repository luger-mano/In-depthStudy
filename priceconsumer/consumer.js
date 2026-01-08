const amqp = require('amqplib')

const queue = process.env.RABBITMQ_QUEUE || 'PRICE'

amqp.connect({
    hostname: process.env.RABBITMQ_HOST || 'localhost',
    port: process.env.RABBITMQ_PORT || 5672,
    username: process.env.RABBITMQ_USERNAME || 'admin',
    password: process.env.RABBITMQ_PASSWORD || 12345
})
    .then((connection) => {
        connection.createChannel()
                .then((channel) => {
                    channel.consume(queue, (message) => {
                       console.log(message.content.toString())
                    }, {noAck: true})
                })
                .catch((err) => console.log(err))
    })
    .catch((err) => console.log(err))