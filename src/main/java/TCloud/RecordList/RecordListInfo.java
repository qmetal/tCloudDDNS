package TCloud.RecordList;

public class RecordListInfo {
    //解析记录的总数
    String sub_domains;
    //有过滤条件，当前实际返回的记录条数
    String record_total;

    public String getSub_domains() {
        return sub_domains;
    }

    public void setSub_domains(String sub_domains) {
        this.sub_domains = sub_domains;
    }

    public String getRecord_total() {
        return record_total;
    }

    public void setRecord_total(String record_total) {
        this.record_total = record_total;
    }

    @Override
    public String toString() {
        return "RecordListInfo{" +
                "sub_domains='" + sub_domains + '\'' +
                ", record_total='" + record_total + '\'' +
                '}';
    }
}
