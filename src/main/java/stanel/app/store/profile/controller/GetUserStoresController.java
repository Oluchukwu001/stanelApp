package stanel.app.store.profile.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import stanel.app.store.profile.services.GetUserStores;
import stanel.app.store.store.StoreMapper;
import stanel.app.store.store.StoreResponseDTO;
import stanel.app.store.user.dtos.Response;

@RestController
@RequestMapping("/api/v1/profiles")
public class GetUserStoresController {
    private final StoreMapper storeMapper;

    private final GetUserStores getUserStores;
    

    public GetUserStoresController(StoreMapper storeMapper, GetUserStores getUserStores) {
        this.storeMapper = storeMapper;
        this.getUserStores = getUserStores;
    }


    @GetMapping("/{id}/stores")
    @ResponseStatus(HttpStatus.OK)
    public Response getUserStores(@PathVariable Long id) {

        List<StoreResponseDTO> stores = storeMapper.toStoreResponseDTOs(getUserStores.getUserStore(id));

        return new Response(stores, "User stores retrieved successfully");
    }
}
