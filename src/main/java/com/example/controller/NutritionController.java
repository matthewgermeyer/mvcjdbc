package com.example.controller;

import com.example.domain.Nutrition;
import com.example.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("nutritions",nutritionService.findAll());
        return "nutritions";
    }

    //create a nutrition
    //its making something so use the @PostMapping annotation
    @PostMapping("/nutrition")
    //return statement is a String .. aka html file called result(.html)
    public String nutSubmit(Model model, @ModelAttribute Nutrition nutrition) {
        nutritionService.add(nutrition);
        //return to the page with the full list of nuts.
        model.addAttribute("nutritions",nutritionService.findAll());
        return "nutritions";
    }

}
