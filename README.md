# Pagamentos API

API de pagamentos assíncrona construída com Spring Boot, AWS SQS e DynamoDB.

## O que esse projeto demonstra

- Comunicação assíncrona via filas SQS
- Persistência no DynamoDB com AWS Enhanced Client
- Padrão produtor/consumidor com `@SqsListener`
- Ambiente local com LocalStack (sem custo de conta AWS)

## Fluxo
