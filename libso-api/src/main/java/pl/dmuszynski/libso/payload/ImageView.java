package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Image;

@Projection(name = "imageView", types = Image.class)
public interface ImageView extends EntityView {
    String getName();
    String getType();
    byte[] getPicByte();
}
