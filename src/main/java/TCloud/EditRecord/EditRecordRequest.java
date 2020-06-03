package TCloud.EditRecord;

import TCloud.CommonResponse;
import TCloud.TCloudRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Optional;

public class EditRecordRequest extends TCloudRequest {
    public EditRecordRequest(String secId,
                             String secKey,
                             String domain,
                             Integer recordId,
                             String subDomain,
                             String recordType,
                             String ipAddress
    ) {
        super(secId, secKey);
        this.setAction("RecordModify");
        this.requestArgs.put("domain", domain);
        this.requestArgs.put("subDomain", subDomain);
        this.requestArgs.put("recordId", recordId.toString());
        this.requestArgs.put("recordType", recordType);
        this.requestArgs.put("recordLine", "默认");
        this.requestArgs.put("value", ipAddress);
    }

    public void editRecord() throws Exception {
        String responseBody = this.doRequest();
        CommonResponse<Object> response = Optional.ofNullable(
                JSON.parseObject(responseBody,
                        new TypeReference<CommonResponse<Object>>(){}))
                .orElseThrow(() -> new Exception("edit record request error."));
        if (response.getCode() != 0) {
            throw new Exception(response.toString());
        }
    }
}
