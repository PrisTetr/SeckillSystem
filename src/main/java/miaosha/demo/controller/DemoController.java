package miaosha.demo.controller;

import lombok.extern.slf4j.Slf4j;
import miaosha.demo.domain.User;
import miaosha.demo.redis.RedisService;
import miaosha.demo.result.CodeMsg;
import miaosha.demo.result.Result;
import miaosha.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "hello world";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("hello,imooc");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> Error(){
       return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","hg");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<String> redisGet(){
        log.info("运行到使用get方法前");
        String v1 = redisService.get("key1",String.class);
        return Result.success(v1);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        log.info("运行到使用set方法前");
        boolean ret = redisService.set("key2","hello,idea");
        return Result.success(ret);
    }
}
