package com.hartini.controller;

import com.hartini.entity.*;
import com.hartini.service.ItemCategoryService;
import com.hartini.service.ItemService;
import com.hartini.service.OrderDetailService;
import com.hartini.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by misbahul on 21/06/17.
 */
@Controller
@SessionAttributes({
        "userData",
        "cart"
})
public class ProductController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemCategoryService itemCategoryService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(value = "/product/detail/{id}", method = RequestMethod.GET)
    public String showDetailProduct(@PathVariable int id, Model model) {
        List<ItemCategory> itemCategories = itemCategoryService.fetchAllItemCategory();
        Item item = itemService.fetchItemById(id);

        model.addAttribute("category", itemCategories);
        model.addAttribute("item", item);
        return "product/detail";
    }

    @RequestMapping(value = "/product/category/{id}", method = RequestMethod.GET)
    public String showByCategory(@PathVariable int id, Model model) {
        int limit = 8;
        int start = 0;

        List<ItemCategory> itemCategories = itemCategoryService.fetchAllItemCategory();
        List<Item> itemList = itemService.fetchItemByCategoryLimit(id, start, limit);

        model.addAttribute("category", itemCategories);
        model.addAttribute("itemList", itemList);
        return "product/by-category";
    }

    @RequestMapping(value = "/product/category/{idCategory}/page/{start}", method = RequestMethod.GET)
    public String showByCategoryPagination(@PathVariable int idCategory, @PathVariable int start, Model model) {
        int limit = 3;
        if (start == 1) {
            start = start - 1;
        } else {
            start = (start - 1) * limit;
        }

        List<ItemCategory> itemCategories = itemCategoryService.fetchAllItemCategory();
        List<Item> itemList = itemService.fetchItemByCategoryLimit(idCategory, start, limit);
        int count = itemList.size();
        int pagination = (int) Math.ceil((double) count / limit);

        model.addAttribute("category", itemCategories);
        model.addAttribute("itemList", itemList);
        model.addAttribute("pagination", pagination);
        return "product/by-category-page";
    }

    @RequestMapping(value = "/product/buy/{id}", method = RequestMethod.GET)
    public String addToCart(@PathVariable int id, Map model, HttpSession httpSession) {
        List<Integer> cartlist = new ArrayList<>();
        Cart cart = new Cart();
        if (httpSession.getAttribute("cart") != null) {
            cart = (Cart) httpSession.getAttribute("cart");
            cartlist = cart.getId();
        }

        cartlist.add(id);
        cart.setId(cartlist);

        model.put("cart", cart);
        return "redirect:/";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String showCheckOut(Model model, HttpSession httpSession) {
        List<ItemCategory> itemCategories = itemCategoryService.fetchAllItemCategory();
        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<Integer> idList = cart.getId();

        List<Item> itemList = itemService.fetchItemInCart(idList);

        model.addAttribute("category", itemCategories);
        model.addAttribute("itemList", itemList);
        return "/product/checkout";
    }

    @RequestMapping(value = "/checkout-now", method = RequestMethod.GET)
    public String checkoutNow(Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        Cart cart = (Cart) httpSession.getAttribute("cart");
        int[] idList = new int[cart.getId().size()];

        int x = 0;
        for (int i:cart.getId()) {
            idList[x] = i;
            x++;
        }

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Order order = new Order();
        order.setUserId(userSession.getId());
        order.setDate(timestamp);

        orderService.addTransaction(order, idList);

        List<Order> orderList = orderService.fetchAllOrder();
        Collections.reverse(orderList);
        int oid = 0;

        for (Order o:orderList) {
            if (userSession.getId() == o.getUserId()) {
                oid = o.getId();
                break;
            }
        }


        cart.getId().clear();
        model.addAttribute("oid", oid);
        model.addAttribute("cart", cart);
        return "/product/checkout-status";
    }

    @RequestMapping(value = "/print-invoice/{oid}", method = RequestMethod.GET)
    public String printInvoice(@PathVariable int oid, Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession == null)
            return "redirect:/";

        int totalHarga = 0;
        Order order = orderService.fetchOrderById(oid);
        List<OrderDetail> orderDetailList = orderDetailService.fetchOrderDetailByOrderId(oid);
        List<Integer> idItemList = new ArrayList<>();

        for (int i = 0; i < orderDetailList.size(); i++) {
            idItemList.add(orderDetailList.get(i).getItemId());
        }

        List<Item> itemList = itemService.fetchItemInCart(idItemList);

        for (Item item:itemList) {
            totalHarga += item.getPrice();
        }

        model.addAttribute("order", order);
        model.addAttribute("itemList", itemList);
        model.addAttribute("totalHarga", totalHarga);

        return "/product/invoice";
    }
}
