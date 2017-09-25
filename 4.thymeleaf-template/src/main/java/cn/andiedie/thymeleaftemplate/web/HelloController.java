package cn.andiedie.thymeleaftemplate.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String index(ModelMap map) {
        // 添加用于渲染的属性
        map.addAttribute("content", "Andiedie");
        // 返回模板名，对应src/main/resources/templates
        return "index";
    }
}
