# Microservices com RabbitMQ + Spring Boot

Este projeto implementa uma arquitetura de **microsserviços orientada a eventos** utilizando **Spring Boot** e **RabbitMQ** para comunicação assíncrona entre serviços.

## 📁 Estrutura do Projeto

O repositório contém dois microsserviços principais:

```
Microservices-RabbitMQ-Spring-Boot
│
├── ms_user
│   └── Responsável por criar usuários e publicar eventos
│
├── ms_email
│   └── Responsável por consumir eventos e enviar e-mails
```

Cada microsserviço é uma aplicação Spring Boot independente, com sua própria estrutura interna e dependências.

---

## 🧩 Microsserviço: ms_user

Responsabilidades:

* Criar e gerenciar usuários
* Publicar eventos no RabbitMQ quando um usuário é criado

Estrutura interna:

* `controllers` → Endpoints REST
* `services` → Regras de negócio
* `producers` → Envio de mensagens para RabbitMQ
* `repositories` → Acesso ao banco de dados
* `dtos` → Objetos de transferência
* `models` → Entidades
* `configs` → Configurações do RabbitMQ

## 📧 Microsserviço: ms_email

Responsabilidades:

* Consumir eventos do RabbitMQ
* Simular ou executar envio de e-mails

Estrutura interna:

* `consumers` → Listener do RabbitMQ
* `services` → Serviço de envio de e-mail
* `dtos` → Dados recebidos
* `models` → Entidades
* `repositories` → Persistência do log de e-mail
* `configs` → Configurações do RabbitMQ

Ao receber, ele dispara o envio do e-mail.

---

## 🔄 Fluxo de Comunicação

1. O cliente chama o **ms_user** via REST
2. O **ms_user** cria o usuário no banco
3. O **ms_user** envia mensagem para o **RabbitMQ**
4. O **ms_email** consome a mensagem da fila
5. O **ms_email** envia o e-mail

Representação:

```
[ Client ] → [ ms_user ] → RabbitMQ → [ ms_email ]
```

---

## ⚙️ Pré-requisitos

Para rodar o projeto corretamente, você precisa ter:

* RabbitMQ instalado e rodando localmente
* Java 17 ou superior
* Maven


Se você tiver o painel de administração habilitado:

```
http://localhost:15672
```

---

## ⚙️ Configuração do application.properties

Em ambos os serviços:

```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

---

## ▶️ Como Rodar o Projeto

1. Certifique-se de que o **RabbitMQ está em execução localmente**
2. Inicie primeiro o **ms_email**
3. Em seguida, inicie o **ms_user**
4. Faça uma requisição POST para criar um usuário

Exemplo:

```
POST http://localhost:8080/users
```

Body (exemplo):

```json
{
  "name": "João Silva",
  "email": "joao@email.com"
}
```
---

## ✅ Tecnologias Utilizadas

* Java 17+
* Spring Boot
* RabbitMQ
* Maven
* JPA / Hibernate
* REST API

---

## 🚀 Possíveis melhorias futuras

* Implementar Dead Letter Queue (DLQ)
* Adicionar Retry automático
* Implementar Notification-Service
* Adicionar Spring Cloud Config
* Criar um API Gateway

---

## 👨‍💻 Autor

Desenvolvido por **Nunes**
Projeto focado em estudo de microsserviços e mensageria assíncrona.
