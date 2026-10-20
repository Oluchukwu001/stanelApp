package stanel.app.store.order;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import stanel.app.store.orderRetundOrRefund.OrderReturnOrRefund;
import stanel.app.store.orderRetundOrRefund.OrderReturnOrRefundRequestDTO;

@RestController
@RequestMapping("/api/v1/orders")

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO createOrder(
            @RequestBody OrderRequestDTO requestDTO) {

        Order order = orderService.createOrder(requestDTO);

        return OrderResponseDTO.mapToDTO(order);
    }

    @PatchMapping("/{orderId}/status")
    public OrderResponseDTO updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody OrderStatusRequestDTO requestDTO) {

        Order order = orderService.updateOrderStatus(
                orderId,
                requestDTO);

        return OrderResponseDTO.mapToDTO(order);
    }

    @PatchMapping("/{orderId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDTO cancelOrder(
            @PathVariable Long orderId) {
        Order order = orderService.cancelOrder(orderId);
        return OrderResponseDTO.mapToDTO(order);
    }

    @PostMapping("/return-refund")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderReturnOrRefund returnOrRefundOrder(
            @RequestBody OrderReturnOrRefundRequestDTO requestDTO) {

        return orderService.returnOrRefundOrder(requestDTO);
    }

    @GetMapping("/store_orders")
    public Page<OrderResponseDTO> getAllStoreOrderByStoreId(
            @RequestParam Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return orderService.getAllStoreOrderByStoreId(storeId, page, size).map(OrderResponseDTO::mapToDTO);

    }
}
