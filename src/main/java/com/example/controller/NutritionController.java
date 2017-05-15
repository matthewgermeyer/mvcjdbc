package com.example.controller;

import com.example.domain.Nutrition;
import com.example.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class NutritionController {

    @Autowired
    NutritionService nutritionService;

    //return an Empty Nutrition
    @GetMapping("/nutrition")
    public String getEmptyNut(Model model) {
        model.addAttribute("nutrition", new Nutrition());
        return "nutrition";
    }

    @RequestMapping("/nutritions")
    public String getNutritions(Model model) {
        //get all a list of Nuts from service
        //model.addAtribute nutritions from above
        model.addAttribute("nutritions", nutritionService.findAll());
        return "nutritions";
    }


    @PostMapping("/nutrition")
    public String nutSubmit(@Valid Nutrition nutrition, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "nutrition";
        }
        nutritionService.add(nutrition);
        model.addAttribute("nutritions", nutritionService.findAll());
        return "nutritions";
    }

}
