package com.qkd.util;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.controller.ViewController;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/keyUtil")
public class KeyUtil {

    private Logger logger = Logger.getLogger(ViewController.class);

    @RequestMapping("/getSerno")
    @ResponseBody
    public synchronized final static String createKey(){
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMddHHmmssSSS",
                Locale.ENGLISH
        );
        try {
            Thread.sleep(30);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return sdf.format(new Date());
    }

}












