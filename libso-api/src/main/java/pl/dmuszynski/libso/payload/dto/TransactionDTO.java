package pl.dmuszynski.libso.payload.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.dmuszynski.libso.payload.TransactionView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
public class TransactionDTO extends AbstractDTO implements TransactionView {

    private Long userId;

    private BigDecimal totalPrice;

    private int transactionProductsAmount;

    private LocalDateTime transactionDate;

    private Set<ProductDTO> products;

    @Override
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    @Override
    public int getTransactionProductsAmount() {
        return this.transactionProductsAmount;
    }

    @Override
    public LocalDateTime getTransactionDate() {
        return this.transactionDate;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }

    @Override
    public Set<ProductDTO> getProducts() {
        return this.products;
    }
}
