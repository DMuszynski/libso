package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.AddressView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

@SuperBuilder
@NoArgsConstructor
public class AddressDTO extends AbstractDTO implements AddressView {

    private String country;

    private String province;

    private String location;

    private String zipCode;

    private String street;

    private String apartmentNumber;

    private boolean deliveryAddress;

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public String getProvince() {
        return this.province;
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

    @Override
    public String getApartmentNumber() {
        return this.apartmentNumber;
    }

    @Override
    public boolean isDeliveryAddress() {
        return this.deliveryAddress;
    }
}
