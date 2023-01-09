package benedict.zhang.torrentkitty.controller;

import benedict.zhang.torrentkitty.datamodel.IRequest;
import benedict.zhang.torrentkitty.datamodel.IResponse;

public interface ITorrentkittyController {

    public IResponse onAction(IRequest request);
}
