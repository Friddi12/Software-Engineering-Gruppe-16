FROM node:20-alpine
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

EXPOSE 5173
# --host kreves for at Vite skal godta forespørsler ut av Docker
CMD ["npm", "run", "dev", "--", "--host"]