package com.wangsong.schedule.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wangsong.common.model.CodeEnum;
import com.wangsong.common.model.Result;
import com.wangsong.system.api.SystemAPI;
import com.wangsong.system.model.UserDO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


/**
 * 定时任务 controller
 *
 * @author ty
 * @date 2015年1月14日
 */
@Controller
@RequestMapping("/schedule/schedule")
public class ScheduleJobController {

    @Autowired
    private SystemAPI client;

    @RequestMapping("/add")
    @ResponseBody
    public Object add(String text) {
        UserDO userDO=new UserDO();
        userDO.setUsername(text);
        return client.getUser(userDO);
    }

    @HystrixCommand(fallbackMethod = "test")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result list() {
        return new Result(CodeEnum.SUCCESS.getCode(), 1);
    }


    public Result test() {
        return new Result(CodeEnum.SUCCESS.getCode(), 2);
    }


}
