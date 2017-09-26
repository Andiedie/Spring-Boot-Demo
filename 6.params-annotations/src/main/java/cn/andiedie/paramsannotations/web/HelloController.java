package cn.andiedie.paramsannotations.web;

import cn.andiedie.paramsannotations.domain.TestBean;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@RestController
public class HelloController {
    // 获取path中的参数
    @GetMapping("/path-variable/{a}/{b}")
    // 如果变量名和路径中的参数名同名，可以直接使用PathVariable（大小写敏感）
    // 如果不同，需要设置PathVariable的value/name指定名称
    public String pathVariable(@PathVariable String a, @PathVariable("b") String c) {
        return a + "," + c;
    }

    // 获取header中的参数
    @GetMapping("/request-header")
    // 如果变量名和Header名同名（忽略大小写），可直接使用RequestHeader
    // 如果不同名，需要设置RequestHeader的value/name指定名称
    public String requestHeader(@RequestHeader String host, @RequestHeader("user-agent") String useragent) {
        return host + "\n" + useragent;
    }

    // 获取cookie中的某个值
    @GetMapping("/cookie-value")
    // 如果变量名和cookie中的key相同（大小写敏感），可以直接使用CookieValue
    // 如果不同名，需要设置CookieValue的value/name指定名称
    public String cookieValue(@CookieValue String a, @CookieValue("b") String c) {
        return a + ", " + c;
    }

    // 获取请求中query、form-data和x-www-form-urlencoded中的值
    @PostMapping("/request-param")
    // 如果变量名和以上三者的key相同（大小写敏感），可以直接使用RequestParam
    // 如果不同名，需要设置RequestParam的value/name指定名称
    // 如果出现多个匹配的key，则会使用逗号隔开
    // 如query：a=1 form-data: a=2
    // 则最终a=1,2 （query总是在前）
    // 如果多个匹配的key对应的value相同，还是会使用逗号隔开
    // 例如a=1,1
    public String requestParamPOST(@RequestParam String a) {
        return a;
    }

    // 获取请求body （不支持form-data和x-www-form-urlencoded）
    @PostMapping("/request-body")
    public TestBean requestBody(@RequestBody TestBean bean) {
        return bean;
    }

}
