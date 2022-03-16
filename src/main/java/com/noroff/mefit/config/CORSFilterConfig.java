//package com.noroff.mefit.config;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static com.noroff.mefit.config.ConfigSettings.CORS.*;
//
///**
// * Filter for enabling CORS support.
// */
//@Component
//public class CORSFilterConfig extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
//        response.addHeader("Access-Control-Allow-Origin", ALLOW_ORIGIN);
//        response.addHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
//        response.addHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
//        response.addHeader("Access-Control-Expose-Headers", EXPOSED_HEADERS);
//        response.addHeader("Access-Control-Allow-Credentials", ALLOW_CREDENTIALS.toString().toLowerCase());
//        response.addIntHeader("Access-Control-Max-Age", MAX_AGE);
//        filterChain.doFilter(request, response);
//    }
//}
