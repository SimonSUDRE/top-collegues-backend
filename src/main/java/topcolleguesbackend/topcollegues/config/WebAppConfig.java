package topcolleguesbackend.topcollegues.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan({ "topcolleguesbackend.topcollegues.controller", "topcolleguesbackend.topcollegues.web.listener" })
public class WebAppConfig {
	
}