package com.studyspace.studyspaceapi.config;

import com.studyspace.studyspaceapi.filter.MultiReadRequestFilter;
import org.springframework.web.WebApplicationInitializer;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

/**
 * 配置过滤器 。为spring mvc 不用在 web.xml 配置 filter 而定制的类。对spring boot而言，可有可无
 * @author lin
 * @date 2018/12/25
 */
public class RequestBodyParamConfigInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        registerFilter(container);
    }

    private void registerFilter(ServletContext container) {
        initializeSAMLFilter(container);
    }

    private void initializeSAMLFilter(ServletContext container) {
        FilterRegistration.Dynamic filterRegistration = container.addFilter("MultiReadRequestFilter", MultiReadRequestFilter.class);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        filterRegistration.setAsyncSupported(true);
    }

}
