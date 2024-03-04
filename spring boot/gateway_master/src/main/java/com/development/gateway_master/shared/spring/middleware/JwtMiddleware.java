package com.development.gateway_master.shared.spring.middleware;


import com.development.commons.shared.spring.object_mapper.CustomObjectMapper;
import com.development.gateway_master.shared.jwt.infrastructure.JwtPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Base64;

@Component
public final class JwtMiddleware implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse response,
            FilterChain filterChain
    ) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authorizationHeader)){
            var token = decodeAuth(authorizationHeader);
            request.setAttribute("idUser", token.getSid());
        }
        filterChain.doFilter(request, response);
    }

    private String[] payload(String authorizationHeader) {
        return authorizationHeader.substring(7).split("\\.");
    }

    private JwtPayload decodeAuth(String authorizationHeader) throws JsonProcessingException {
        return authorizationHeader.startsWith("Bearer ") ? CustomObjectMapper.get().readValue(
                new String(Base64.getDecoder().decode(payload(authorizationHeader)[1])),
                JwtPayload.class) : null;
    }
}
