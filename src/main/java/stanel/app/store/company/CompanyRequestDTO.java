package stanel.app.store.company;

import java.util.List;

public record CompanyRequestDTO(
    String name,
    String logo,
    List<String> imageUrlList,
    String phoneNumber,
    String address,
    String email
) {
    
}
