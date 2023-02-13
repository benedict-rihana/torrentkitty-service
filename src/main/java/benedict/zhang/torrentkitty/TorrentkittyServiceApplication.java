package benedict.zhang.torrentkitty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${config.home}/config.properties")
@EnableAspectJAutoProxy
public class TorrentkittyServiceApplication {

    public static ConfigurableApplicationContext CONTEXT;

    public static void main(String[] args) {
        CONTEXT = SpringApplication.run(TorrentkittyServiceApplication.class, args);
    }

}