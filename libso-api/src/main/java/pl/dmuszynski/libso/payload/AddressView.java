package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Address;

@Projection(name = "addressView", types = Address.class)
public interface AddressView extends EntityView {
    String getCountry();
    String getProvince();
    String getLocation();
    String getZipCode();
    String getStreet();
    String getApartmentNumber();
    boolean isDeliveryAddress();
}
