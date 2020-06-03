package TCloud.DeleteRecord;

import TCloud.CommonResponse;
import TCloud.RecordList.RecordListResponse;
import TCloud.TCloudRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Optional;

public class DeleteRecordRequest extends TCloudRequest {
    public DeleteRecordRequest(String secId, String secKey, String domain, Integer recordId) {
        super(secId, secKey);
        this.setAction("RecordDelete");
        this.requestArgs.put("recordId", recordId.toString());
        this.requestArgs.put("domain", domain);
    }

    public void deleteRecord() throws Exception {
        String responseBody = this.doRequest();
        CommonResponse<Object> response = Optional.ofNullable(
                JSON.parseObject(responseBody,
                        new TypeReference<CommonResponse<Object>>(){}))
                .orElseThrow(() -> new Exception("delete record request error."));
        if (response.getCode() != 0) {
            throw new Exception(response.toString());
        }
    }
}
