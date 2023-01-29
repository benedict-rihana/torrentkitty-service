package benedict.zhang.torrentkitty.parser.impl;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParseResult {

    private List<ISearchResult> resultList;

    private ResultCode resultCode;

    private ParseResult(ResultCode resultCode, List<ISearchResult> resultList){
        this.resultCode = resultCode;
        this.resultList = resultList;
    }

    public static ParseResult createFailedResult(){
        return new ParseResult(ResultCode.SERVER_ERR, new ArrayList<>(0));
    }

    public static ParseResult createParseResult(List<ISearchResult> resultList){
        if(resultList.isEmpty()){
            return new ParseResult(ResultCode.NO_HIT,resultList);
        }
        return new ParseResult(ResultCode.HIT, resultList);
    }
}
