package pagamentos.api.pagamentos.api;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class PagamentoConsumer {

    private static final Logger log = LoggerFactory.getLogger(PagamentoConsumer.class);

    private final DynamoDbTable<Pagamento> tabela;

    public PagamentoConsumer(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.tabela = dynamoDbEnhancedClient.table("pagamentos", TableSchema.fromBean(Pagamento.class));
    }

    @SqsListener("pagamentos")
    public void processar(PagamentoRequest request) {

        log.info("Processando pagamento: {} -> {} | Valor: {}",
                request.pagadorId(), request.beneficiarioId(), request.valor());

        try {
            Pagamento pagamento = new Pagamento();
            pagamento.setId(UUID.randomUUID().toString());
            pagamento.setPagadorId(request.pagadorId());
            pagamento.setBeneficiarioId(request.beneficiarioId());
            pagamento.setValor(request.valor());
            pagamento.setStatus("PROCESSADO");
            pagamento.setCriadoEm(LocalDateTime.now().toString());

            tabela.putItem(pagamento);

            log.info("Pagamento salvo no DynamoDB com id: {}", pagamento.getId());

        } catch (Exception e) {
            log.error("Erro ao salvar no DynamoDB: {}", e.getMessage(), e);
        }
    }
}