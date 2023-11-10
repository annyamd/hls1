//package ru.itmo.hls1.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz.requestMatchers("/player").hasAuthority("USER")
//                        .requestMatchers("/playground/*").permitAll()
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//    }
//
//    @Value("${jwt.public.key}")
//    RSAPublicKey key;
//
//    @Value("${jwt.private.key}")
//    RSAPrivateKey priv;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // @formatter:off
//        Object OAuth2ResourceServerConfigurer;http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().authenticated()
//                )
//                .csrf((csrf) -> csrf.ignoringRequestMatchers("/token"))
//                .httpBasic(withDefaults())
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .exceptionHandling((exceptions) -> exceptions
//                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
//                );
//        // @formatter:on
//        return http.build();
//    }
//
//    @Bean
//    UserDetailsService users() {
//        // @formatter:off
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user")
//                        .password("{noop}password")
//                        .authorities("app")
//                        .build()
//        );
//        // @formatter:on
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withPublicKey(this.key).build();
//    }
//
//    @Bean
//    JwtEncoder jwtEncoder() {
//        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
//        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
//        return new NimbusJwtEncoder(jwks);
//    }
//
//
//}
