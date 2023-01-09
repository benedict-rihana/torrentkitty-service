package benedict.zhang.torrentkitty.http.client;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;

import java.io.IOException;
import java.util.List;

public interface ITorrentKittyClient {

    public List<ISearchResult> request(String url) throws IOException;

}
