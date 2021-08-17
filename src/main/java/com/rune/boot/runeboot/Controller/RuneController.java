package com.rune.boot.runeboot.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RuneController {

    @RequestMapping("/")
    public ModelAndView hello(){
        System.out.println("================================== START ==================================");
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

}
