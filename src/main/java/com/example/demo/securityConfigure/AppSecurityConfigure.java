package com.example.demo.securityConfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.example.demo.enums.ApplicationUserPermission;
import com.example.demo.enums.ApplicationUserRole;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfigure  extends WebSecurityConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		    //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//use this for form submission to the server
		    .csrf().disable()
		    
		    .authorizeRequests()
		    .antMatchers("/","index","/css/*","/js/*").permitAll()
		    //.antMatchers("/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name())   //Role base authenticaiton
		   // .antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(ApplicationUserPermission.EMPLOYEE_WRITE.getPermission())
		  //  .antMatchers(HttpMethod.PUT,"/api/**").hasAuthority(ApplicationUserPermission.EMPLOYEE_WRITE.getPermission())
		   // .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(ApplicationUserPermission.EMPLOYEE_WRITE.getPermission())
		   // .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole( ApplicationUserRole.ADMININTERN.name(),ApplicationUserRole.ADMIN.name(),ApplicationUserRole.STUDENT.name())
		    .anyRequest()
		    .authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/login").permitAll()//customer login page
		     .defaultSuccessUrl("/employee", true) //refirect after login
		     .and()
		     .logout()
		     .logoutUrl("/logout")
		     .invalidateHttpSession(true)
		     .clearAuthentication(true);
		
		    
		
		
		
	}

	

	//Create user with in memoery database
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		      UserDetails curry = User.builder()
		             .username("curryflex")
		             .password(passwordEncoder.encode("1234"))
		            // .roles(ApplicationUserRole.STUDENT.name())
		             .authorities(ApplicationUserRole.STUDENT.grantedAuthorities())
		             .build();
		      
		    UserDetails tyna =  User.builder()
		                   .username("tyna")
		                   .password(passwordEncoder.encode("12345"))
		                  // .roles(ApplicationUserRole.ADMIN.name())
		                   .authorities(ApplicationUserRole.ADMIN.grantedAuthorities())
		                   .build();
		    
		    UserDetails husna =  User.builder()
	                   .username("husna")
	                   .password(passwordEncoder.encode("12345"))
	                  // .roles(ApplicationUserRole.ADMININTERN.name())
	                   .authorities(ApplicationUserRole.ADMININTERN.grantedAuthorities())
	                   .build();
		      
		      return new InMemoryUserDetailsManager(
		    		  curry,
		    		  tyna,
		    		  husna
		    		  );
	}


	
	
	
	
	

	
}
