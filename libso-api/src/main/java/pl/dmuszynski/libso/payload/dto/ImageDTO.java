package pl.dmuszynski.libso.payload.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.dmuszynski.libso.payload.ImageView;

@SuperBuilder
@NoArgsConstructor
public class ImageDTO extends AbstractDTO implements ImageView {

    private String name;

    private String type;

    private byte[] picByte;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public byte[] getPicByte() {
        return this.picByte;
    }
}
