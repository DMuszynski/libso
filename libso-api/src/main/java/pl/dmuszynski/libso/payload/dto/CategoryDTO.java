package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.CategoryView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

@SuperBuilder
@NoArgsConstructor
public class CategoryDTO extends AbstractDTO implements CategoryView {

    private String name;

    private CategoryDTO parent;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CategoryView getParent() {
        return this.parent;
    }
}
