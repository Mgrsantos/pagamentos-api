package pagamentos.api.pagamentos.api;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import java.math.BigDecimal;

@DynamoDbBean
public class Pagamento {

    private String id;
    private String pagadorId;
    private String beneficiarioId;
    private BigDecimal valor;
    private String status;
    private String criadoEm;

    public Pagamento() {}

    @DynamoDbPartitionKey
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPagadorId() { return pagadorId; }
    public void setPagadorId(String pagadorId) { this.pagadorId = pagadorId; }

    public String getBeneficiarioId() { return beneficiarioId; }
    public void setBeneficiarioId(String beneficiarioId) { this.beneficiarioId = beneficiarioId; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCriadoEm() { return criadoEm; }
    public void setCriadoEm(String criadoEm) { this.criadoEm = criadoEm; }
}