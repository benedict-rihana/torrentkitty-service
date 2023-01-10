package benedict.zhang.torrentkitty.http.client.impl;

import benedict.zhang.torrentkitty.datamodel.ISearchResult;
import benedict.zhang.torrentkitty.http.client.ITorrentKittyClient;
import benedict.zhang.torrentkitty.parser.ISearchResultParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OkttpTorrentKtClientImpl implements ITorrentKittyClient {

    private final OkHttpClient client;

    @Autowired
    private ISearchResultParser parser;

    public OkttpTorrentKtClientImpl() {
        client = new OkHttpClient();
    }

    @Override
    public List<ISearchResult> request(String url) throws IOException {
        var request = (new Request.Builder()).url(url).build();
        try (var response = client.newCall(request).execute()) {
            final var code = response.code();
            try (final var body = response.body()) {
                return Optional.ofNullable(body).map(resp -> {
                    try {
                        return parser.parse(resp.string());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new ArrayList<ISearchResult>();
                    }
                }).orElseGet(ArrayList::new);
            }
        }
    }
}
