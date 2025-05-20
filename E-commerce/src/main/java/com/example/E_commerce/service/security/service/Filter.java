package com.example.E_commerce.service.security.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class Filter extends OncePerRequestFilter {
    @Autowired
    private JWTservice jwtService;
    @Autowired
    private ApplicationContext context;// if directrly use UserdetailService it will
    //create the circular dependency

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // In request there will be many header and one of them is Authorization head
        // in Authentication head there will be jwtToken as
        //Bearer eyJfjdlkffdljf.fdslkjfdkl .fjdslkfjfljfklfjdklfjdfkljf
        String authHeader= request.getHeader("Authorization");
        String token= null;
        String username= null;
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);
            username=jwtService.extractUserNameFromToken(token);
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            MyUserDetailService userDetailsService = context.getBean(MyUserDetailService.class);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails((new WebAuthenticationDetailsSource().buildDetails(request)));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

}
