package stanel.app.store.buyers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import stanel.app.store.store.StoreMapper;

@Mapper(
    componentModel = "spring",
    uses = StoreMapper.class
)
public interface StoreOwnerMapper {
    @Mapping (source = "storeOwner.stores",target = "storeResponseDTos")
    StoreOwnerResponseDTO toStoreOwnerResponseDTO(StoreOwner storeOwner);
}