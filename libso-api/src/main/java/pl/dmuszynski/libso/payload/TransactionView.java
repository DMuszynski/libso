package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Transaction;
import pl.dmuszynski.libso.payload.dto.ProductDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Projection(name = "transactionView", types = Transaction.class)
public interface TransactionView extends EntityView {
    BigDecimal getTotalPrice();
    int getTransactionProductsAmount();
    LocalDateTime getTransactionDate();
    Long getUserId();
    Set<ProductDTO> getProducts();
}
