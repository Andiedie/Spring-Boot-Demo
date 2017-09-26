package cn.andiedie.handleexception.web;

import cn.andiedie.handleexception.domain.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping
    public String index() throws MyException {
        throw new MyException("错误发生啦");
    }
}
