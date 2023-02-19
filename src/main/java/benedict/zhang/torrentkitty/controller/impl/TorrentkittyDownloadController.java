package benedict.zhang.torrentkitty.controller.impl;

import benedict.zhang.torrentkitty.controller.ITorrentkittyController;
import benedict.zhang.torrentkitty.datamodel.IRequest;
import benedict.zhang.torrentkitty.datamodel.IResponse;
import benedict.zhang.torrentkitty.service.IDownloadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor_={@Autowired})
public class TorrentkittyDownloadController implements ITorrentkittyController {

    private IDownloadService downloadService;

    @Override
    public IResponse onAction(IRequest request) {
        return null;
    }
}
