package benedict.zhang.torrentkitty.parser;

import benedict.zhang.torrentkitty.parser.impl.ParseResult;

public interface ISearchResultParser {

    public ParseResult parse(String body);
}
