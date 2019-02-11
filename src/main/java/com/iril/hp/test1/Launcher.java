package com.iril.hp.test1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        new SpringApplicationBuilder() //
            .sources(Launcher.class)//
            .run(args);
    }

}
