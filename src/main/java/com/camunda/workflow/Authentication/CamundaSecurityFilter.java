import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import javax.servlet.Filter;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;

@Configuration
public class CamundaSecurityFilter {

  
public class CamundaSecurityFilter {

    @Value('${camunda.rest-api.jwt.secret-path}')
    String jwtSecretPath;

    @Value('${camunda.rest-api.jwt.validator-class}')
    String jwtValidatorClass;

    @Bean
    public FilterRegistrationBean processEngineAuthenticationFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("camunda-jwt-auth");
        registration.addInitParameter('authentication-provider', 'io.digitalstate.camunda.authentication.jwt.AuthenticationFilterJwt');
        registration.addInitParameter('jwt-secret-path', jwtSecretPath);
        registration.addInitParameter('jwt-validator', jwtValidatorClass);
        registration.addUrlPatterns("/rest/*");
        registration.setFilter(getProcessEngineAuthenticationFilter());
        return registration;
    }

    @Bean
    public Filter getProcessEngineAuthenticationFilter() {
        return new ProcessEngineAuthenticationFilterJwt();
    }
}

