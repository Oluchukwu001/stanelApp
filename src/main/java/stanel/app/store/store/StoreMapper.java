package stanel.app.store.store;

import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface StoreMapper {
    StoreResponseDTO toStoreResponseDTO(Store store);

}
