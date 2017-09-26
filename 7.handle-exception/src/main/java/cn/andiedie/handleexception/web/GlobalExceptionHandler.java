package cn.andiedie.handleexception.web;

import cn.andiedie.handleexception.domain.ResponseModel;
import cn.andiedie.handleexception.domain.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public ResponseModel<String> myExceptionHandler(HttpServletRequest request, MyException e) {
        ResponseModel<String> info = new ResponseModel<>();
        info.setCode(ResponseModel.ERROR);
        info.setUrl(request.getRequestURL().toString());
        info.setMessage(e.getMessage());
        info.setData("Data");
        return info;
    }
}
