package com.studyspace.studyspaceapi.config;

import com.studyspace.studyspaceapi.bean.DefaultMultiReadRequestBean;
import com.studyspace.studyspaceapi.bean.MultiReadRequestBean;
import com.studyspace.studyspaceapi.filter.MultiReadRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import jakarta.servlet.ServletRequest;

/**
 * 设置 MultiReadRequest 配置
 *
 * @author lin
 * @date 2018/11/9
 */
@Configuration
@Import(MultiReadRequestFilter.class)
public class MvcMultiReadRequestConfigurer {

    /**
     * 默认的 MultiReadRequestBean 启动条件
     * 使用时必须保证至少有一个 MultiReadRequestBean ，避免每个请求都被至少读取两次body
     *
     * @param request
     * @return
     */
    @Bean(name = "defaultMultiReadRequestBean")
    public MultiReadRequestBean defaultMultiReadRequestBean(ServletRequest request) {
        return new DefaultMultiReadRequestBean();
    }

}
