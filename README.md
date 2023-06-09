# RequestBodyParam : Get Http Body Param

[![Build Status](https://travis-ci.com/LambdaExpression/RequestBodyParam.svg?branch=master)](https://travis-ci.com/LambdaExpression/RequestBodyParam)
[![Maintainability](https://api.codeclimate.com/v1/badges/e547270d7828998c6f38/maintainability)](https://codeclimate.com/github/LambdaExpression/RequestBodyParam/maintainability)

[English](#english)


## English

RequestBodyParam is to solve the problem of defining Dto (or String receiving parsing) when using @RequestBody. Based on the logic of RequestBody, RequestBodyParam is written to support reading the parameters under body.

## I.Spring Boot Quick Start

#### 1.Add Dependency

**Note:** RequestBodyParam requires Java 8 or later.

If your application is build in maven, just add the following code in pom.xml.

```xml

    <dependencies>
        ...
        <dependency>
          <groupId>com.github.lambdaexpression</groupId>
          <artifactId>request-body-param</artifactId>
          <version>2.0.3.RELEASE</version>
        </dependency>
        ...
    </dependencies>


```

#### 2.Add Configurer

Add @EnableRequestBodyParam tag injection service

```java

@Component
@EnableRequestBodyParam
public class RequestBodyParamConfigurer {

}

```

#### 3.Use On Controller

Controller param add @RequestBodyParam tag

```java

@RestController
@RequestMapping
public class TestController {

    @PostMapping(value = "test1", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object test1(@RequestBodyParam Integer value1 , @RequestBodyParam String value2 ) {
        Map<String, Object> date = new HashMap<>(2);
        date.put("value1", v1);
        date.put("value2", value2);
        return date;
    }

}
```

#### 4.Test
run project

```shell
[root@centos ~]# curl -H "Content-type:application/json" -X POST -d '{"value1":123,"value2":"test"}' http://127.0.0.1:8080/test1
{"value2":"test","value1":123}
```


## II.Spring MVC Quick Start

#### 1.maven Add Dependency
             
**Note:** RequestBodyParam requires Java 8 or later.
             
If your application is build in maven, just add the following code in pom.xml.


```xml

    <dependencies>
        ...
        <dependency>
          <groupId>com.github.lambdaexpression</groupId>
          <artifactId>request-body-param</artifactId>
          <version>2.0.1.RELEASE</version>
        </dependency>
        ...
    </dependencies>


```

#### 2.Update SpringMVC-servlet.xml

```xml

<bean class="com.studyspace.studyspaceapi.config.RequestBodyParamConfigurer"/>

<mvc:annotation-driven>
<mvc:argument-resolvers>
    <bean class="com.studyspace.studyspaceapi.resolver.RequestBodyParamArgumentResolver"/>
</mvc:argument-resolvers>
</mvc:annotation-driven>

```

#### 3.Use On Controller
       
Controller param add @RequestBodyParam tag

```java

    @RequestMapping(value = "test1", produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public Object test1(@RequestBodyParam Integer value1 , @RequestBodyParam String value2 ) {
        Map<String, Object> date = new HashMap<>(4);
        date.put("value1", v1);
        date.put("value2", value2);
        return date;
    }

```

#### 4.Test

run project

```shell
[root@centos ~]# curl -H "Content-type:application/json" -X POST -d '{"value1":123,"value2":"test"}' http://127.0.0.1:8080/test1
{"value2":"test","value1":123}
```

## III.Advanced

### 1.RequestBodyParam Tag

@RequestBodyParam(value="name",request=false) provide an additional three labels

name|action
---|---
value|alias
name|same value, alias
request|whether the body param parameter is required. The default is true , an exception is thrown when the argument is empty; set to false if body param is allowed to be empty

### 2.MultiReadRequestBean

Intercept rules for custom @RequestBodyParam by inheriting MultiReadRequestBean

2.0.3.RELEASE Previously, need to add MultiReadRequestBean to support PUT. Now DefaultMultiReadRequestBean supports POST and PUT by default, and in special cases you can define MultiReadRequestBean filter by yourself.

```
@Component
@EnableRequestBodyParam
public class RequestBodyParamConfigurer {

    /**
     * Customize MultiReadHttpServletRequest
     * <p>Use default DefaultMultiReadRequestBean when don't has Customize MultiReadRequestBean
     *
     * Customize the usage conditions of MultiReadHttpServletRequest
     * <p>When MultiReadRequestBean is not defined, the default DefaultMultiReadRequestBean condition will be used
     *
     * @see cn.kknotes.open.bean.DefaultMultiReadRequestBean
     * @param request
     * @return
     */
    @Bean
    public MultiReadRequestBean testMultiReadRequestBean(ServletRequest request) {
        return new MultiReadRequestBean() {
            @Override
            public boolean filter(ServletRequest request) {
                // Definition rule
                // 自定义条件
                return true;
            }
        };
    }

}
```
[demo](https://github.com/LambdaExpression/RequestBodyParam/blob/master/demo/spring-boot-demo/src/main/java/com/github/lambdaexpression/demo/config/RequestBodyParamConfigurer.java)




--------------

## 中文

RequestBodyParam is to solve the problem that Dto (or String receiving and parsing) must be defined when using @RequestBody. Based on the logic of RequestBody, RequestBodyParam is written to support reading parameters under body
## I.spring-boot quick use

#### 1. Maven introduces the jar package

**Note:** RequestBodyParam requires Java 8 or higher.

If your application is built in maven, just add the following code in pom.xml.
```xml

    <dependencies>
        ...
        <dependency>
          <groupId>com.github.lambdaexpression</groupId>
          <artifactId>request-body-param</artifactId>
          <version>2.0.1.RELEASE</version>
        </dependency>
        ...
    </dependencies>


```

#### 2.Establish Configurer

Add @EnableRequestBodyParam tag to inject service
```java

@Component
@EnableRequestBodyParam
public class RequestBodyParamConfigurer {

}

```

#### 3.Controller层使用

Add the @RequestBodyParam tag to the interface input parameter```java

@RestController
@RequestMapping
public class TestController {

    @PostMapping(value = "test1", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object test1(@RequestBodyParam Integer value1 , @RequestBodyParam String value2 ) {
        Map<String, Object> date = new HashMap<>(2);
        date.put("value1", v1);
        date.put("value2", value2);
        return date;
    }

}
```


#### 4. Call
Start project call

```shell
[root@centos ~]# curl -H "Content-type:application/json" -X POST -d '{"value1":123,"value2":"test"}' http://127.0.0.1:8080/test1
{"value2":"test","value1":123}
```


## II.spring-mvc 快速使用

#### 1.maven 引入jar包

```xml

    <dependencies>
        ...
        <dependency>
          <groupId>com.github.lambdaexpression</groupId>
          <artifactId>request-body-param</artifactId>
          <version>2.0.3.RELEASE</version>
        </dependency>
        ...
    </dependencies>


```

#### 2.添加 SpringMVC-servlet.xml 配置

```xml

<bean class="com.studyspace.studyspaceapi.config.RequestBodyParamConfigurer"/>

<mvc:annotation-driven>
<mvc:argument-resolvers>
    <bean class="com.studyspace.studyspaceapi.resolver.RequestBodyParamArgumentResolver"/>
</mvc:argument-resolvers>
</mvc:annotation-driven>

```

#### 3.Controller层使用

Add the @RequestBodyParam tag to the interface input parameter```java

    @RequestMapping(value = "test1", produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public Object test1(@RequestBodyParam Integer value1 , @RequestBodyParam String value2 ) {
        Map<String, Object> date = new HashMap<>(4);
        date.put("value1", v1);
        date.put("value2", value2);
        return date;
    }

```

#### 4. Call
Start project call

```shell
[root@centos ~]# curl -H "Content-type:application/json" -X POST -d '{"value1":123,"value2":"test"}' http://127.0.0.1:8080/test1
{"value2":"test","value1":123}
```

## III.进阶使用

### 1.RequestBodyParam 标签

@RequestBodyParam(value="name",request=false) 提供额外的三个标签

parameter name|function
---|---
value | alias
name|same as value, alias
request|Whether the body param parameter is required. The default is true , an exception is thrown when the parameter is empty; if the body param is allowed to be empty, please set it to false

### 2.MultiReadRequestBean

通过继承 MultiReadRequestBean 自定义 @RequestBodyParam 的拦截规则

2.0.3.RELEASE 以前需要添加 MultiReadRequestBean 来支持 PUT，现在DefaultMultiReadRequestBean 默认支持 POST 和 PUT，并且特殊情况下可以自行通过定义 MultiReadRequestBean filter

```
@Component
@EnableRequestBodyParam
public class RequestBodyParamConfigurer {

    /**
     * Customize MultiReadHttpServletRequest
     * <p>Use default DefaultMultiReadRequestBean when don't has Customize MultiReadRequestBean
     *
     * 自定义 MultiReadHttpServletRequest 的使用条件
     * <p>没有定义 MultiReadRequestBean 时，会使用默认的 DefaultMultiReadRequestBean 条件
     *
     * @see cn.kknotes.open.bean.DefaultMultiReadRequestBean
     * @param request
     * @return
     */
    @Bean
    public MultiReadRequestBean testMultiReadRequestBean(ServletRequest request) {
        return new MultiReadRequestBean() {
            @Override
            public boolean filter(ServletRequest request) {
                // Definition rule
                // 自定义条件
                return true;
            }
        };
    }

}
```
[demo](https://github.com/LambdaExpression/RequestBodyParam/blob/master/demo/spring-boot-demo/src/main/java/com/github/lambdaexpression/demo/config/RequestBodyParamConfigurer.java)
