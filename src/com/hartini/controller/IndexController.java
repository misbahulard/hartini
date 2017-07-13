package com.hartini.controller;

import com.hartini.entity.Item;
import com.hartini.entity.ItemCategory;
import com.hartini.service.ItemCategoryService;
import com.hartini.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by misbahul on 08/06/17.
 */
@Controller
public class IndexController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndexPage(Model model) {
        List<ItemCategory> itemCategories = itemCategoryService.fetchAllItemCategory();

        int limit = 8;
        int start = 0;

        List<Item> itemList = itemService.fetchItemByPage(start, limit);

        model.addAttribute("itemList", itemList);
        model.addAttribute("category", itemCategories);
        return "index";
    }

    @RequestMapping(value = "/404notfound", method = RequestMethod.GET)
    public String showNotFoundPage() {
        return "404";
    }

    @RequestMapping(value = "*", method = RequestMethod.GET)
    public String redirectNotFound() {
        return "redirect:/404notfound";
    }
}
