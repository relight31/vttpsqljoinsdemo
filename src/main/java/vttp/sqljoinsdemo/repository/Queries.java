package vttp.sqljoinsdemo.repository;

public interface Queries {
    public static final String SQL_INSERT_LINEITEMS = "insert into lineitems (quantity, order_id, prod_id) values (?,?,?)";
    public static final String SQL_INSERT_PURCHASEORDER = "insert into purchaseorder (order_id, name) values (?,?)";
    public static final String SQL_CHECK_PURCHASEORDER_EXISTS = "select * from purchaseorder where order_id = ?";
    public static final String SQL_SELECT_PURCHASEORDER = "select * from purchaseorder where order_id = ?";
    public static final String SQL_GET_LINEITEMVIEWS_BY_ORDERID = "select * from line_item_view where order_id= ?";
}
