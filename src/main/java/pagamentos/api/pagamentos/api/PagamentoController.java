package pagamentos.api.pagamentos.api;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final SqsTemplate sqsTemplate;

    public PagamentoController(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    @PostMapping
    public ResponseEntity<String> criarPagamento(@RequestBody PagamentoRequest request) {
        // Publica na fila — não processa aqui, só envia
        sqsTemplate.send("pagamentos", request);
        // Retorna 202 Accepted — recebeu mas ainda não processou
        return ResponseEntity.accepted().body("Pagamento recebido, processando...");
    }
}