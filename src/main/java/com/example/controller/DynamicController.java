package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Xin09 on 2017/3/6.
 */
@Controller
public class DynamicController {
    @RequestMapping(value = "/dynamic",method = RequestMethod.GET)
    public String say(){
        return "dynamiclist";
    }
}
