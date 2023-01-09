package benedict.zhang.torrentkitty.datamodel.impl;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResultImpl implements ISearchResult {

    private String title;

    private String magnetLink;

}
