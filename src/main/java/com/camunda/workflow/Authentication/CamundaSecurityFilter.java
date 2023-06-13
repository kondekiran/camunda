import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import javax.servlet.Filter;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
@Configuration
public class CamundaSecurityConfig
{

@Bean
public FilterRegistrationBean<ProcessEngineAuthenticationFilter> processEngineAuthenticationFilter()
{
 FilterRegistrationBean<ProcessEngineAuthenticationFilter> registration = new FilterRegistrationBean<>();
 registration.setName("camunda-auth");
 registration.setFilter(this.getProcessEngineAuthenticationFilter());
 registration
 .addInitParameter("authentication-provider", HttpBasicAuthenticationProvider.class.getName());
		registration.addUrlPatterns("/engine-rest/*");
		return registration;
	}

@Bean
public ProcessEngineAuthenticationFilter getProcessEngineAuthenticationFilter()
{
return new ProcessEngineAuthenticationFilter();
	}
}

