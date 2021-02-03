package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.model.Transaction;
import pl.dmuszynski.libso.payload.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.Set;

public interface TransactionService {
    Transaction createTransaction(Long userId, BigDecimal totalPrice, int transactionProductsAmount);
    Set<TransactionDTO> findAllTransactionByUserId(Long userId);
}
