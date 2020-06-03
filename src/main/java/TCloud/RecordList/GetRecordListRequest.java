package TCloud.RecordList;

import TCloud.CommonResponse;
import TCloud.TCloudRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Optional;

public class GetRecordListRequest extends TCloudRequest {
    public GetRecordListRequest(String secId, String secKey, String domain) {
        super(secId, secKey);
        requestArgs.put("domain", domain);
        this.setAction("RecordList");
    }
    public GetRecordListRequest(String secId, String secKey, String domain, String subDomain, String recordType) {
        super(secId, secKey);
        requestArgs.put("domain", domain);
        this.setAction("RecordList");
        requestArgs.put("subDomain", subDomain);
        requestArgs.put("recordType", recordType);
    }


    public RecordListResponse getRecordList() throws Exception {
        String responseBody = this.doRequest();
        CommonResponse<RecordListResponse> response = Optional.ofNullable(
                JSON.parseObject(responseBody,
                        new TypeReference<CommonResponse<RecordListResponse>>(){}))
                .orElseThrow(() -> new Exception("Request error."));
        if (response.getCode() != 0) {
            throw new Exception(response.toString());
        }

        return response.getData();
    }
}
