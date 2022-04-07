package vttp.sqljoinsdemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static vttp.sqljoinsdemo.repository.Queries.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class KwikRepo {
    private Logger logger = Logger.getLogger(KwikRepo.class.getName());

    @Autowired
    JdbcTemplate template;

    public boolean addLineItem(int quantity,
            String orderID,
            String prodID) {
        int added = template.update(SQL_INSERT_LINEITEMS, quantity, orderID, prodID);
        return added == 1;
    }

    public boolean addPurchaseOrder(String orderID,
            String name) {
        int added = template.update(SQL_INSERT_PURCHASEORDER, orderID, name);
        return added == 1;
    }

    public boolean purchaseOrderExists(String orderID) {
        SqlRowSet result = template.queryForRowSet(SQL_CHECK_PURCHASEORDER_EXISTS, orderID);
        return result.next();
    }

    public SqlRowSet getPurchaseOrder(String orderID) {
        SqlRowSet result = template.queryForRowSet(SQL_SELECT_PURCHASEORDER, orderID);
        return result;
    }

    public SqlRowSet getLineItemViewsByOrderID(String orderID) {
        logger.log(Level.INFO, "Able to call getLineItemViewsByOrderID method");
        SqlRowSet result = template.queryForRowSet(SQL_GET_LINEITEMVIEWS_BY_ORDERID, orderID);
        return result;
    }
}
