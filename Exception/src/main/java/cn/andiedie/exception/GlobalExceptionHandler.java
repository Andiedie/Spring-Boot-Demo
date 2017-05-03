package cn.andiedie.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {

    
    public ExceptionInfo<String> MyExceptionHandler(HttpServletRequest req, MyException e) {

    }
}
