package org.controller.cms;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
@RequestMapping("cms/tmp")
public class TmpController {
    @RequestMapping("hello")
    public @ResponseBody
    String hello() throws Exception{
        return "myhello";
    }
}
