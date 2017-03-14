package com.example.controller;

import com.example.Entity.User;
import com.example.Mapper.UserMapper;
import com.example.Util.JsonUtil;
import com.example.Util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Xin09 on 2017/3/8.
 */
@Controller
public class UserContorller {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/relogin", method= RequestMethod.GET)
    public String relogin(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest req, Model model){
        String username = req.getParameter("name");
        String password = req.getParameter("psd");
        User u = userMapper.findbyname(username);
        if (u == null){
            return "loginfail";
        }else {
            String getpassword = u.getPassword();
            Integer integral = u.getIntegral();
            if (getpassword.equals(password)){
                model.addAttribute("username",username);
                model.addAttribute("integral",integral);
                SessionUtil.setLoginUser(req,u);
                return "personalcenter";
            }else
                return "index";
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(HttpServletRequest param) {
        String name = param.getParameter("username");
        String password = param.getParameter("password");
        User u = userMapper.findbyname(name);
        if (u == null){
            userMapper.insert(name,password,0,0);
            return "index";
        } else {
            return "regfail";
        }
    }

    @RequestMapping(value = "/center",method = RequestMethod.GET)
    public String test(HttpServletRequest request,Model model){
        User u= SessionUtil.getLoginUser(request);
        if (u == null){
            return "index";
        }else {
            model.addAttribute("username",u.getName());
            model.addAttribute("integral",u.getIntegral());
            return "personalcenter";
        }

    }

    @RequestMapping("/sign")
    @ResponseBody
    public String sign(HttpServletRequest request,Model model){
        User u= SessionUtil.getLoginUser(request);
        int score = userMapper.findintegral(u.getId());//获取积分
        Date time = userMapper.findtime(u.getId());
        Date d = new Date();//当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat nyr = new SimpleDateFormat("yyyy-MM-dd");
        int con_sign = userMapper.findcon(u.getId());//连续签到次数，默认0
        if (time == null){
            int a = userMapper.sign_time(sdf.format(d),u.getId());
            if (a!=1){
               return JsonUtil.getFailure();
            }else {
                userMapper.addcon_sign(con_sign+1,u.getId());
                userMapper.addintegral(score+5,u.getId());
                int integral = userMapper.findintegral(u.getId());
                model.addAttribute("username",u.getName());
                model.addAttribute("integral",integral);
                return JsonUtil.getSuccess();
            }
       }else{
            if (nyr.format(d).equals(nyr.format(time))){//判断今天是否签到
            return JsonUtil.getFailure();
        }else {
            if (((d.getTime()-time.getTime())/(1000 * 60 * 60))<48) {//判断是否是连续签到
            userMapper.addcon_sign(con_sign + 1, u.getId());
            if (con_sign < 4) {
                userMapper.addintegral(score + (5 * con_sign), u.getId());
                int integral = userMapper.findintegral(u.getId());
                userMapper.sign_time(sdf.format(d), u.getId());
                model.addAttribute("username", u.getName());
                model.addAttribute("integral", integral);
                return JsonUtil.getSuccess();
            } else {
                if (con_sign >= 4) {
                    userMapper.addintegral(score + 20, u.getId());
                    int integral = userMapper.findintegral(u.getId());
                    userMapper.sign_time(sdf.format(d), u.getId());
                    model.addAttribute("username", u.getName());
                    model.addAttribute("integral", integral);
                    return JsonUtil.getSuccess();
                }
            }
        }
           else {
               userMapper.addcon_sign(0, u.getId());
               userMapper.addintegral(score + 5, u.getId());
               int integral = userMapper.findintegral(u.getId());
               userMapper.sign_time(sdf.format(d),u.getId());
               model.addAttribute("username", u.getName());
               model.addAttribute("integral", integral);
               return JsonUtil.getSuccess();
           }
        }
        }
        return null;
    }


}
