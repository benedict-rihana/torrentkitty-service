package benedict.zhang.torrentkitty.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

    public String baseUrl;

    @Autowired
    public ApplicationContext context;
    public Configuration() {
    }

}
