package com.smart.oauth2.serve.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Description: 资源服务器
 * @ProjectName: spring-parent
 * @Package: com.yaomy.security.resource.api.ResourceController
 * @Date: 2019/7/12 14:59
 * @Version: 1.0
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Object get() {
        return SecurityContextHolder.getContext();
    }

    @RequestMapping(value = "/getRole")
    @ResponseBody
    public Object getRole(@ApiIgnore Authentication authentication) {
        return "你好啊";
    }


}
