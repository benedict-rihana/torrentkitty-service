package benedict.zhang.torrentkitty.http.client.impl;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;
import benedict.zhang.torrentkitty.http.client.ITorrentKittyClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class TorrentKittyClientImpl implements ITorrentKittyClient {

    private final ITorrentKittyClient delegate;

    public TorrentKittyClientImpl(ITorrentKittyClient delegate){
        this.delegate = delegate;
    }

    @Override
    public List<ISearchResult> request(String url) throws IOException {
        return delegate.request(url);
    }

}
