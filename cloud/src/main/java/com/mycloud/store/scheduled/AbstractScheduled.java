/**
 * 
 */
package com.mycloud.store.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Shawn
 *
 */
@Configuration
@PropertySource(name = "props", value = "classpath:/META-INF/tasks.properties", ignoreResourceNotFound=true)
public abstract class AbstractScheduled {

    @Autowired
    private Environment env;

    public Environment getEnv() {
        return env;
    }

}