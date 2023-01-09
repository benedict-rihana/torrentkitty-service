package benedict.zhang.torrentkitty.parser;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;

import java.util.List;

public interface ISearchResultParser {

    public List<ISearchResult> parse(String body);
}
