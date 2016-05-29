/**
 * 
 */
package com.mycloud.store.exception;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author I075320
 *
 */

@Configuration
@PropertySource(name = "props", value = "classpath:/META-INF/error.properties")
public abstract class AbstractExceptionHandler {

    @Autowired
    private Environment env;

    public String handleErrorCode(String errorCode, Object... args) {
        String value = env.getProperty(errorCode);
        String format = MessageFormat.format(value, args);
        return format;
    }

}
