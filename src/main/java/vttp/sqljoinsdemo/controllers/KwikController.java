package vttp.sqljoinsdemo.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.sqljoinsdemo.model.LineItemView;
import vttp.sqljoinsdemo.services.KwikService;

@RequestMapping(path = "/purchase_order")
@Controller
public class KwikController {
    private Logger logger = Logger.getLogger(KwikController.class.getName());

    @Autowired
    KwikService kwikService;

    @GetMapping(path = { "/", "" })
    public String landingPage() {
        return "index";
    }

    @PostMapping(path = { "/", "" })
    public ModelAndView createPurchaseOrder(@RequestBody MultiValueMap<String, String> form) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        try {
            kwikService.makePurchaseOrder(form);
            mav.addObject("message", "Successfully created purchase order");
        } catch (Exception e) {
            mav.addObject("message", "Failed to create purchase order");
        }
        return mav;
    }

    @GetMapping(path = "/{orderID}")
    public ModelAndView viewPurchaseOrder(@PathVariable String orderID) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("orderID", orderID);
        if (!kwikService.purchaseOrderExists(orderID)) {
            logger.log(Level.INFO, "Order ID " + orderID + " does not exist");
            mav.setViewName("nohave");
        } else {
            logger.log(Level.INFO, "Order ID " + orderID + " available");
            mav.addObject("order", kwikService.getPurchaseOrder(orderID));
            List<LineItemView> lineItemViews = kwikService.getLineItemViews(orderID);
            mav.addObject("lineitemviews", lineItemViews);
            mav.addObject("totalcost", kwikService.getTotalCost(lineItemViews));
            mav.setViewName("vieworder");
        }
        return mav;
    }
}
