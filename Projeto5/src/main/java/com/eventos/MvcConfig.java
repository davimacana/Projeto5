package com.eventos;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/dashboard").setViewName("dashboard");
        //registry.addViewController("/perfil").setViewName("perfil");
        //registry.addViewController("/eventos").setViewName("eventos");
        registry.addViewController("/tables").setViewName("tables");
        registry.addViewController("/typography").setViewName("typography");
    }

}
