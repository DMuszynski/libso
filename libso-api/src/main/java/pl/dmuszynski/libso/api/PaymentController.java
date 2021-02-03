package pl.dmuszynski.libso.api;

import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.libso.payload.dto.CartDTO;
import pl.dmuszynski.libso.service.PayPalService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "libso/payments/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    private final PayPalService payPalService;

    @GetMapping
    public Map<String, Object> getCreatePaymentRequest(@RequestParam("total-price") String totalPrice) throws PayPalRESTException {
        return payPalService.createPayment(totalPrice);
    }

    @PostMapping
    public Map<String, Object> postCompletePaymentRequest(HttpServletRequest request, @RequestBody CartDTO cartDetails) throws PayPalRESTException {
        return payPalService.completePayment(request, cartDetails);
    }
}
