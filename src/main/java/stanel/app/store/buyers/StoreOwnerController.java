package stanel.app.store.buyers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store-owner")
public class StoreOwnerController {

    private final StoreOwnerService service;
    private final StoreOwnerMapper storeOwnerMapper;

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreOwnerResponseDTO createStoreOwner(
            @RequestBody StoreOwnerRequestDTO requestDTO) {

        StoreOwner storeOwner = service.createStoreOwner(requestDTO);

        return storeOwnerMapper.toStoreOwnerResponseDTO(storeOwner);
    }

    // GET BY ID
    @GetMapping("/{id}")
    @ResponseStatus (HttpStatus.OK)
    public StoreOwnerResponseDTO getStoreOwnerById(
            @PathVariable Long id) {

        StoreOwner storeOwner = service.getStoreOwnerById(id);

        return storeOwnerMapper.toStoreOwnerResponseDTO(storeOwner);
    }

    // GET ALL WITH PAGINATION
    @GetMapping
    @ResponseStatus (HttpStatus.OK)
    public org.springframework.data.domain.Page<StoreOwnerResponseDTO> getAllStoreOwner(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return service.getAllStoreOwner(page, size)
                .map(storeOwnerMapper::toStoreOwnerResponseDTO);
    }

    // UPDATE
    @PutMapping("/{id}")
    @ResponseStatus (HttpStatus.OK)
    public StoreOwnerResponseDTO updateStoreOwner(
            @PathVariable Long id,
            @RequestBody StoreOwnerRequestDTO requestDTO) {

        StoreOwner storeOwner = service.updateStoreOwner(id, requestDTO);

        return storeOwnerMapper.toStoreOwnerResponseDTO(storeOwner);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStoreOwnerById(
            @PathVariable Long id) {

        return service.deleteStoreOwnerById(id);
    }
}