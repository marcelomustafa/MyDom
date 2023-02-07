package br.com.mariapuri.mydom.config.securityCookie.JWTAuthentication.JWTAuthentication_01;
//package br.com.mariapuri.mydom.config.security.JWTAuthentication;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import br.com.mariapuri.mydom.app.domain.model.UserModel;
//import br.com.mariapuri.mydom.app.service.UserService;
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//public class AuthTokenFilter extends OncePerRequestFilter {
// 
//    public static final String _BEARER = "Bearer ";
// 
//    @Autowired
//    private UserService userService;
// 
//    @Autowired
//    private JWTUtil jwtUtil;
// 
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
// 
//        try {
//            String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
// 
// 
//            if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(_BEARER)) {
//                String jwtToken = headerAuth.substring(7);
// 
//                String username = jwtUtil.parseJWT(jwtToken).getSubject();
// 
//                UserModel userDetails = userService.findByUserName(username).get();
//                UsernamePasswordAuthenticationToken authentication =
//                        new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
// 
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
// 
//        } catch (Exception ex) {
//            log.error("Error authenticating user request : {}", ex.getMessage());
//        }
// 
//        filterChain.doFilter(request, response);
//    }
//    
//    @Bean
//    public AuthTokenFilter getJWTAuthTokenFilter() throws Exception {
//        return new AuthTokenFilter();
//    }    
//}