package pl.dmuszynski.libso.service.implementation;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.model.Offer;
import pl.dmuszynski.libso.model.Product;
import pl.dmuszynski.libso.payload.dto.CartDTO;
import pl.dmuszynski.libso.payload.dto.PurchaseDTO;
import pl.dmuszynski.libso.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service(value = "payPalService")
public class PayPalServiceImpl implements PayPalService {
    @Value("${paypal.clientId}")
    private String CLIENT_ID;
    @Value("${paypal.secret}")
    private String CLIENT_SECRET;

    private final CartService cartService;
    private final OfferService offerService;
    private final MessageService messageService;
    private final PurchaseService purchaseService;
    private final TransactionService transactionService;

    @Override
    public Map<String, Object> createPayment(String totalPrice) throws PayPalRESTException {
        final APIContext context = new APIContext(CLIENT_ID, CLIENT_SECRET, "sandbox");
        final Payment createdPayment = this.preparePayment(totalPrice).create(context);

        final Map<String, Object> response = new HashMap<>();
        if (createdPayment != null) {
            response.put("status", "success");
            response.put("redirect_url", this.getCompletePaymentUrl(createdPayment));
        }

        return response;
    }

    @Override
    @Transactional
    public Map<String, Object> completePayment(HttpServletRequest req, CartDTO cartDetails) throws PayPalRESTException {
        final APIContext context = new APIContext(CLIENT_ID, CLIENT_SECRET, "sandbox");
        final Payment executedPayment = this.executePayment(context, req.getParameter("paymentId"),
            req.getParameter("PayerID"));

        final Map<String, Object> response = new HashMap<>();
        if (executedPayment != null) {
            final pl.dmuszynski.libso.model.Transaction createdTransaction = this.transactionService
                .createTransaction(cartDetails.getId(), cartDetails.getTotalPrice(), cartDetails.getCapacity());

            final Set<Product> transactionProducts = new HashSet<>();
            for (PurchaseDTO purchase: cartDetails.getPurchases()) {
                Offer updatedOffer = this.offerService.updatePurchaseOffer(purchase);
                transactionProducts.add(updatedOffer.getProduct());
            }
            createdTransaction.setProducts(transactionProducts);

            this.sendTransactConfirmation(createdTransaction);
            this.purchaseService.deleteAllByCartId(cartDetails.getId());
            this.cartService.clearCart(cartDetails.getId());

            response.put("status", "success");
        }

        return response;
    }

    private Payment preparePayment(String totalPrice) {
        final Amount amount = new Amount("PLN", totalPrice);
        final List<Transaction> transactions = new ArrayList<>();

        final Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Realizacja transakcji na platformie Libso");
        transactions.add(transaction);

        final Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        final RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:4200/libos/cancel");
        redirectUrls.setReturnUrl("http://localhost:4200/libso/cart");

        final Payment payment = new Payment("sale", payer);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);

        return payment;
    }

    private Payment executePayment(APIContext context, String paymentId, String payerId) throws PayPalRESTException {
        final Payment payment = new Payment();
        final PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        payment.setId(paymentId);

        return payment.execute(context, paymentExecution);
    }

    private String getCompletePaymentUrl(Payment createdPayment) {
        final List<Links> links = createdPayment.getLinks();
        for (Links link: links)
            if (link.getRel().equals("approval_url"))
                return link.getHref();

         return "";
    }

    private void sendTransactConfirmation(pl.dmuszynski.libso.model.Transaction transaction) {
        final String mailSubject = "Potwierdzenie transakcji na platformie Libso";
        final String mailContent = "Właśnie przyjęliśmy Twoje nowe zamówienie o numerze " + transaction.getId() + ". \n"
            + "Rozpoczniemy jego kompletację oraz wyślemy produkty na wybrany przez ciebie adres dostawy. \n"
            + "Informacje na temat transakcji znajdują się na twoim koncie pod poniższym adresem: \n"
            + "http://localhost:4200/account/my-shopping";

        this.messageService.sendMessage(transaction.getUser().getEmail(), mailSubject, mailContent, true);
    }
}
