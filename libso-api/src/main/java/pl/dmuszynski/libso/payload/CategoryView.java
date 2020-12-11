package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Category;

import java.util.Set;

@Projection(name = "categoryView", types = Category.class)
public interface CategoryView extends EntityView {
    String getName();
    CategoryView getParent();
//    Set<CategoryView> getSubcategories();
}
