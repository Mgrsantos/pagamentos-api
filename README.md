# Pagamentos API

API de pagamentos assíncrona construída com Spring Boot, AWS SQS e DynamoDB.

## O que esse projeto demonstra

- Comunicação assíncrona via filas SQS
- Persistência no DynamoDB com AWS Enhanced Client
- Padrão produtor/consumidor com `@SqsListener`
- Ambiente local com LocalStack (sem custo de conta AWS)

## Fluxo
## Stack

- Java 17
- Spring Boot 3.5
- AWS SQS (LocalStack)
- AWS DynamoDB (LocalStack)
- Docker

## Como rodar localmente

```bash
# Sobe o LocalStack
docker compose up -d

# Cria a fila e a tabela
docker exec pagamentosapi-localstack-1 awslocal sqs create-queue --queue-name pagamentos
docker exec pagamentosapi-localstack-1 awslocal dynamodb create-table \
  --table-name pagamentos \
  --attribute-definitions AttributeName=id,AttributeType=S \
  --key-schema AttributeName=id,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST

# Roda a aplicação e envia um pagamento
curl -X POST http://localhost:8080/pagamentos \
  -H "Content-Type: application/json" \
  -d '{"pagadorId":"user-1","beneficiarioId":"user-2","valor":150.00}'
```
