package vttp.sqljoinsdemo.services;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import vttp.sqljoinsdemo.model.LineItemView;
import vttp.sqljoinsdemo.model.PurchaseOrder;
import vttp.sqljoinsdemo.repository.KwikRepo;

@Service
public class KwikService {

    private Logger logger = Logger.getLogger(KwikService.class.getName());

    @Autowired
    KwikRepo kwikRepo;

    public void makePurchaseOrder(MultiValueMap<String, String> form) throws Exception {
        // check if purchase order exists
        String orderID = makeOrderID(form.getFirst("name"), form.getFirst("date"));
        if (!kwikRepo.purchaseOrderExists(orderID)) {
            kwikRepo.addPurchaseOrder(orderID, form.getFirst("name"));
        }
        // add lineitems
        if (!form.getFirst("sku1").isBlank() && !form.getFirst("qty1").isBlank()) {
            kwikRepo.addLineItem(Integer.parseInt(form.getFirst(
                    "qty1")),
                    orderID,
                    form.getFirst("sku1"));
        }

        if (!form.getFirst("sku2").isBlank() && !form.getFirst("qty2").isBlank()) {
            kwikRepo.addLineItem(Integer.parseInt(form.getFirst(
                    "qty2")),
                    orderID,
                    form.getFirst("sku2"));
        }

        if (!form.getFirst("sku3").isBlank() && !form.getFirst("qty3").isBlank()) {
            kwikRepo.addLineItem(Integer.parseInt(form.getFirst(
                    "qty3")),
                    orderID,
                    form.getFirst("sku3"));
        }
    }

    public static String makeOrderID(String name, String date) {
        if (name.length() <= 4) {
            return name + date.substring(0, 4);
        } else {
            return name.substring(0, 4) + date.substring(0, 4);
        }
    }

    public boolean purchaseOrderExists(String orderID) {
        return kwikRepo.purchaseOrderExists(orderID);
    }

    public PurchaseOrder getPurchaseOrder(String orderID) {
        SqlRowSet result = kwikRepo.getPurchaseOrder(orderID);
        if (result.next()) {
            logger.log(Level.INFO, "Retrieved purchase order " + orderID);
            PurchaseOrder order = PurchaseOrder.createFromSqlRowSet(result);
            logger.log(Level.INFO, "Successfully created PurchaseOrder object");
            return order;
        }
        return null;
    }

    public List<LineItemView> getLineItemViews(String orderID) {
        SqlRowSet result = kwikRepo.getLineItemViewsByOrderID(orderID);
        logger.log(Level.INFO, "Retrieved line items for order ID" + orderID);
        List<LineItemView> viewList = new LinkedList<>();
        while (result.next()) {
            logger.log(Level.INFO, "Creating LineItemView object");
            LineItemView line = LineItemView.createLineItemView(result);
            viewList.add(line);
        }
        logger.log(Level.INFO, "Created " + viewList.size() + " line item views");
        return viewList;
    }

    public double getTotalCost(List<LineItemView> lineItemViews){
        double total = 0;
        for (LineItemView line : lineItemViews) {
            total += line.getUnitPrice() * line.getQuantity();
        }

        return total;
    }
}
