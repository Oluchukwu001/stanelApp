package stanel.app.store.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stanel.app.store.util.ServiceUtil;
import stanel.app.store.util.ValidationUtil;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(ProductRequestDTO requestDTO) {
        validateProductInput(requestDTO);
        Product product = new Product();
        product.setName(requestDTO.name());
        product.setImageUrls(requestDTO.imageUrl());
        product.setDescription(requestDTO.description());
        product.setPrice(requestDTO.price());
        product.setQuantity(requestDTO.quantity());
        product.setSize(requestDTO.size());
        product.setStatus(requestDTO.status());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequestDTO requestDTO) {
        ValidationUtil.requirePositive(id, "Product id must positive");
        Product product = ServiceUtil.findOrThrow(productRepository, id, "Product");
        ServiceUtil.setIfPresent(requestDTO.name(), product::setName);
        ServiceUtil.setIfPresent(requestDTO.description(), product::setDescription);
        ServiceUtil.setIfPresent(requestDTO.price(), product::setPrice);
        ServiceUtil.setIfPresent(requestDTO.quantity(), product::setQuantity);
        ServiceUtil.setIfPresent(requestDTO.imageUrl(), product::setImageUrls);
        ServiceUtil.setIfPresent(requestDTO.size(), product::setSize);
        ServiceUtil.setIfPresent(requestDTO.status(), product::setStatus);
        return productRepository.save(product);
    }

    public String deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            return "Product with id " + id + " not found";
        }
        productRepository.deleteById(id);
        return "Product with id " + id + " was successfully deleted";
    }

    public Product getProductById(Long id) {
        return ServiceUtil.findOrThrow(productRepository, id, "Product");
    }

    public Page<Product> getAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    // TO COUNT AND GROUP AVAILABLE PRODUCT BY NAME
    public List<ProductDTOCount> countAllProductByStatusAndGroupByName(ProductStatus status) {
        List<Object[]> objects = productRepository.countProductsByNameAndStatus(status);
        return objects.stream().map(el -> new ProductDTOCount((String) el[0], (Long) el[1]))
                .toList();
    }

    // TO VALIDATE PRODUCT REQUEST DTO
    private void validateProductInput(ProductRequestDTO requestDTO) {
        ValidationUtil.requireNonBlank(requestDTO.name(), "Product name is required");
        ValidationUtil.requireNonBlank(requestDTO.size(), "Product size is required");
        ValidationUtil.requireNotEmpty(requestDTO.imageUrl(), "Product Images are required");
        ValidationUtil.requirePositive(requestDTO.quantity(), "Product quantity must be positive number");
        ValidationUtil.requirePositive(requestDTO.price(), "Product price must positive");
        ValidationUtil.requireNonNull(requestDTO.status(), "Product status is required");
        ValidationUtil.requireNonBlank(requestDTO.description(), "Product description is required");
    }

}
