package stanel.app.store.store;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import stanel.app.store.order.OrderResponseDTO;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final StoreMapper storeMapper;

    // CREATE STORE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreResponseDTO createStore(
            @RequestBody StoreRequestDTO requestDTO) {

        return storeMapper.toStoreResponseDTO(
                storeService.createStore(requestDTO));
    }

    // UPDATE STORE
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreResponseDTO updateStore(
            @PathVariable Long id,
            @RequestBody StoreRequestDTO requestDTO) {

        return storeMapper.toStoreResponseDTO(
                storeService.updateStore(id, requestDTO));
    }

    // GET STORE BY ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StoreResponseDTO getStoreById(
            @PathVariable Long id) {

        return storeMapper.toStoreResponseDTO(
                storeService.getStoreById(id));
    }

    // GET ALL STORES WITH PAGINATION
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StoreResponseDTO> getAllStores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return storeService
                .getAllStores(page, size)
                .map(storeMapper::toStoreResponseDTO);
    }

    // DELETE STORE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStore(
            @PathVariable Long id) {

        return storeService.deleteStoreById(id);
    }

    // GET ALL ORDER BELONG TO A PARTICULAR STORE BY PASSING STORE ID 
    public Page<OrderResponseDTO> getAllOrdersByStoreId(
        @RequestParam Long id,
        @RequestParam (defaultValue = "0") int page,
        @RequestParam  (defaultValue = "10") int size
    ){
        return storeService.getAllStoreOrdersByStoreId(id, page,size);
    }
}
