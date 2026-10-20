package stanel.app.store.product;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    // CREATE PRODUCT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO requestDTO) {
        return mapper.toProductResponseDTO(productService.createProduct(requestDTO));
    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO requestDTO) {

        return mapper.toProductResponseDTO(productService.updateProduct(id, requestDTO));
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return mapper.toProductResponseDTO(productService.getProductById(id));
    }

    // GET ALL PRODUCTS WITH PAGINATION
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponseDTO> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return productService.getAllProduct(page, size).map(el -> mapper.toProductResponseDTO(el));
    }

    // COUNT AND GROUP PRODUCTS BY NAME AND STATUS
    @GetMapping("/count")
    public List<ProductDTOCount> countAllProductByStatusAndGroupByName(
            @RequestParam ProductStatus status) {

        return productService.countAllProductByStatusAndGroupByName(status);
    }
}