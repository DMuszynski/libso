package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.model.Cart;
import pl.dmuszynski.libso.model.User;
import pl.dmuszynski.libso.payload.dto.CartDTO;

public interface CartService {
    Cart clearCart(Long cartId);
    CartDTO findCartDtoByUserId(Long userId);
    void updateCart(CartDTO cartDetails, Long cartId);
    void createCart(User user);
}
