package benedict.zhang.torrentkitty.http.client.impl;

import benedict.zhang.torrentkitty.http.client.ITorrentKittyClient;
import benedict.zhang.torrentkitty.http.client.datamodel.TorrentKittyRequest;
import benedict.zhang.torrentkitty.http.client.datamodel.TorrentKittyResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TorrentKittyClientImpl implements ITorrentKittyClient {

    private final ITorrentKittyClient delegate;

    public TorrentKittyClientImpl(ITorrentKittyClient delegate){
        this.delegate = delegate;
    }

    @Override
    public TorrentKittyResponse request(TorrentKittyRequest request) throws IOException {
        return delegate.request(request);
    }

}
