package TCloud.RecordList;

import java.util.ArrayList;

public class Domain {
    //域名的 ID
    String id;
    //域名
    String name;
    //punycode编码后的域名
    String punycode;
    //域名的等级
    String grade;
    //域名所有者的邮箱帐号
    String owner;
    //域名扩展的状态信息，"notexist"、"dnserror"、"" 分别代表 "域名未注册"、"DNS 设置错误"、"正常"
    String ext_status;
    //域名下的解析记录默认的 TTL 值
    Integer ttl;
    //当前域名允许的最小的 TTL
    Integer min_ttl;
    //域名应该设置的 NS 地址
    ArrayList<String> dnspod_ns;
    //域名的状态，"enable"、"pause"、"spam"、"lock" 分别代表 "正常"、"暂停解析"、"已被封禁"、"已锁定"
    String status;
    //域名所在项目的 ID
    Integer q_project_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPunycode() {
        return punycode;
    }

    public void setPunycode(String punycode) {
        this.punycode = punycode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getExt_status() {
        return ext_status;
    }

    public void setExt_status(String ext_status) {
        this.ext_status = ext_status;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Integer getMin_ttl() {
        return min_ttl;
    }

    public void setMin_ttl(Integer min_ttl) {
        this.min_ttl = min_ttl;
    }

    public ArrayList<String> getDnspod_ns() {
        return dnspod_ns;
    }

    public void setDnspod_ns(ArrayList<String> dnspod_ns) {
        this.dnspod_ns = dnspod_ns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getQ_project_id() {
        return q_project_id;
    }

    public void setQ_project_id(Integer q_project_id) {
        this.q_project_id = q_project_id;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", punycode='" + punycode + '\'' +
                ", grade='" + grade + '\'' +
                ", owner='" + owner + '\'' +
                ", ext_status='" + ext_status + '\'' +
                ", ttl=" + ttl +
                ", min_ttl=" + min_ttl +
                ", dnspod_ns=" + dnspod_ns +
                ", status='" + status + '\'' +
                ", q_project_id=" + q_project_id +
                '}';
    }
}
