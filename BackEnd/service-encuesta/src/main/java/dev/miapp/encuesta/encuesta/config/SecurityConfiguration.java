package dev.miapp.encuesta.encuesta.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;

import dev.miapp.encuesta.encuesta.security.AwsCognitoJwtAuthenticationFilter;
import dev.miapp.encuesta.encuesta.security.AwsCognitoRSAKeyProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${awsCognitoRegion}")
	private String awsCognitoRegion;
	
	//pool id de cognito configurado previamente
	@Value("${awsCognitoPoolId}")
	private String awsUserPoolsId;
	
	@Bean
	public JWTVerifier beanJWTVerifier() throws IOException {
		RSAKeyProvider keyProvider = new AwsCognitoRSAKeyProvider(awsCognitoRegion, awsUserPoolsId);
		Algorithm algorithm = Algorithm.RSA256(keyProvider);
		JWTVerifier jwtVerifier = JWT.require(algorithm)
		.acceptLeeway(1)   //1 sec for nbf and iat
    	.acceptExpiresAt(5)   //5 secs for exp
		.build();
		return jwtVerifier;
	}
	
	@Autowired
	private AwsCognitoJwtAuthenticationFilter awsCognitoJwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().cacheControl();
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.csrf().disable()
				.authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS,"/api/**").permitAll()
					.antMatchers(HttpMethod.GET,"/v2/api-docs").permitAll()
					.antMatchers(HttpMethod.POST,"/api/v1/security/login").permitAll()
					.antMatchers(HttpMethod.POST,"/api/**/security/first-reset-password").permitAll()
					.antMatchers(HttpMethod.POST,"/api/**/security/change-password").permitAll()
					.antMatchers(HttpMethod.POST,"/api/**/security/refresh-token").permitAll()
					.antMatchers(HttpMethod.POST,"/api/**/security/signout*").permitAll()
					.antMatchers("/api/**").authenticated()
					.antMatchers("/**").permitAll()
					.anyRequest().authenticated()
				.and()
				.addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
