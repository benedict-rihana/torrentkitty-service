package benedict.zhang.torrentkitty.http.client;

import benedict.zhang.torrentkitty.http.client.datamodel.TorrentKittyRequest;
import benedict.zhang.torrentkitty.http.client.datamodel.TorrentKittyResponse;

import java.io.IOException;

public interface ITorrentKittyClient {

    public TorrentKittyResponse request(TorrentKittyRequest request) throws IOException;

}
