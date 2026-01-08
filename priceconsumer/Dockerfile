FROM node:18 AS builder

WORKDIR /app

COPY package.json package-lock.json* ./

RUN npm install --production

COPY . .

FROM node:18-alpine

WORKDIR /app

COPY --from=builder /app .

EXPOSE 5672

CMD ["node", "./consumer.js"]
