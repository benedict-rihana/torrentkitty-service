package benedict.zhang.torrentkitty.datamodel.impl;

import benedict.zhang.torrentkitty.datamodel.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRequest implements IRequest {
    private String searchKey;
    private Integer pageNumber;


    public static SearchRequest createSearchRequest(String key){
        //final var request = new SearchRequest(key,1);
        return new SearchRequest(key,1);
    }

    public static SearchRequest createSearchRequest(String key, Integer pageNumber){
        return new SearchRequest(key,pageNumber);
    }
}
