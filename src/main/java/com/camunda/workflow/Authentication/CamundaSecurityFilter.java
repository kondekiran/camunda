@Configuration
public class CamundaSecurityFilter {

	@Bean
	public FilterRegistrationBean processEngineAuthenticationFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setName("camunda-auth");
		registration.setFilter(getProcessEngineAuthenticationFilter());
		registration.addInitParameter("authentication-provider",
				"org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
		registration.addUrlPatterns("/*");
		return registration;
	}

	@Bean
	public Filter getProcessEngineAuthenticationFilter() {
		return new ProcessEngineAuthenticationFilter();
	}
}
