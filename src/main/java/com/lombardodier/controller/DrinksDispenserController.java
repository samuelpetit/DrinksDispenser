package com.lombardodier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lombardodier.model.DrinksDispenser;


@Controller
public class DrinksDispenserController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String index() {
        return "redirect:/form";
    }
    
    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String drinksDispenserForm(Model model) {
        model.addAttribute("drinksDispenserContent", new DrinksDispenser());
        return "form";
    }
}
