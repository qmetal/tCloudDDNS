package TCloud.RecordList;

import java.util.ArrayList;

public class RecordListResponse {
    Domain domain;
    RecordListInfo info;
    ArrayList<DnsRecord> records;

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public RecordListInfo getInfo() {
        return info;
    }

    public void setInfo(RecordListInfo info) {
        this.info = info;
    }

    public ArrayList<DnsRecord> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<DnsRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "RecordListResponse{" +
                "domain=" + domain +
                ", info=" + info +
                ", records=" + records +
                '}';
    }
}
