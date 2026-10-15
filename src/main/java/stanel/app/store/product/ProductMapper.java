package stanel.app.store.product;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface ProductMapper {
    @Mapping (source = "product.imageUrls", target = "imageUrl")
    @Mapping (source = "product.cartItem.id", target = "cartItemId")
    ProductResponseDTO toProductResponseDTO(Product product);
    List<ProductResponseDTO> tProductResponseDTOs(List<Product> products);

}
