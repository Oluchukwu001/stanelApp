package stanel.app.store.store;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface StoreMapper {
    StoreResponseDTO toStoreResponseDTO(Store store);
    List<StoreResponseDTO> toStoreResponseDTOs(List<Store> stores);

}
