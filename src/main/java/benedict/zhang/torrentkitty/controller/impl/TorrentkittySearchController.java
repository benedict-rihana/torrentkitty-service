package benedict.zhang.torrentkitty.controller.impl;

import benedict.zhang.torrentkitty.controller.ITorrentkittyController;
import benedict.zhang.torrentkitty.datamodel.IRequest;
import benedict.zhang.torrentkitty.datamodel.IResponse;
import benedict.zhang.torrentkitty.datamodel.Status;
import benedict.zhang.torrentkitty.datamodel.impl.SearchRequest;
import benedict.zhang.torrentkitty.datamodel.impl.SearchResponse;
import benedict.zhang.torrentkitty.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TorrentkittySearchController implements ITorrentkittyController {

    @Autowired
    private ISearchService searchService;

    @Override
    public IResponse onAction(IRequest request) {
        try{
            final var results = searchService.search(((SearchRequest)request).getSearchKey());
            final var response = SearchResponse.createSuccessResponse();
            response.withResults(results);
            return response;
        }catch (Exception ioException){
            ioException.printStackTrace();
            return SearchResponse.createFailedResponse();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{key}")
    public ResponseEntity<SearchResponse> search(@PathVariable("key")String key){
        final var response = (SearchResponse)onAction(SearchRequest.createSearchRequest(key));
        if (response.getReponseStatus() == Status.SUCCESS){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
        }
    }
}
