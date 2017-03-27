package com.example.controller;

import com.example.Entity.User;
import com.example.Util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Xin09 on 2017/3/3.
 */
@Controller
public class ContentController {
    @RequestMapping(value = "/ss",method = RequestMethod.GET)
    public String say(HttpServletRequest request,Model model){
        User u= SessionUtil.getLoginUser(request);
        model.addAttribute("username",u.getName());
        return "content";
    }
}
