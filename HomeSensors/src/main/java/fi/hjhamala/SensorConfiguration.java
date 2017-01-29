package fi.hjhamala;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableConfigurationProperties(HomeSensorsProperties.class)
@EnableAsync
@EnableScheduling
public class SensorConfiguration {
	
	@Autowired
	HomeSensorsProperties properties;
	
	@Bean
	public JavaMailSenderImpl mailSender() {
	    JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
	    javaMailSenderImpl.setHost("smtp.gmail.com");
	    javaMailSenderImpl.setPort(587);
	    javaMailSenderImpl.setUsername(properties.getEmailAddress());
	    javaMailSenderImpl.setPassword(properties.getEmailPassword());
	    javaMailSenderImpl.setJavaMailProperties(javaMailProperties());
	    return javaMailSenderImpl;
	}

	public Properties javaMailProperties(){
	    Properties properties = new Properties();
	    // add more properties in the same way
	    properties.put("mail.transport.protocol", "smtp");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.debug", "true");
	    return properties;
	}
	
}
