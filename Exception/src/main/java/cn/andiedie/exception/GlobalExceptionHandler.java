package cn.andiedie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MyException.class)
    public ExceptionInfo<String> MyExceptionHandler(HttpServletRequest req, MyException e) {
        ExceptionInfo<String> info = new ExceptionInfo<>();
        info.setCode(10);
        info.setMessage(e.getMessage());
        info.setData("Data");
        info.setUrl(req.getRequestURL().toString());
        return info;
    }
}
