package benedict.zhang.torrentkitty.http.client.datamodel;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Data
public class TorrentKittyResponse {

    private Exception exception;

    private ResponseStatus responseStatus;

    private Integer rawHttpStatusCode;

    private List<ISearchResult> resultList;

    private Integer pageNumber;

    private Boolean hasNext;

    @Setter(AccessLevel.NONE) // Read-only
    private Boolean hasPrevious;

    private TorrentKittyResponse(Integer rawHttpStatusCode,Exception e){
        this.rawHttpStatusCode = rawHttpStatusCode;
        Optional.ofNullable(e).ifPresent(this::setException);
        init();
    }

    private void init(){
        switch (rawHttpStatusCode) {
            case 200 -> this.responseStatus = ResponseStatus.OK;
            case -1 -> this.responseStatus = ResponseStatus.Exception;
            default -> this.responseStatus = ResponseStatus.Failed;
        }
    }

    public static TorrentKittyResponse createResponse(Integer httpStatus){
        return new TorrentKittyResponse(httpStatus,null);
    }

    public static TorrentKittyResponse createExceptionResponse(Integer httpStatus, Exception e){
        return new TorrentKittyResponse(httpStatus,e);
    }

    public Boolean getHasPrevious(){
        return pageNumber > 1;
    }
}
