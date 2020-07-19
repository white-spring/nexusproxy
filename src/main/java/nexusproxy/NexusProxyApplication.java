package nexusproxy;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.Servlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NexusProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NexusProxyApplication.class, args);
    }
    @Bean
    public Servlet nexusProxyServlet(){
        return new ProxyServlet();
    }
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(nexusProxyServlet(), "/*");
        Map<String, String> params = new HashMap<>();
        params.put("targetUri", "http://localhost:8081");
        params.put("log", "true");
        registrationBean.setInitParameters(params);
        return registrationBean;
    }

}
