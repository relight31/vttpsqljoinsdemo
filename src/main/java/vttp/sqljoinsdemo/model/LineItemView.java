package vttp.sqljoinsdemo.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LineItemView {
    private int quantity;
    private String orderID;
    private String description;
    private double unitPrice;

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public static LineItemView createLineItemView(SqlRowSet result) {
        LineItemView line = new LineItemView();
        line.setQuantity(result.getInt("quantity"));
        line.setOrderID(result.getString("order_id"));
        line.setDescription(result.getString("description"));
        line.setUnitPrice(result.getDouble("unit_price"));
        return line;
    }
}
