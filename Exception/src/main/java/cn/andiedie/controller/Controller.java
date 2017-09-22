package cn.andiedie.controller;

import cn.andiedie.exception.MyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping
    public void exception() throws Exception {
        throw new MyException("Something Wrong");
    }
}
