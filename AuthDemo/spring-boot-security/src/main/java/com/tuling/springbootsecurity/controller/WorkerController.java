package com.tuling.springbootsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/worker")
public class WorkerController {

//    @Secured("ROLE_worker")
//    @RolesAllowed("ROLE_worker")
//    @PreAuthorize("hasAnyRole('worker')")
    @GetMapping("/query")
    public Object queryWorker(){
        return "worker";
    }
}
