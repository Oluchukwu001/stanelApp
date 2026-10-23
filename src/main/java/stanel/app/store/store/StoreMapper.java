package stanel.app.store.store;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface StoreMapper {
    @Mapping (source = "storeOwner.id",target = "storeOwnerId")
    StoreResponseDTO toStoreResponseDTO(Store store);
    List<StoreResponseDTO> toStoreResponseDTOs(List<Store> stores);

}
