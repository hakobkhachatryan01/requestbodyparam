package com.studyspace.studyspaceapi.bean;


import jakarta.servlet.ServletRequest;

/**
 * 使用 MultiReadRequest 的基础条件bean
 * @author lin
 * @date 2018/11/9
 */
public interface MultiReadRequestBean {

    boolean filter(ServletRequest request);

}
