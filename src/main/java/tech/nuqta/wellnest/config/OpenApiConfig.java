package tech.nuqta.wellnest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * The OpenApiConfig class specifies the OpenAPI configuration for the WellNest API.
 * It includes information such as the API contact details, description, version, licensing, terms of service,
 * servers, and security requirements.
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Akobir Abduganiev",
                        email = "akobir.abduganiev@ya.ru",
                        url = "https://nuqta.tech"
                ),
                description = "OpenApi specification for the WellNest project",
                title = "WellNest API",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://nuqta.tech"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080/"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://petroleum-prince-undefined-inn.trycloudflare.com/"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
