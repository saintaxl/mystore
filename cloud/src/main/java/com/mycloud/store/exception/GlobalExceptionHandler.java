/**
 * 
 */
package com.mycloud.store.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mycloud.exception.BusinessException;
import com.mycloud.exception.SystemException;
import com.mycloud.store.constant.PageView;


/**
 * @author I075320
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends AbstractExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({SQLException.class, DataAccessException.class })
    public ErrorMessage handleSQLException(HttpServletRequest request, Exception ex) {
        logger.info("SQLException Occured:: URL=" + request.getRequestURL());
        ErrorMessage errormessage = new ErrorMessage();
        return errormessage;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="")
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ErrorMessage handleIOException(HttpServletRequest request, Exception ex) {
        logger.error("IOException handler executed");
        ErrorMessage errormessage = new ErrorMessage();
        return errormessage;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ModelAndView handleBusinessException(HttpServletRequest request,HttpServletResponse response,BusinessException ex) {
        logger.error("BusException handler executed");
        String errorCode = ex.getErrorCode();
        String message = handleErrorCode(errorCode, ex.getArgs());
        
        ErrorMessage errormessage = new ErrorMessage();
        errormessage.setErrorCode(errorCode);
        errormessage.setMessage(message);
        String errorPage = response.getHeader("errorPage");
        String fromPage = response.getHeader("fromPage");

        ModelAndView model = new ModelAndView();
        if(StringUtils.isNotEmpty(errorPage)){
        	model.setViewName(errorPage);
        }else{
        	model.setViewName(PageView.ERROR_PAGE_404);
        }
        
        model.addObject("fromPage",fromPage);
        model.addObject("errorMessage",errormessage);
        return model;
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public ErrorMessage handleSystemException(HttpServletRequest request,SystemException ex) {
        logger.error("SysException handler executed");
        String errorCode = ex.getErrorCode();
        String message = handleErrorCode(errorCode, ex.getArgs());
        
        ErrorMessage errormessage = new ErrorMessage();
        errormessage.setErrorCode(errorCode);
        errormessage.setMessage(message);
        return errormessage;
    }


}
