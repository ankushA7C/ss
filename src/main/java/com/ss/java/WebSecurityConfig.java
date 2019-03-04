package com.ss.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	               .antMatchers("/home").authenticated()
	               //.antMatchers("/").permitAll()
	                .anyRequest().authenticated()
	                .antMatchers("/resources/**","/resources/static/assets/*").permitAll().anyRequest().permitAll()
		            .and()
	            .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/home")
	                .permitAll()
	                .and()
	            .logout()
	            .logoutUrl("/j_spring_security_logout")
	            .logoutSuccessUrl("/login")
	             .permitAll();
	    }

	/* Hard coded user credentials */
	  
	  
	  @Bean
	  
	  @Override public UserDetailsService userDetailsService() { UserDetails user =
	  User.withDefaultPasswordEncoder() .username("user") .password("12345")
	  .roles("USER") .build();
	  
	  return new InMemoryUserDetailsManager(user); }
	 
		 
	/*Dynamic users from database
	 * 
	 * 
	 * @Autowired private DataSource dataSource;
	 * 
	 * 
	 * @Bean
	 * 
	 * @Primary
	 * 
	 * @ConfigurationProperties("spring.datasource") public DataSourceProperties
	 * dataSourceProperties() { return new DataSourceProperties(); }
	 * 
	 * @Bean
	 * 
	 * @ConfigurationProperties("spring.datasource") public HikariDataSource
	 * dataSource(DataSourceProperties properties) { return
	 * properties.initializeDataSourceBuilder().type(HikariDataSource.class)
	 * .build(); }
	 * 
	 * @Autowired public void configAuthentication(AuthenticationManagerBuilder
	 * auth) throws Exception {
	 * 
	 * auth.jdbcAuthentication().dataSource(dataSource)
	 * .authoritiesByUsernameQuery("select username, role from user_roles where username=?"
	 * )
	 * .usersByUsernameQuery("select username, password, active from users where username=?"
	 * ) .passwordEncoder(new Md4PasswordEncoder()); }
	 */
	    
	
}

