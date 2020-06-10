package cn.luxu.springsecurityjwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.luxu.springsecurityjwtdemo.mapper")
public class SpringSecurityJwtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtDemoApplication.class, args);
    }

}
