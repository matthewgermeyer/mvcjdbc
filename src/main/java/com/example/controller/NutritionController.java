package com.example.controller;

import com.example.common.FoodType;
import com.example.domain.Nutrition;
import com.example.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class NutritionController {

    @Autowired
    NutritionService nutritionService;

    //return an Empty Nutrition
    @GetMapping("/nutrition")
    public String getEmptyNut(Model model) {
        model.addAttribute("nutrition", new Nutrition());
        model.addAttribute("foodType", FoodType.values());
        return "nutrition";
    }

    @GetMapping("/nutrition/{id}")
    public String getStudent(@PathVariable("id") Long id, Model model) {
        model.addAttribute("nutrition", nutritionService.find(id.intValue()));
//        model.addAttribute("foodType", FoodType.values());

        return "view-nutrition";
    }


    @RequestMapping("/nutritions")
    public String getNutritions(Model model) {
        model.addAttribute("nutritions", nutritionService.findAll());
        return "nutritions";
    }


    @PostMapping("/nutrition")
    public String nutSubmit(@Valid Nutrition nutrition, BindingResult bindingResult, Model model) {
        model.addAttribute("foodType", FoodType.values());
        if (bindingResult.hasErrors()) {


            System.out.println("\n\n\n return to nutrition..");
            System.out.println(bindingResult);
            return "nutrition";
        }
        nutritionService.add(nutrition);
        model.addAttribute("nutritions", nutritionService.findAll());
        return "nutritions";

    }

    @RequestMapping("/nutrition-delete")
    public String deleteNutrition(@RequestParam(value = "nutritionId", required = true) Long nutritionId, @RequestParam(value = "action", required = true) String action, Model model) {
        if ("remove".equals(action)) {
            nutritionService.delete(nutritionId);
        }

        model.addAttribute("nutritions", nutritionService.findAll());
        return "nutritions";
    }


    //Under Construction --Delete
    @DeleteMapping("/nutrition/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        nutritionService.delete(id);
        model.addAttribute("nutritions", nutritionService.findAll());
        return "nutritions";
    }

}
