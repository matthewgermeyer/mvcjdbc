package com.example.controller;

import com.example.common.FoodType;
import com.example.domain.Nutrition;
import com.example.domain.Product;
import com.example.service.NutritionService;
import com.example.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
public class NutritionController {
    private static final Logger logger = LoggerFactory.getLogger(NutritionController.class);

    @Autowired
    NutritionService nutritionService;
    @Autowired
    ProductService productService;

    @GetMapping("/nutrition")
    public String getEmptyNut(Model model, @RequestParam(value = "id", required = true) Integer productId) {
        model.addAttribute("nutrition", new Nutrition());
        model.addAttribute("foodType", FoodType.values());
        model.addAttribute("productId", productId);
        return "nutrition";
    }

    @GetMapping("/product")
    public String getEmptyProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping("/nutrition")
    public String nutSubmit(@Valid Nutrition nutrition, BindingResult bindingResult, Model model) {
        System.out.println("\n\n\n "+nutrition);
        model.addAttribute("foodType", FoodType.values());
        if (bindingResult.hasErrors()) {

            System.out.println("\n\n\n return to nutrition..");
            System.out.println(bindingResult);
            return "nutrition";
        }
        nutritionService.add(nutrition);
        model.addAttribute("product", productService.find(nutrition.getProductId()));
        return "view-product";

    }

    @PostMapping("/product")
    public String productSubmit(@Valid Product product, BindingResult bindingResult, Model model) {
        System.out.println("\n\n\n Before adding");
        if (bindingResult.hasErrors()) {

            System.out.println("\n\n\n return to product..");
            System.out.println(bindingResult);
            return "product";
        }

        productService.add(product);

        model.addAttribute("products", productService.findAll());
        return "products";

    }

    //Drill Down
    @GetMapping("/nutrition/{id}")
    public String getNut(@PathVariable("id") Long id, Model model) {
        model.addAttribute("nutrition", nutritionService.find(id.intValue()));
        return "view-nutrition";
    }

    @GetMapping("/product/{id}")
    public String getProd(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.find(id));
        return "view-product";
    }


    //The Lists!  - Nuts
    @RequestMapping("/nutritions")
    public String getNutritions(Model model) {
        model.addAttribute("nutritions", nutritionService.findAll());
        return "nutritions";
    }

    // Products
    @RequestMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

//    @RequestMapping("/nutrition-delete")
//    public String deleteNutrition(@RequestParam(value = "nutritionId", required = true) Long nutritionId, @RequestParam(value = "action", required = true) String action, Model model) {
//        if ("remove".equals(action)) {
//            nutritionService.delete(nutritionId);
//        }
//
//        model.addAttribute("nutritions", nutritionService.findAll());
//        return "nutritions";
//    }

    @RequestMapping("/product-delete")
    public String deleteProduct(@RequestParam(value = "productId", required = true) int productId, @RequestParam(value = "action", required = true) String action, Model model) {
        if ("remove".equals(action)) {
            nutritionService.delete(productId);
        }

        model.addAttribute("products", productService.findAll());
        return "products";
    }

    //Drill Down -> edit
    @RequestMapping("/nutrition-editMode")
    public String editNutrition(@RequestParam(value = "id", required = true) Long nutritionId, Model model) {
        System.out.println("\n\n\nin edit mode.");

        model.addAttribute("nutrition", nutritionService.find(nutritionId));
        model.addAttribute("foodType", FoodType.values());
        return "edit-nutrition";
    }

    @RequestMapping("/product-editMode")
    public String editProduct(@RequestParam(value = "id", required = true) int productId, Model model) {
        System.out.println("\n\n\nin edit mode.");

        model.addAttribute("product", productService.find(productId));
        return "edit-product";
    }

    //nutrition-editMode -> Post Update..
    @PostMapping("/nutrition-update")
    public String nutUpdate(@Valid Nutrition nutrition, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            System.out.println("\n\n\n return to nutrition..");
            System.out.println(bindingResult);
            return "edit-nutrition";
        }
        System.out.println("\n\n updating.. "+nutrition);
        nutritionService.update(nutrition);
        model.addAttribute("nutritions", nutritionService.findAll());
        return "nutritions";

    }

    @PostMapping("/product/nutrition-delete")
    public String deleteNutritionFromProduct(@RequestParam(value = "nutritionId", required = true) long nutritionId, @RequestParam(value = "productId", required = true) int productId, Model model) {
        nutritionService.delete(nutritionId);
        model.addAttribute("product", productService.find(productId));
        return "view-product";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/hello-admin")
    public String helloAdmin() {
        return "hello-admin";
    }


    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleDefaultErrors(final Exception exception, final HttpServletRequest request, final HttpServletResponse resp) {
        logger.warn(exception.getMessage() + "\n" + stackTraceAsString(exception));
        return new ModelAndView("error", "message", exception.getMessage());
    }

    private String stackTraceAsString(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        return sw.toString();
    }


}
