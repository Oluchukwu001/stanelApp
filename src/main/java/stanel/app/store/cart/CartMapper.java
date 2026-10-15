package stanel.app.store.cart;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@Mapper (sou)

public interface CartMapper {

    CartDTO toCartDTO(Cart cart);

}
