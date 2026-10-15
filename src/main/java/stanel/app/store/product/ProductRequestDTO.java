package stanel.app.store.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequestDTO(
        String name,
        String size,
        Integer quantity,
        ProductStatus status,
        BigDecimal price,
        List<String> imageUrl,
        String description) {

}
