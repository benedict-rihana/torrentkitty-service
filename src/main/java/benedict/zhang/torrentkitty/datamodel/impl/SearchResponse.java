package benedict.zhang.torrentkitty.datamodel.impl;

import benedict.zhang.torrentkitty.datamodel.IResponse;
import benedict.zhang.torrentkitty.datamodel.ISearchResult;
import benedict.zhang.torrentkitty.datamodel.Status;
import benedict.zhang.torrentkitty.parser.ISearchResultParser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class SearchResponse implements IResponse {

    @JsonIgnore
    private final Status status;

    private List<ISearchResult> resultList;

    private SearchResponse(Status status){
        this.status = status;
    }
    @Override
    public Status getReponseStatus() {
        return status;
    }

    public static SearchResponse createSuccessResponse(){
        final var res = new SearchResponse(Status.SUCCESS);
        return res;
    }

    public static SearchResponse createFailedResponse(){
        final var res = new SearchResponse(Status.FAILED);
        return res;
    }

    public void withResults(List<ISearchResult> results){
        this.resultList = results;
    }

//    public void withHttpResponse(String httpResponse){
//        if (Objects.requireNonNull(status) == Status.SUCCESS) {
//            parse(httpResponse);
//        }
//    }
//
//    public void withException(Exception exception){
//        if (Objects.requireNonNull(status) == Status.FAILED) {
//            failed(exception);
//        }
//    }
//
//    public void parse(String httpResponse){
//
//    }
//
//    public void failed(Exception exception){
//
//    }

}
