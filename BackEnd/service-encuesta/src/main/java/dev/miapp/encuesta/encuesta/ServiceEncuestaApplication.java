package dev.miapp.encuesta.encuesta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;

@SpringBootApplication
public class ServiceEncuestaApplication {

	@Value("${awsCognitoRegion}")
	private String awsCognitoRegion;
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceEncuestaApplication.class, args);
	}
	
	@Bean
	public AWSCognitoIdentityProviderClient CognitoClient() {        
        AWSCognitoIdentityProviderClient cognitoClient = new AWSCognitoIdentityProviderClient(new DefaultAWSCredentialsProviderChain());
        cognitoClient.setRegion(Region.getRegion(Regions.fromName(awsCognitoRegion)));
                
        return cognitoClient;
	}

}
