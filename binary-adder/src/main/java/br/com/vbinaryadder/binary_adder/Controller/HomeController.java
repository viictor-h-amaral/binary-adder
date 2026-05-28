package br.com.vbinaryadder.binary_adder.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public record HomeController() {
    
    @GetMapping("/home")
    public String home (){
        return "home";
    }

    @GetMapping("/handbook")
    public String handbook (){
        return "handbook";
    }

    @GetMapping("/settings")
    public String settings (){
        return "settings";
    }

    @GetMapping("/circuit")
    public String circuit (){
        return "circuit";
    }

    @GetMapping("/logic")
    public String logic (){
        return "logic";
    }

    @GetMapping("/about")
    public String about (){
        return "about";
    }

}
