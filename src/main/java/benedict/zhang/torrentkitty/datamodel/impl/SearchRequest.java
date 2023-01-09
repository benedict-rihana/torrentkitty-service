package benedict.zhang.torrentkitty.datamodel.impl;

import benedict.zhang.torrentkitty.datamodel.IRequest;
import lombok.Data;

@Data
public class SearchRequest implements IRequest {
    private String searchKey;

    private SearchRequest(String key){
        this.searchKey = key;
    }

    public static SearchRequest createSearchRequest(String key){
        final var request = new SearchRequest(key);
        return request;
    }
}
