package benedict.zhang.torrentkitty.aspect;

import benedict.zhang.torrentkitty.datamodel.impl.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@SuppressWarnings("unused")
@Slf4j
public class ControllerAspectLogger {

    @Pointcut(value = "execution ( * benedict.zhang.torrentkitty.controller.impl.*.*(..) )")
    public void controllerRequested() {

    }

    @Before("controllerRequested()")
    public void loggingBefore(JoinPoint joinPoint) {
        log.info("REQUEST " + JoinPoint(joinPoint));
        //log.info("request for " + request.toString());
    }

    @AfterReturning(value = "controllerRequested()", returning = "response")
    public void loggingReturn(JoinPoint joinPoint, Object response) {
        log.info("RESPONSE:" + ReturnObjectInfo(response));
    }

    @AfterThrowing(value = "controllerRequested()", throwing = "exception")
    public void loggingException(JoinPoint joinPoint, Exception exception) {
        log.error("EXCEPTION OCCUR ON " + JoinPoint(joinPoint), exception);
    }

    @After(value = "controllerRequested()")
    public void loggingFinallyAfter(JoinPoint joinPoint){
        log.info("REQUEST " + JoinPoint(joinPoint) + " FINISHED");
    }

    private String JoinPoint(JoinPoint joinPoint) {
        final var params = Arrays.stream(joinPoint.getArgs()).map(Object::toString).toList();
        final var paramStr = String.join(",", params);
        final var method = joinPoint.getSignature().getName();
        return "[" + method + "] with " + paramStr;
    }


    @SuppressWarnings("rawtypes")
    private String ReturnObjectInfo(Object retObj) {
        if (retObj instanceof final ResponseEntity responseEntity) {
            return ResponseEntityInfo(responseEntity);
        }
        return retObj.toString();
    }

    @SuppressWarnings("rawtypes")
    private String ResponseEntityInfo(ResponseEntity responseEntity) {
        final var ownerBody = responseEntity.getBody();
        final var msgBuilder = new StringBuilder(responseEntity.getStatusCode().toString());
        if (ownerBody instanceof SearchResponse) {
            msgBuilder.append(", ").append(SearchResponseInfo((SearchResponse) ownerBody));
        }
        return msgBuilder.toString();
    }

    private String SearchResponseInfo(SearchResponse searchResponse) {
        final var msgBuilder = new StringBuilder();
        final var status = searchResponse.getReponseStatus();
        final var torrentKittyResponse = searchResponse.getTorrentkittyResponse();
        msgBuilder.append("Response Status: ").append(status.toString());
        Optional.ofNullable(torrentKittyResponse).ifPresent(response -> {
            final var searchResult = response.getResults();
            final var pageNumber = response.getPageNumber();
            final var resultCount = searchResult.getResultList().size();
            final var resultStatus = searchResult.getResultCode().toString();
            msgBuilder.append(", PageNumber = ").append(pageNumber).append(", Result Code: ").append(resultStatus).append(", Total Results: ").append(resultCount);
        });
        return msgBuilder.toString();
    }
}
