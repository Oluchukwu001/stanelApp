package stanel.app.store.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import stanel.app.store.exception.BadRequestException;
import stanel.app.store.orderItem.OrderItem;
import stanel.app.store.cartItem.CartItem;
import stanel.app.store.orderItem.OrderitemRepository;
import stanel.app.store.orderRetundOrRefund.OrderReturnOrRefund;
import stanel.app.store.orderRetundOrRefund.OrderReturnOrRefundRepository;
import stanel.app.store.orderRetundOrRefund.OrderReturnOrRefundRequestDTO;
import stanel.app.store.product.Product;
import stanel.app.store.product.ProductRepository;
import stanel.app.store.store.Store;
import stanel.app.store.store.StoreRepository;
import stanel.app.store.util.ServiceUtil;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderitemRepository orderitemRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final OrderReturnOrRefundRepository orderReturnOrRefundRepository;

    @Transactional
    public Order createOrder(OrderRequestDTO requestDTO) {
        // get their Store ID
        Store store = ServiceUtil.findOrThrow(storeRepository, requestDTO.getStoreId(), "Store");
        // We get the cartItems from their store
        List<CartItem> cartItems = store.getCart().getCartItems();
        // We crate an Order
        Order order = new Order();
        // We convert the Cart items to order items
        List<OrderItem> orderItems = convertAllToOrderItems(cartItems);
        // We set the orderItems in the order to the newly converted items
        order.setOrderItems(orderItems);
        // Compute the total sum and total quantity of products
        order.setTotalQuantity(getTotalQuantity(cartItems));
        order.setTotalPrice(getTotalPrice(cartItems));
        // we Save the order
        Order savedOrder=orderRepository.save(order);
        cartItems.clear();
        return savedOrder;
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatusRequestDTO status) {
        Order order = fetchOrder(orderId);
        order.setStatus(status.status());
        return orderRepository.save(order);
    }

    @Transactional
    public Order cancelOrder(Long id) {

        Order order = fetchOrder(id);

        if (order.getStatus() == Status.DELIVERED) {
            throw new BadRequestException("Order can not be cancelled");
        }

        if (order.getStatus() == Status.CANCELLED) {
            throw new BadRequestException("Order has been cancelled already");
        }

        order.setStatus(Status.CANCELLED);

        return orderRepository.save(order);
    }

    @Transactional
    public OrderReturnOrRefund returnOrRefundOrder(OrderReturnOrRefundRequestDTO refundRequestDTO) {
        Order order = fetchOrder(refundRequestDTO.orderId());
        Store store = fetchStore(refundRequestDTO.storeId());
        OrderItem item = fetchOrderItem(refundRequestDTO.orderItemId());
        if (!store.getOrders().contains(order) || !order.getOrderItems().contains(item)) {
            throw new BadRequestException("Order does not belong to the store");
        }
        OrderReturnOrRefund returnOrRefund = createReturnOrRefund(refundRequestDTO.reasons(), order, store, item);
        return orderReturnOrRefundRepository.save(returnOrRefund);

    }

    @Transactional(readOnly = true)
    public Page<Order> getAllStoreOrderByStoreId(Long storeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAllOrderByStoreId(storeId, pageable);
    }

    private OrderReturnOrRefund createReturnOrRefund(String reason, Order order, Store store, OrderItem item) {
        OrderReturnOrRefund refund = new OrderReturnOrRefund();
        refund.setReason(reason);
        refund.setOrderItem(item);
        refund.setOrder(order);
        refund.setStore(store);
        return refund;

    }

    private Order fetchOrder(Long id) {
        return ServiceUtil.findOrThrow(orderRepository, id, "Order");
    }

    @SuppressWarnings("null")
    private Integer getTotalQuantity(List<CartItem> cartItems) {
        return cartItems.stream().map(el -> el.getQuantity()).reduce(0, Integer::sum);
    }

    @SuppressWarnings("null")
    private BigDecimal getTotalPrice(List<CartItem> cartItems) {
        return cartItems.stream().map(el -> el.getPrice().multiply(BigDecimal.valueOf(el.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Store fetchStore(Long storeId) {
        return ServiceUtil.findOrThrow(storeRepository, storeId, "Store");
    }

    private OrderItem convertToOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(cartItem.getPrice());
        Product product = ServiceUtil.findOrThrow(productRepository, cartItem.getProduct().getId(), "Product");
        orderItem.setProduct(product);
        return orderItem;
    }

    private List<OrderItem> convertAllToOrderItems(List<CartItem> cartItems) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            orderItems.add(convertToOrderItem(cartItem));
        }
        return orderItems;
    }

    private OrderItem fetchOrderItem(Long orderItemId) {
        return ServiceUtil.findOrThrow(orderitemRepository, orderItemId, "OrderItem");
    }

}
