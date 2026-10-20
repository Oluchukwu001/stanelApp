package stanel.app.store.profile.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import stanel.app.store.profile.dto.ProfileResponseDTO;
import stanel.app.store.profile.services.GetStoreOwners;
import stanel.app.store.user.dtos.Response;

@RestController
@RequestMapping("/api/v1/storeOwner-profiles")
public class GetStoreOwnersController {

    private final GetStoreOwners getStoreOwners;
    

    public GetStoreOwnersController(GetStoreOwners getStoreOwners) {
        this.getStoreOwners = getStoreOwners;
    }


    @GetMapping("/store-owners")
    @ResponseStatus(HttpStatus.OK)
    public Response getStoreOwners() {

        List<ProfileResponseDTO> storeOwners = ProfileResponseDTO.mapToDtos(getStoreOwners.execute());

        return new Response(
                storeOwners,
                "Store owners retrieved successfully");
    }
}
