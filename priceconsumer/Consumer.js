const amqp = require('amqplib')

const queue = 'PRICE'

amqp.connect({
    host: 'localhost',
    port: 5672,
    username: 'admin',
    password: 12345
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