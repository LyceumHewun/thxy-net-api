package cc.lyceum.api.thxy.logistics.pojo;

/**
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-08-02
 */
public class PaymentRecord {

    /**
     * 用户名
     */
    private String yhm;
    /**
     * 缴费时间
     */
    private String jfsj;
    /**
     * 缴费金额
     */
    private String jfje;
    /**
     * 总预购电量
     */
    private String zygdl;
    /**
     * 上次余量
     */
    private String scyl;
    /**
     * 本次预购电量
     */
    private String bcygdl;

    public PaymentRecord() {
    }

    public PaymentRecord(String yhm, String jfsj, String jfje, String zygdl, String scyl, String bcygdl) {
        this.yhm = yhm;
        this.jfsj = jfsj;
        this.jfje = jfje;
        this.zygdl = zygdl;
        this.scyl = scyl;
        this.bcygdl = bcygdl;
    }

    @Override
    public String toString() {
        return "PaymentRecord{" +
                "用户名='" + yhm + '\'' +
                ", 缴费时间='" + jfsj + '\'' +
                ", 缴费金额='" + jfje + '\'' +
                ", 总预购电量='" + zygdl + '\'' +
                ", 上次余量='" + scyl + '\'' +
                ", 本次预购电量='" + bcygdl + '\'' +
                '}';
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getJfsj() {
        return jfsj;
    }

    public void setJfsj(String jfsj) {
        this.jfsj = jfsj;
    }

    public String getJfje() {
        return jfje;
    }

    public void setJfje(String jfje) {
        this.jfje = jfje;
    }

    public String getZygdl() {
        return zygdl;
    }

    public void setZygdl(String zygdl) {
        this.zygdl = zygdl;
    }

    public String getScyl() {
        return scyl;
    }

    public void setScyl(String scyl) {
        this.scyl = scyl;
    }

    public String getBcygdl() {
        return bcygdl;
    }

    public void setBcygdl(String bcygdl) {
        this.bcygdl = bcygdl;
    }
}
