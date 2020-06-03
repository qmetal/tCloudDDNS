package TCloud.AddRecord;

import TCloud.CommonResponse;
import TCloud.TCloudRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Optional;

public class AddRecordRequest extends TCloudRequest {
    public AddRecordRequest(String secId,
                            String secKey,
                            String domain,
                            String subDomain,
                            String recordType,
                            String ipAddress
    ) {
        super(secId, secKey);
        this.setAction("RecordCreate");
//        this.setAction("RecordList");
        this.requestArgs.put("domain", domain);
        this.requestArgs.put("subDomain", subDomain);
        this.requestArgs.put("recordType", recordType);
        this.requestArgs.put("recordLine", "默认");
        this.requestArgs.put("value", ipAddress);

        System.out.println(this.requestArgs);
    }

    public void addRecord() throws Exception {
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
