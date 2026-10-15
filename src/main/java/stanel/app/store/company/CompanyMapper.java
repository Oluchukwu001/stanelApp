package stanel.app.store.company;

import java.util.List;

import org.mapstruct.Mapper;

import stanel.app.store.buyers.StoreOwner;
import stanel.app.store.buyers.StoreOwnerMapper;
import stanel.app.store.buyers.StoreOwnerResponseDTO;
import stanel.app.store.staff.Staff;
import stanel.app.store.staff.StaffMapper;
import stanel.app.store.staff.StaffResponseDTO;
import stanel.app.store.store.StoreMapper;

@Mapper (componentModel = "spring",uses ={ StoreMapper.class,
    StaffMapper.class,
    StoreOwnerMapper.class
})
public interface CompanyMapper {
    
    CompanyResponseDTO toDto(Company company);
    List<StaffResponseDTO> toStaffResponseDTO(List<Staff> staffs);
    List<StoreOwnerResponseDTO> toStoreOwners(List<StoreOwner> storeOwners);
}
