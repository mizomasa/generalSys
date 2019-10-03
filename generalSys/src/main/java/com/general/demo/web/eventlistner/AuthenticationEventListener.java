package com.general.demo.web.eventlistner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListener {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationEventListener.class);

    @EventListener
    public void authenticationSuccess(AuthenticationSuccessEvent e) {
        logger.info("*** 認証成功: " + e.getAuthentication().getName());
    }

    @EventListener
    public void interactiveAuthenticationSuccess(InteractiveAuthenticationSuccessEvent e) {
        logger.info("*** 相互認証成功: " + e.getAuthentication().getName());
    }

    @EventListener
    public void authenticationBadCredential(AuthenticationFailureBadCredentialsEvent e) {
        logger.info("*** 認証失敗: " + e.getAuthentication().getName());
    }
}
