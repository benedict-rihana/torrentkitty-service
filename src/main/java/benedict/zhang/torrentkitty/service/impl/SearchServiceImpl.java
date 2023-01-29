package benedict.zhang.torrentkitty.service.impl;

import benedict.zhang.torrentkitty.http.client.ITorrentKittyClient;
import benedict.zhang.torrentkitty.http.client.datamodel.TorrentKittyRequest;
import benedict.zhang.torrentkitty.http.client.datamodel.TorrentKittyResponse;
import benedict.zhang.torrentkitty.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private ITorrentKittyClient torrentKittyClientImpl;
    @Override
    public TorrentKittyResponse search(String key, Integer pageNumber) throws Exception {
        final var request = new TorrentKittyRequest(key,pageNumber);
        return torrentKittyClientImpl.request(request);
    }
}
