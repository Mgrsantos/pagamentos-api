package pagamentos.api.pagamentos.api;

import java.math.BigDecimal;

public record PagamentoRequest(
        String pagadorId,
        String beneficiarioId,
        BigDecimal valor
) {}