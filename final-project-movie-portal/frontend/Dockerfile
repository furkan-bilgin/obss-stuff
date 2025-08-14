FROM oven/bun:alpine AS builder

WORKDIR /app

COPY package.json ./
COPY bun.lock ./

RUN bun i

COPY . .

RUN bun run build

FROM nginx:alpine AS production

COPY --from=builder /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
