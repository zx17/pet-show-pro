package com.example.Util;

import com.example.Common.Constant;
import com.example.Entity.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Xin09 on 2017/3/9.
 */
public class SessionUtil {

    public static User getLoginUser(HttpServletRequest req){

        User user=(User) req.getSession().getAttribute(Constant.LOGIN_SESSION_KEY);
        return user;

    }


    public static void removeLoginUser(HttpServletRequest req){
        req.getSession().removeAttribute(Constant.LOGIN_SESSION_KEY);
    }

    public static void setLoginUser(HttpServletRequest req, User login_user){
        HttpSession session=req.getSession();
        if(null != session && null != login_user){
            removeLoginUser(req);
            session.setAttribute(Constant.LOGIN_SESSION_KEY, login_user);
        }
    }
}
