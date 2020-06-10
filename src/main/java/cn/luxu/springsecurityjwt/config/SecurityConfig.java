package cn.luxu.springsecurityjwt.config;

import cn.luxu.springsecurityjwt.config.auth.CustomAccessDeniedHandler;
import cn.luxu.springsecurityjwt.config.auth.CustomAuthExceptionEntryPoint;
import cn.luxu.springsecurityjwt.filter.JWTAuthenticationFilter;
import cn.luxu.springsecurityjwt.filter.JwtAuthorizationTokenFilter;
import cn.luxu.springsecurityjwt.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.luxu.springsecurityjwtdemo.config.auth.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final CustomAuthExceptionEntryPoint customAuthExceptionEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final ObjectMapper jacksonObjectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(customAuthExceptionEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login","/home").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JWTAuthenticationFilter(authenticationManagerBean(),jacksonObjectMapper));
        http.addFilter(new JwtAuthorizationTokenFilter(authenticationManagerBean()));
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        String separator = System.lineSeparator();
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_USER " + separator + " ROLE_USER > ROLE_TOURISTS";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

}
