package com.example.servlet.filter;


import com.example.entity.User;
import com.example.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import java.sql.SQLException;
import java.util.Optional;
import java.util.StringTokenizer;


public class BasicAuthFilter implements Filter {

    private String realm = "Protected";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        UserService userService = UserService.getInstance();
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();

                if ("Basic".equalsIgnoreCase(basic)) {
                    try {
                        String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
                        int p = credentials.indexOf(":");
                        if (p != -1) {
                            String username = credentials.substring(0, p).trim();
                            String password = credentials.substring(p + 1).trim();

                            if (!userService.isExist(username, password)) {
                                sendAuthError(resp, "INVALID USERNAME OR PASSWORD");
                            }else {
                                filterChain.doFilter(servletRequest, servletResponse);
                            }
                        } else {
                            sendAuthError(resp, "INVALID TOKEN");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            sendAuthError(resp, "UNAUTHORIZED");

        }
    }

    @Override
    public void destroy() {
    }

    private void sendAuthError(HttpServletResponse resp, String message) throws IOException {
        resp.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        resp.sendError(resp.SC_UNAUTHORIZED, message);
    }
}
