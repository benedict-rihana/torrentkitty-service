package benedict.zhang.torrentkitty.http.client.datamodel;

import benedict.zhang.torrentkitty.TorrentkittyServiceApplication;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class TorrentkittyConfig {
    @Value("${torrentkitty.search.page.max.count}")
    private Integer maxCount;


    public static TorrentkittyConfig getInstance(){
        return TorrentkittyServiceApplication.CONTEXT.getBean(TorrentkittyConfig.class);
    }
}
