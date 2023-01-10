package benedict.zhang.torrentkitty.service;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;

import java.util.List;

public interface ISearchService {

    public List<ISearchResult> search(String key, Integer pageNumber) throws Exception;
}
