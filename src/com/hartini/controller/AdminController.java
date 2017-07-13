package com.hartini.controller;

import com.hartini.entity.Item;
import com.hartini.entity.ItemCategory;
import com.hartini.entity.User;
import com.hartini.service.ItemCategoryService;
import com.hartini.service.ItemService;
import com.hartini.service.OrderService;
import com.hartini.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by misbahul on 12/06/17.
 */
@Controller
@SessionAttributes("userData")
public class AdminController {

    @Autowired
    ServletContext context;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showLoginPage(Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession != null)
            return "redirect:/admin/dashboard";
        User user = new User();
        model.addAttribute("user", user);
        return "admin/admin-login";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String authUser(@Valid User user, BindingResult bindingResult, Map model) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        User userDB = userService.fetchUserByUsername(user.getUsername());

        if (userDB.getId() != 1) {
            model.put("error", "You'are not administrator");
        } else {
            if (passwordEncryptor.checkPassword(user.getPassword(), userDB.getPassword())) {
                model.put("userData", userDB);
                return "redirect:/admin/dashboard";
            } else {
                model.put("error", "Invalid username or password");
                return "admin/admin-login";
            }
        }
        return "admin/admin-login";
    }

    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession == null)
            return "redirect:/admin";

        int itemTotal = itemService.countItem();
        int orderTotal = orderService.countOrder();
        int userTotal = userService.countUser();
        User user = (User) httpSession.getAttribute("userData");

        model.addAttribute("itemTotal", itemTotal);
        model.addAttribute("orderTotal", orderTotal);
        model.addAttribute("userTotal", userTotal);
        model.addAttribute("user", user);
        return "admin/dashboard";
    }

    /**
     * ITEM
     * Controller untuk melihat daftar Item
     *
     * @param start: index awal untuk query
     * @param model: index akhir untuk query
     * @return view admin/item/index
     */
    @RequestMapping(value = "/admin/item/{start}", method = RequestMethod.GET)
    public String itemList(@PathVariable int start, Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession == null)
            return "redirect:/admin";

        int limit = 5;
        if (start == 1) {
            start = start - 1;
        } else {
            start = (start - 1) * limit;
        }

        List<Item> itemList = itemService.fetchItemByPage(start, limit);
        int count = itemService.countItem();
        int pagination = (int) Math.ceil((double) count / limit);

        User user = (User) httpSession.getAttribute("userData");

        model.addAttribute("user", user);
        model.addAttribute("itemList", itemList);
        model.addAttribute("pagination", pagination);
        return "admin/item/index";
    }

    @RequestMapping(value = "/admin/item/create", method = RequestMethod.GET)
    public String showAddItemForm(Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession == null)
            return "redirect:/admin";


        Item item = new Item();
        List<ItemCategory> itemCategories = itemCategoryService.fetchAllItemCategory();
        Map<Integer, String> itemCategoryMap = new HashMap<>();
        for (ItemCategory ic : itemCategories) {
            itemCategoryMap.put(ic.getId(), ic.getName());
        }

        User user = (User) httpSession.getAttribute("userData");

        model.addAttribute("user", user);
        model.addAttribute("item", item);
        model.addAttribute("itemCategoryMap", itemCategoryMap);
        return "admin/item/add-form";
    }

    @RequestMapping(value = "/admin/item/create", method = RequestMethod.POST)
    public String addItem(@Valid Item item, BindingResult bindingResult, Map model) throws IOException {

        String uploadPath = context.getRealPath("") + File.separator + "assets" + File.separator + "img" + File.separator + "item" + File.separator;

        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("admin");

        String randomName = encryptor.encrypt(new Date().toString());
        String imageName = randomName + "." + FilenameUtils.getExtension(item.getFile().getOriginalFilename());

        FileCopyUtils.copy(item.getFile().getBytes(), new File(uploadPath + imageName));

        item.setImage(imageName);
        itemService.addItem(item);
        return "redirect:/admin/item/1";
    }

    @RequestMapping(value = "/admin/item/edit/{id}", method = RequestMethod.GET)
    public String showEditItemForm(@PathVariable int id, Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession == null)
            return "redirect:/admin";

        Item item = itemService.fetchItemById(id);
        List<ItemCategory> itemCategories = itemCategoryService.fetchAllItemCategory();
        Map<Integer, String> itemCategoryMap = new HashMap<>();
        for (ItemCategory ic : itemCategories) {
            itemCategoryMap.put(ic.getId(), ic.getName());
        }

        User user = (User) httpSession.getAttribute("userData");

        model.addAttribute("user", user);
        model.addAttribute("item", item);
        model.addAttribute("itemCategoryMap", itemCategoryMap);
        return "admin/item/edit-form";
    }

    @RequestMapping(value = "/admin/item/update", method = RequestMethod.POST)
    public String updateItem(@Valid Item item, BindingResult bindingResult, Map model) throws IOException {

        Item itemDB = itemService.fetchItemById(item.getId());

        if (item.getImage() == "") {
            item.setImage(itemDB.getImage());
        } else {
            String uploadPath = context.getRealPath("") + File.separator + "assets" + File.separator + "img" + File.separator + "item" + File.separator;

            if (itemDB.getImage() != "default.jpg") {
                File file = new File(uploadPath + itemDB.getImage());
                file.delete();
            }

            StandardStringDigester digester = new StandardStringDigester();

            String randomName = digester.digest(new Date().toString());
            String imageName = randomName + "." + FilenameUtils.getExtension(item.getFile().getOriginalFilename());
            imageName = imageName.replaceAll("[\\/]", "x");

            System.out.println(imageName);
            FileCopyUtils.copy(item.getFile().getBytes(), new File(uploadPath + imageName));
            item.setImage(imageName);
        }

        itemService.updateItem(item);
        return "redirect:/admin/item/1";
    }

    @RequestMapping(value = "/admin/item/delete/{id}", method = RequestMethod.GET)
    public String deleteItem(@PathVariable int id) {
        Item item = itemService.fetchItemById(id);
        String uploadPath = context.getRealPath("") + File.separator + "assets" + File.separator + "img" + File.separator + "item" + File.separator;

        File file = new File(uploadPath + item.getImage());
        file.delete();

        itemService.removeItem(id);
        return "redirect:/admin/item/1";
    }

    /**
     * ITEM CATEGORY
     * Controller untuk melihat daftar Item
     *
     * @param start: index awal untuk query
     * @param model: index akhir untuk query
     * @return view admin/item-category/index
     */
    @RequestMapping(value = "/admin/item-category/{start}", method = RequestMethod.GET)
    public String itemCategoryList(@PathVariable int start, Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession == null)
            return "redirect:/admin";


        int limit = 8;
        if (start == 1) {
            start = start - 1;
        } else {
            start = (start - 1) * limit;
        }

        List<ItemCategory> itemCategoryList = itemCategoryService.fetchItemCategoryByPage(start, limit);
        int count = itemCategoryService.countItem();
        int pagination = (int) Math.ceil((double) count / limit);

        User user = (User) httpSession.getAttribute("userData");

        model.addAttribute("user", user);
        model.addAttribute("itemCategoryList", itemCategoryList);
        model.addAttribute("pagination", pagination);
        return "admin/item-category/index";
    }

    @RequestMapping(value = "/admin/item-category/create", method = RequestMethod.GET)
    public String showAddItemCategoryForm(Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession == null)
            return "redirect:/admin";

        ItemCategory itemCategory = new ItemCategory();
        User user = (User) httpSession.getAttribute("userData");

        model.addAttribute("user", user);
        model.addAttribute("itemCategory", itemCategory);
        return "admin/item-category/add-form";
    }

    @RequestMapping(value = "/admin/item-category/create", method = RequestMethod.POST)
    public String addItemCategory(@Valid ItemCategory itemCategory, BindingResult bindingResult, Map model) {
        itemCategoryService.addItemCategory(itemCategory);
        return "redirect:/admin/item-category/1";
    }

    @RequestMapping(value = "/admin/item-category/edit/{id}", method = RequestMethod.GET)
    public String showEditItemCategoryForm(@PathVariable int id, Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession == null)
            return "redirect:/admin";

        ItemCategory itemCategory = itemCategoryService.fetchItemCategoryById(id);
        User user = (User) httpSession.getAttribute("userData");

        model.addAttribute("user", user);
        model.addAttribute("itemCategory", itemCategory);
        return "admin/item-category/edit-form";
    }

    @RequestMapping(value = "/admin/item-category/edit", method = RequestMethod.POST)
    public String updateItemCategory(@Valid ItemCategory itemCategory, BindingResult bindingResult, Map model) {
        itemCategoryService.updateItemCategory(itemCategory);
        return "redirect:/admin/item-category/1";
    }

    @RequestMapping(value = "/admin/item-category/delete/{id}", method = RequestMethod.GET)
    public String deleteItemCategory(@PathVariable int id) {
        itemCategoryService.removeItemCategory(id);
        return "redirect:/admin/item-category/1";
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String getUsers(Model model) {
        User user = userService.fetchUserById(2);
        model.addAttribute("user", user);
        return "admin/user-list";
    }
}
