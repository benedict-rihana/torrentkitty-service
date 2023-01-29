package benedict.zhang.torrentkitty.service;

import benedict.zhang.torrentkitty.http.client.datamodel.TorrentKittyResponse;

public interface ISearchService {

    public TorrentKittyResponse search(String key, Integer pageNumber) throws Exception;
}
