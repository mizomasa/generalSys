package com.general.demo.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.general.demo.domain.model.Menu;

public class MenuInterceptor extends HandlerInterceptorAdapter{

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        Menu menu = new Menu();

        menu.addBusiness("top", "/", "ホーム", "home")
            .addBusiness("b1","#","システム設定","gear")
                .addFunc("b1", "/systemSetting", "システムマスタ", "gear")
            .addBusiness("b2","/users","ユーザ管理","organization");
        if(modelAndView!=null)
            modelAndView.addObject("menus", menu.getBusinesses());
    }
}
