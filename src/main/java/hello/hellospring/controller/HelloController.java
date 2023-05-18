package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    /**
     * method         : hello
     * author         : 오동준
     * date           : 2023/05/18
     * description    : ex01_template.txt
     */
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring!!");
        return "ex01/hello";
    }

    /**
     * method         : helloMvc
     * author         : 오동준
     * date           : 2023/05/18
     * description    :  @RequestParam (value는 key값, required는 필수 여부, defaultValue는 기본값)
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name" , required = false, defaultValue = "흐미") String name, Model model ){
        model.addAttribute("name", name);
        return "ex01/hello-template";
    }

    /**
     * method         : heeloString
     * author         : 오동준
     * date           : 2023/05/18
     * description    : ResponseBody 어노테이션을 사용하여 데이터를 반환한다.
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String heeloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    /**
     * method         : helloApi
     * author         : 오동준
     * date           : 2023/05/18
     * description    : json 방식으로 데이터를 반환한다.
     *
     */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    /**
     * class          : Hello
     * author         : 오동준
     * date           : 2023/05/18
     * description    : helloApi 메소드에서 사용할 클래스
     */

    static  class  Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
