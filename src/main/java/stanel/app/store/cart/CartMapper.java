package stanel.app.store.cart;

import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface CartMapper {

    CartDTO toCartDTO(Cart cart);
    
}
