package com.tuling.security.distributed.salary.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：楼兰
 * @date ：Created in 2020/10/16
 * @description:
 **/
@RestController
@RequestMapping("/mobile")
public class MobileController {

    @RequestMapping("/query")
    @PreAuthorize("hasAuthority('mobile')") //需要授权客户端有Mobile资源权限
    public String query(){
        return "mobile info";
    }
}
