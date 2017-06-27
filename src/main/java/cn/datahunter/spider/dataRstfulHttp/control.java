package cn.datahunter.spider.dataRstfulHttp;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by zachary on 16/8/8.
 */
@RestController  //声明为控制器
@EnableAutoConfiguration //自动配置
public class control {
    @RequestMapping("/") //请求路径，和Spring一样
    public String Hello(){
        return "Hello Spring boot!";
    }
    @RequestMapping("/user/{username}") //带参数的请求路径
    public String User(@PathVariable("username") String username){
        return "Hello "+username;
    }
    @RequestMapping("/requset/") //带参数的请求路径
    public String ruest(@RequestParam("username") String username,@RequestParam("passworld")int i){
        return "Hello "+username+i;
    }
}
