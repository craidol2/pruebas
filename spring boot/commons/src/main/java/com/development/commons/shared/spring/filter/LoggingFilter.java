package com.development.commons.shared.spring.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var requestWrapper = new ContentCachingRequestWrapper(request);
        var responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long duration = System.currentTimeMillis() - startTime;

        var requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        var responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        LOGGER.info("FINISHED PROCESSING : REQUEST_URI={}; REQUEST_QUERY_STRING={}; RESPONSE_CODE={}; METHOD={}; REQUEST_PAYLOAD={}; RESPONSE={}; TIME_TAKEN(MS)={}", request.getRequestURI(), request.getQueryString(), response.getStatus(), request.getMethod(), requestBody, responseBody, duration);

        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            var value = new String(contentAsByteArray, characterEncoding);
            return value.replaceAll("\\s{2,}", " ").trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.toString());
        }
        return "";
    }
}
