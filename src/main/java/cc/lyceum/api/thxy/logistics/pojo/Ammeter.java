package cc.lyceum.api.thxy.logistics.pojo;

/**
 * @author Lyceum Huwen
 * @version 1.0.0
 * @date 2018-08-02
 */
public class Ammeter {

    /**
     * 用户名
     */
    private String yhm;
    /**
     * 用户编号
     */
    private String yhbh;
    /**
     * 总用电量
     */
    private String zydl;
    /**
     * 余电
     */
    private String yd;
    /**
     * 抄表时间
     */
    private String cbsj;

    public Ammeter() {
    }

    public Ammeter(String yhm, String yhbh, String zydl, String yd, String cbsj) {
        this.yhm = yhm;
        this.yhbh = yhbh;
        this.zydl = zydl;
        this.yd = yd;
        this.cbsj = cbsj;
    }

    @Override
    public String toString() {
        return "Ammeter{" +
                "用户名='" + yhm + '\'' +
                ", 用户编号='" + yhbh + '\'' +
                ", 总用电量='" + zydl + '\'' +
                ", 余电='" + yd + '\'' +
                ", 抄表时间='" + cbsj + '\'' +
                '}';
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getZydl() {
        return zydl;
    }

    public void setZydl(String zydl) {
        this.zydl = zydl;
    }

    public String getYd() {
        return yd;
    }

    public void setYd(String yd) {
        this.yd = yd;
    }

    public String getCbsj() {
        return cbsj;
    }

    public void setCbsj(String cbsj) {
        this.cbsj = cbsj;
    }
}
