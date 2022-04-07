package vttp.sqljoinsdemo.model;

import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class PurchaseOrder {
    private String orderID;
    private String name;
    private Date date;

    public String getOrderID() {
        return this.orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static PurchaseOrder createFromSqlRowSet(SqlRowSet result) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setName(result.getString("name"));
        purchaseOrder.setDate(result.getDate("order_date"));
        purchaseOrder.setOrderID(result.getString("order_id"));
        return purchaseOrder;
    }
}
