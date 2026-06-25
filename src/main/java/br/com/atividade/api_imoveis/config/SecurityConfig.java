package br.com.atividade.api_imoveis.config;

import br.com.atividade.api_imoveis.security.FiltroJwtAutenticacao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final FiltroJwtAutenticacao filtroJwtAutenticacao;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
                return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
// 1. Libera as rotas de documentação do Swagger
                                .requestMatchers("/v3/api-docs/**",
                                        "/swagger-ui/**", "/swagger-ui.html").permitAll()
// 2. Libera o endpoint de login (se bloquear o login, ninguém entra!)
.requestMatchers("/api/auth/login").permitAll()
// 3. EXIGE AUTENTICAÇÃO para qualquer outra rota dentro de /api/
.requestMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
)
// Injeta o nosso Filtro customizado de JWT antes do filtro padrão de autenticação do Spring
                .addFilterBefore(filtroJwtAutenticacao,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}