package com.studyspace.studyspaceapi.filter;

import com.studyspace.studyspaceapi.request.MultiReadHttpServletRequest;
import com.studyspace.studyspaceapi.util.SpringBeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.studyspace.studyspaceapi.bean.MultiReadRequestBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author lin
 * @date 2018/11/9
 */
@Component
@Import(SpringBeanUtils.class)
public class MultiReadRequestFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (useMultiRead(request)) {
            MultiReadHttpServletRequest multiReadHttpServletRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);
            chain.doFilter(multiReadHttpServletRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean useMultiRead(ServletRequest request){
        Map<String, MultiReadRequestBean> beans = SpringBeanUtils.getBeansOfType(MultiReadRequestBean.class);
        boolean use = !beans.isEmpty();
        for (String key : beans.keySet()) {
            if (beans.size() > 1 && StringUtils.equals("defaultMultiReadRequestBean", key)) {
                continue;
            }
            if (!beans.get(key).filter(request)) {
                use = false;
            }
        }
        return use;
    }

}
