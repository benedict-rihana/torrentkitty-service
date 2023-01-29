package benedict.zhang.torrentkitty.http.client.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TorrentKittyRequest {

    private String searchKey;

    private Integer pageNumber;

}
