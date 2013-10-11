package com.demo.securityutils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
        /**
         * Local Instance of Logger
         */
        private static Logger logger = Logger.getLogger(SecurityAccessDeniedHandler.class);
        private String accessDeniedUrl;

        public void handle(HttpServletRequest request,
                        HttpServletResponse response,
                        AccessDeniedException accessDeniedException) throws IOException,
                        ServletException {
                logger.debug("Secured Content access violation!!");
                response.sendRedirect("../");

        }

        /**
         * @return the accessDeniedUrl
         */
        public String getAccessDeniedUrl() {
                return accessDeniedUrl;
        }

        /**
         * @param accessDeniedUrl
         *            the accessDeniedUrl to set
         */
        public void setAccessDeniedUrl(String accessDeniedUrl) {
                this.accessDeniedUrl = accessDeniedUrl;
        }

}