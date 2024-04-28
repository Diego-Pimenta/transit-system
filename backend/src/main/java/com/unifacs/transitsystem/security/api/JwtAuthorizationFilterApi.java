package com.unifacs.transitsystem.security.api;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

import static com.unifacs.transitsystem.security.SecurityConstants.*;

public class JwtAuthorizationFilterApi extends BasicAuthenticationFilter {

    public JwtAuthorizationFilterApi(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if(header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authorization = authorizeRequest(request);
        SecurityContextHolder.getContext().setAuthentication(authorization);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authorizeRequest(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if(token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .build()
                    .parseSignedClaims(token.replace(TOKEN_PREFIX, ""))
                    .getPayload();
            String user = claims.getSubject();
            if(user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
