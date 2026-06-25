package br.com.atividade.api_imoveis.security;
//Responsável por criar (gerar) o token na hora do login e decodificar (validar) o token nas requisições seguintes.

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    // Injeta a chave definida no application.yml
    @Value("${api.security.token.secret}")
    private String secret;

    // Método para gerar o token no login
    public String gerarToken(String login) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("api-imoveis")              //Identificador do emissor do token
                    .withSubject(login)                     //Dono do token (usuário em questão)
                    .withExpiresAt(gerarDataExpiracao())    //Tempo limite
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token JWT ", exception);
        }
    }

    //Método para validar o token que vem do cabeçalho das requisições
    public String validarToken (String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("api-imoveis")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();                          //Retorna o login do usuario se o token for válido
        } catch (JWTVerificationException exception) {
            return null;                                    //Retorna null se o token estiver violado, expirado ou inválido
        }
    }

    //Definir que o token expira em 2hrs no horário de BSB
    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
