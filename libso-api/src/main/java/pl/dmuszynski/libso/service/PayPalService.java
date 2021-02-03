package pl.dmuszynski.libso.service;

import com.paypal.base.rest.PayPalRESTException;
import pl.dmuszynski.libso.payload.dto.CartDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface PayPalService {
    Map<String, Object> createPayment(String sum) throws PayPalRESTException;
    Map<String, Object> completePayment(HttpServletRequest req, CartDTO cartDetails) throws PayPalRESTException;
}
