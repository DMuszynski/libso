package pl.dmuszynski.libso.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import pl.dmuszynski.libso.service.OfferService;
import pl.dmuszynski.libso.payload.dto.OfferDTO;
import pl.dmuszynski.libso.payload.OfferView;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "libso/offers")
@CrossOrigin(origins = "http://localhost:4200")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<OfferView> createOffer(@RequestBody OfferDTO offerDetails) {
        final OfferView createdProductDto = this.offerService.createOffer(offerDetails);
        return new ResponseEntity<>(createdProductDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{offerId}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<OfferView> updateOfferById(@RequestBody OfferDTO offerDetails, @PathVariable Long offerId) {
        final OfferView updatedProductDto = this.offerService.updateOfferById(offerDetails, offerId);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{offerId}")
    public ResponseEntity<OfferDTO> findOfferById(@PathVariable Long offerId) {
        final OfferDTO foundOffer = this.offerService.findById(offerId);
        return new ResponseEntity<>(foundOffer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<OfferDTO>> findAllOfferDTO() {
        final Set<OfferDTO> foundOffersDtoSet = this.offerService.findAllOfferDTO();
        if(!foundOffersDtoSet.isEmpty())
            return new ResponseEntity<>(foundOffersDtoSet, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/categories/{categoryId}")
    public ResponseEntity<Set<OfferView>> findOffersByCategoryId(@PathVariable Long categoryId){
        return null;
    }

    @DeleteMapping(value = "/{offerId}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> deleteOfferById(@PathVariable Long offerId) {
        this.offerService.deleteOfferById(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
