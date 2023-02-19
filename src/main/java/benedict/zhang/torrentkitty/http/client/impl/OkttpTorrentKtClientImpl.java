package benedict.zhang.torrentkitty.http.client.impl;

import benedict.zhang.torrentkitty.http.client.ITorrentKittyClient;
import benedict.zhang.torrentkitty.http.client.datamodel.TorrentKittyRequest;
import benedict.zhang.torrentkitty.http.client.datamodel.TorrentKittyResponse;
import benedict.zhang.torrentkitty.parser.ISearchResultParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class OkttpTorrentKtClientImpl implements ITorrentKittyClient {

    private final OkHttpClient client;

    //@Value("${torrentkitty.search.api}")
    private String api;

    private ISearchResultParser parser;

    @Autowired
    public OkttpTorrentKtClientImpl(ISearchResultParser parser, @Value("${torrentkitty.search.api}") String api) {
        this.api = api;
        this.parser = parser;
        client = new OkHttpClient();
    }

    @Override
    public TorrentKittyResponse request(TorrentKittyRequest torrentKittyRequest) throws IOException {
        final var pageNumber = torrentKittyRequest.getPageNumber();
        final var searchKey = torrentKittyRequest.getSearchKey();
        final var url = Optional.ofNullable(pageNumber)
                .map(number -> api + "/" + searchKey + "/" + number)
                .orElseGet(() -> api + "/" + searchKey);
        var request = (new Request.Builder()).url(url).build();
        try (var response = client.newCall(request).execute()) {
            final var code = response.code();
            try (final var body = response.body()) {
                return Optional.ofNullable(body).map(resp -> {
                    try {
                        final var parseResult = parser.parse(resp.string());
                        final var torrentKittyResponse = TorrentKittyResponse.createResponseForRequest(torrentKittyRequest, code);
                        torrentKittyResponse.setResults(parseResult);
                        return torrentKittyResponse;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return TorrentKittyResponse.createExceptionResponseForRequest(torrentKittyRequest, code, e);
                    }
                }).orElseGet(() -> TorrentKittyResponse.createExceptionResponseForRequest(torrentKittyRequest, code, null));
            }
        }
    }
}
