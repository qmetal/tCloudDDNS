package TCloud.RecordList;

public class DnsRecord {
    //domain, subDomain, recordType, recordLine, value
    Integer id;
    Integer ttl;
    String value;
    Integer enabled;
    String updated_on;
    String q_project_id;
    String name;
    String line;
    String line_id;
    String type;
    String remark;
    String mx;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }

    public String getQ_project_id() {
        return q_project_id;
    }

    public void setQ_project_id(String q_project_id) {
        this.q_project_id = q_project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMx() {
        return mx;
    }

    public void setMx(String mx) {
        this.mx = mx;
    }

    @Override
    public String toString() {
        return "DnsRecord{" +
                "id=" + id +
                ", ttl=" + ttl +
                ", value='" + value + '\'' +
                ", enabled=" + enabled +
                ", updated_on='" + updated_on + '\'' +
                ", q_project_id='" + q_project_id + '\'' +
                ", name='" + name + '\'' +
                ", line='" + line + '\'' +
                ", line_id='" + line_id + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", mx='" + mx + '\'' +
                '}';
    }
}
