# Etapa 1: Construir a aplicação Angular
FROM node:18-alpine AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos de dependências e instala-as
COPY package.json package-lock.json ./
RUN npm install

# Copia o restante dos arquivos e compila a aplicação
COPY . .
RUN npm run build --prod

# Etapa 2: Servir a aplicação com um servidor HTTP minimalista
FROM node:18-alpine

# Instala o servidor HTTP para servir a aplicação Angular
RUN npm install -g http-server

# Define o diretório de trabalho para a aplicação compilada
WORKDIR /app

# Copia os arquivos compilados da etapa de build
COPY --from=build /app/dist/customer-manager/browser .

# Exponha a porta 8080
EXPOSE 8080

# Inicia o servidor HTTP
CMD ["http-server", "-p", "8080", "-a", "0.0.0.0", "."]
