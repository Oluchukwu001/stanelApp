package stanel.app.store.util;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import stanel.app.store.exception.BadRequestException;
import stanel.app.store.product.Product;
import stanel.app.store.product.ProductRepository;
import stanel.app.store.store.Store;
import stanel.app.store.store.StoreRepository;

@Component
@RequiredArgsConstructor
public class Resolver {
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public Store resolveStore(Long storeId) {
        if (storeId == null)
            throw new BadRequestException("Store Id is required");
        return storeRepository.findById(storeId).orElseThrow(() -> new BadRequestException("Store not found"));
    }

    public Product resolveProduct(Long productId) {
        if (productId == null)
            throw new BadRequestException("Product ID is required");
        return productRepository.findById(productId).orElseThrow(() -> new BadRequestException("Product ID not found"));
    }

}
