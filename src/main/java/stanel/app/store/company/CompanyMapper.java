package stanel.app.store.company;



import org.mapstruct.Mapper;




import stanel.app.store.store.StoreMapper;

@Mapper (componentModel = "spring",uses ={ StoreMapper.class,

})
public interface CompanyMapper {
    
    CompanyResponseDTO toDto(Company company);
}
