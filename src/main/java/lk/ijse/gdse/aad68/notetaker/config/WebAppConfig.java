package lk.ijse.gdse.aad68.notetaker.config;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse.aad68.notetaker")
@EnableWebMvc
@EnableJpaRepositories(basePackages = "lk.ijse.gdse.aad68.notetaker")
@EnableTransactionManagement
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  //(2MB) //file ekak upload krata passe
        maxFileSize = 1024 * 1024 * 10, //(10MB)  //upload karana file eke size eka
        maxRequestSize = 1024 * 1024 *50   //(50MB)  //upload karana file eke size eka athluwa mulu mulipart request eke pramanaya
)
public class WebAppConfig {

}

