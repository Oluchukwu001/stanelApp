package stanel.app.store.company;

import java.util.List;

public record CompanyResponseDTO(
    Long id,
    String name,
    String email,
    String phoneNumber,
    String address,
    List<String> imageUrls,
    String logoUrl
){

}
