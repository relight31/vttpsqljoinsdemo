package vttp.sqljoinsdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import vttp.sqljoinsdemo.model.PurchaseOrder;
import vttp.sqljoinsdemo.services.KwikService;

@SpringBootTest
public class TestKwikService {
    @Autowired
    private KwikService service;

    @Test
    public void shouldGetPurchaseOrder() {
        String orderID = "wilm2022";
        assertTrue(service.getPurchaseOrder(orderID)
                .getClass()
                .equals(PurchaseOrder.class));
    }

    @Test
    public void shouldGetNull() {
        String orderID = "doesNotExist";
        assertNull(service.getPurchaseOrder(orderID));
    }

    @Test
    public void checkPurchaseOrder() {
        String orderID = "wilm2022";
        assertTrue(service.purchaseOrderExists(orderID));
    }
}
