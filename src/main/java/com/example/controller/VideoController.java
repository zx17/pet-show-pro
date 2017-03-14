package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Xin09 on 2017/3/6.
 */
@Controller
public class VideoController {

    @RequestMapping("/video-list")
    public String getvideo(){

        return "videolist";
    }

    @RequestMapping("/video-content")
    public String vidocon(){
        return "videocontent";
    }
}
