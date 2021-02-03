package pl.dmuszynski.libso.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.libso.payload.dto.CartDTO;
import pl.dmuszynski.libso.payload.dto.TransactionDTO;
import pl.dmuszynski.libso.service.TransactionService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "libso/users/{userId}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Set<TransactionDTO>> findAllTransactionByUserId(@PathVariable Long userId) {
        final Set<TransactionDTO> foundAllTransactionById = this.transactionService.findAllTransactionByUserId(userId);
        if (!foundAllTransactionById.isEmpty())
            return new ResponseEntity<>(foundAllTransactionById, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
