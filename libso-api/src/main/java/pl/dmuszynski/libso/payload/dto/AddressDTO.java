package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.AddressView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

@SuperBuilder
@NoArgsConstructor
public class AddressDTO extends AbstractDTO implements AddressView {

    private String country;

    private String location;

    private String zipCode;

    private String street;

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String getZipCode() {
        return this.zipCode;
    }

    @Override
    public String getStreet() {
        return this.street;
    }
}
