package com.example.controller;

import com.example.Entity.Contentlist;
import com.example.Entity.User;
import com.example.Mapper.ContentMapper;
import com.example.Util.SessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Xin09 on 2017/2/14.
 */
@Controller
public class MainController {

    @Autowired
    private ContentMapper contentMapper;

    @RequestMapping("/test")
    public String getContent(HttpServletRequest request,Model model){
//        String type = "新鲜事";
//        Contentlist xxs = contentMapper.findbytype(type);
//        model.addAttribute("xxs",xxs);
//
//        String type1 = "视频";
//        Contentlist sp = contentMapper.findbytype(type1);
//        model.addAttribute("sp",sp);
//        String type2 = "趣闻";
//        Contentlist qw = contentMapper.findbytype(type2);
//        model.addAttribute("qw",qw);

        String currPage=request.getParameter("page");
        int curr=1;
        if(!StringUtils.isEmpty(currPage)){
            curr=Integer.valueOf(currPage);
        }
        int total=contentMapper.quertCount();
        model.addAttribute("total",total/5+1);
        PageHelper.startPage(curr, 5);
        List<Contentlist> list = contentMapper.findbytype();
        model.addAttribute("curr",curr);
        PageInfo<Contentlist> page = new PageInfo<Contentlist>(list);

        model.addAttribute("list", page.getList());
        return "main";
    }



    @RequestMapping(value = "/thematic",method = RequestMethod.GET)
    public String thematic(){
        return "thematiclist";
    }

}
