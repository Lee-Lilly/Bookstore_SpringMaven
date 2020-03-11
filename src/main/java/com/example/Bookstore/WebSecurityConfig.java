package com.example.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Bookstore.web.UserDetailServiceImpl;

//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception { //configure security context
        http
	        .authorizeRequests().antMatchers("/signup", "/saveUser", "/login", "/h2-console/**")
		    	.permitAll()
		    	.and()
            .authorizeRequests()
            	.anyRequest().authenticated() //Requires user authentication in all URLs
                .and()
             .formLogin()
             	.loginPage("/login")
            	.defaultSuccessUrl("/bookstore")
            	.permitAll().and()
            .logout()
            	.permitAll()
            	.invalidateHttpSession(true); // Invalidate session
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
	
	@Autowired
    private UserDetailServiceImpl userDetailsService;	
	
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	 }
	
	//"User.withDefaultPasswordEncoder()" is deprecated method.
	//the following uses AuthenticationManagerBuilder
	//a password encoder is defined by "createDelegatingPasswordEncoder" in "PasswordEncoderFactories".
	//Bean can not be initialised with PasswordEncoderFactories", remove Bean annotation
	//we use this encoder to configure our user with the AuthenticationManagerBuilder
	
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
//            .withUser("user").password(encoder.encode("user")).roles("USER");
//        auth.inMemoryAuthentication()
//        	.withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
//    }
}
