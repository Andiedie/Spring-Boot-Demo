package cn.andiedie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping
    public void exception() throws Exception {
        throw new Exception("Something Wrong");
    }
}
