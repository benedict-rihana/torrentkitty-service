package benedict.zhang.torrentkitty.parser.impl;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;
import benedict.zhang.torrentkitty.datamodel.impl.SearchResultImpl;
import benedict.zhang.torrentkitty.parser.ISearchResultParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class JSoupSearchResultParser implements ISearchResultParser {

    @Override
    public List<ISearchResult> parse(String body) {
        final var doc = Jsoup.parse(body);
        final var resultDoc = doc.getElementById(ParserConstants.SEARCH_RESULT_TABLE_ID);
        return Optional.ofNullable(resultDoc)
                .map(this::parseResultTable)
                .orElseGet(this::createParseFailResult);
    }

    private List<ISearchResult> parseResultTable(Element resultTableElement){
        return resultTableElement.getElementsByTag(ParserConstants.RESULT_ROW_TAG).stream()
                .filter(this::isResultRow)
                .map(this::parseResultRow).filter(Objects::nonNull).toList();
    }

    private ISearchResult parseResultRow(Element row){
        final var actionColumn = row.select(ParserConstants.LINK_COLUMN_CLASS).first();
        return Optional.ofNullable(actionColumn).map(Element::children).map(elements -> {
            final var resultColumn = elements.stream().filter(this::isActionColumn).findFirst();
            return resultColumn.map(this::parseActionColumn).orElse(null);
        }).orElse(null);
    }

    private ISearchResult parseActionColumn(Element column){
        final var title = column.attr(ParserConstants.TITLE_ATTR);
        final var magnetLink = column.attr(ParserConstants.MAGNET_ATTR);
        return new SearchResultImpl(title,magnetLink);
    }
    private Boolean isResultRow(Element row){
        return row.getElementsByTag(ParserConstants.RESULT_HEADER_TAG).size() == 0;
    }

    private Boolean isActionColumn(Element column){
        return column.attributes().hasDeclaredValueForKey(ParserConstants.ACTION_COLUMN_ATTR_KEY) &&
                ParserConstants.ACTION_COLUMN_ATTR_VALUE.equals(column.attr(ParserConstants.ACTION_COLUMN_ATTR_KEY));
    }

    private List<ISearchResult> createParseFailResult(){
        return new ArrayList<>(0);
    }
}
