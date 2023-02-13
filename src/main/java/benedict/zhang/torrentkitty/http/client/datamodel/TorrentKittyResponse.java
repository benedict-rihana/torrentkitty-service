package benedict.zhang.torrentkitty.http.client.datamodel;

import benedict.zhang.torrentkitty.parser.impl.ParseResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Optional;

@Data
public class TorrentKittyResponse {

    @JsonIgnore
    private Exception exception;

    private ResponseStatus responseStatus;

    private Integer rawHttpStatusCode;

    private ParseResult results;

    private Integer pageNumber;

    @Setter(AccessLevel.NONE)
    private Boolean hasNext;

    @Setter(AccessLevel.NONE) // Read-only
    private Boolean hasPrevious;

    private TorrentKittyResponse(TorrentKittyRequest request, Integer rawHttpStatusCode,Exception e){
        this.pageNumber = Optional.ofNullable(request.getPageNumber()).orElse(1);
        this.rawHttpStatusCode = rawHttpStatusCode;
        Optional.ofNullable(e).ifPresent(this::setException);
        init();
    }

    private void init(){
        switch (rawHttpStatusCode) {
            case 200 -> this.responseStatus = ResponseStatus.OK;
            case -1 -> {
                this.responseStatus = ResponseStatus.Exception;
                results = ParseResult.createFailedResult();
            }
            default -> {
                this.responseStatus = ResponseStatus.Failed;
                results = ParseResult.createFailedResult();
            }
        }
    }

    public static TorrentKittyResponse createResponseForRequest(TorrentKittyRequest request, Integer httpStatus){
        return new TorrentKittyResponse(request, httpStatus,null);
    }

    public static TorrentKittyResponse createExceptionResponseForRequest(TorrentKittyRequest request,Integer httpStatus, Exception e){
        return new TorrentKittyResponse(request, httpStatus,e);
    }

    public Boolean getHasPrevious(){
        return pageNumber > 1;
    }

    public Boolean getHasNext(){
       return  Optional.ofNullable(results.getResultList()).map(
               resultList -> resultList.size() == TorrentkittyConfig.getInstance().getMaxCount()
       ).orElse(false);
    }

    public String getErrMsg(){
        return Optional.ofNullable(exception).map(Exception::getMessage).orElseGet(String::new);
    }
}
