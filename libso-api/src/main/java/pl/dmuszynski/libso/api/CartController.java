package pl.dmuszynski.libso.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.libso.payload.CartView;
import pl.dmuszynski.libso.payload.dto.CartDTO;
import pl.dmuszynski.libso.service.CartService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "libso/users/{userId}/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<CartView> findCartDtoByUserId(@PathVariable Long userId) {
        final CartDTO foundCartByUserId = this.cartService.findCartDtoByUserId(userId);
        return new ResponseEntity<>(foundCartByUserId, HttpStatus.OK);
    }

    @PatchMapping(value = "/{cartId}")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<HttpStatus> updateCart(@RequestBody CartDTO cartDetails, @PathVariable Long userId, @PathVariable Long cartId) {
        this.cartService.updateCart(cartDetails, cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
