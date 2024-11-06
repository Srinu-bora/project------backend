
// package com.example.credentials.config;

// import com.example.credentials.util.JwtUtil;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.OncePerRequestFilter;

// import javax.servlet.Filter;
// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletRequest;
// import java.io.IOException;

// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         String token = request.getHeader("Authorization");

//         if (token != null && token.startsWith("Bearer ")) {
//             token = token.substring(7);  // Remove "Bearer " prefix

//             try {
//                 String username = JwtUtil.extractUsername(token);
//                 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                     // Perform your authentication check here
//                     UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
//                     SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                 }
//             } catch (Exception e) {
//                 // Handle exception if necessary
//             }
//         }

//         filterChain.doFilter(request, response);  // Continue with the filter chain
//     }
// }
