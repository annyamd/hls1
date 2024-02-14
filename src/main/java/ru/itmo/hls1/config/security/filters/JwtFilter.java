package ru.itmo.hls1.config.security.filters;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itmo.hls1.config.security.utils.JwtUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String HEADER_NAME = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Получаем заголовок
        var authHeader = request.getHeader(HEADER_NAME);
        if (null == authHeader || "null".equals(authHeader) || !authHeader.startsWith(BEARER_PREFIX)) {
            // Если запрос не содержит необходимый заголовок для аутентификации по JWT, то пускаем дальше по фильтрам
            filterChain.doFilter(request, response);
            return;
        }

        // Получаем токен из заголовка
        var jwt = authHeader.substring(BEARER_PREFIX.length());
        // Обрезаем префикс и получаем имя пользователя из токена
        var username = jwtUtils.extractUserName(jwt);


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            try {
                // Если токен валиден ...
                if (jwtUtils.isValid(jwt, userDetails)) {
                    // то аутентифицируем пользователя
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                // TODO The token lifetime has expired
            } catch (SignatureException e) {
                // TODO The signature is incorrect
            } catch (JwtException e) {
                // TODO JWT validation failed! Can't trust JWT!
            }
        }

        filterChain.doFilter(request, response);
    }
}
