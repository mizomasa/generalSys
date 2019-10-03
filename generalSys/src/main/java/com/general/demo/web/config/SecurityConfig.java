package com.general.demo.web.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .mvcMatchers(HttpMethod.GET,"/").permitAll()//全許可
        .mvcMatchers(HttpMethod.GET,"/login").permitAll()//全許可
        .mvcMatchers(HttpMethod.POST, "/logout").authenticated()
        .anyRequest().authenticated()//上記以外は、認証済みのであれば、アクアセス可
        ;
        this.setLogin(http);
        this.setLogout(http);
    }

    private void setLogin(HttpSecurity http) throws Exception {
        http.formLogin()
            .defaultSuccessUrl("/",false);

    }
    private void setLogout(HttpSecurity http) throws Exception {
        //ログアウト処理
        http.logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/");
        http.exceptionHandling()
            .accessDeniedPage("/accessDenied");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}