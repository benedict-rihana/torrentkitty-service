package benedict.zhang.torrentkitty.service.impl;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;
import benedict.zhang.torrentkitty.http.client.ITorrentKittyClient;
import benedict.zhang.torrentkitty.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SearchServiceImpl implements ISearchService {

    @Value("${torrentkitty.search.api}")
    private String api;
    @Autowired
    private ITorrentKittyClient torrentKittyClientImpl;
    @Override
    public List<ISearchResult> search(String key, Integer pageNumber) throws Exception {
        final var url = Optional.ofNullable(pageNumber).map(number->{
            return api + "/" + key + "/" + number;
        }).orElseGet(()->{return api+"/"+key;});
        return torrentKittyClientImpl.request(url);
    }
}
