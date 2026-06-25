package br.com.atividade.api_imoveis.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        // .info configura os metadas, titulos e contatos do cabeçalho,.addSecurityItem adiciona requisito de
        // segurança global para os testes, .components define o padrão Bearer JWT que será usado para enviar token no HTTP
        return new OpenAPI().info(new Info().title("API de Gerenciamento de Imóveis e Proprietários")
                .description("API REST desenvolvida para matéria de Desenvolvimento de Software. "
                        + "Inclui operações de CRUD para Proprietários e Imóveis com persistência no PostgreSQL.")
                .version("v1.0")
                .contact(new Contact().name("Gabriel Pereira Garcia").email("gpgarcia0196@gmail.com"))
                .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("Insira o token JWT gerado no endpoint de login para acessar as rotas protegidas")));

    }
}
