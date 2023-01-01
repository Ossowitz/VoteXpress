package ru.iooko.votingapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(
                title = "REST API DOCUMENTATION",
                version = "1.0",
                description = "VoteXpress",
                contact = @Contact(url = "https://github.com/Ossowitz/VoteXpress",
                        name = "Ilya Tikhomirov",
                        email = "https://t.me/DispatcherServlet")
        ),
        security = @SecurityRequirement(name = "basicAuth")
)
public class OpenApiConfig {
}