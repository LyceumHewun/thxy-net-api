package cc.lyceum.api.thxy.logistics.pojo;

/**
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-08-02
 */
public class FreeInfo {

    /**
     * 用户编号
     */
    private String yhbh;
    /**
     * 用户名
     */
    private String yhm;
    /**
     * 用户地址
     */
    private String yhdz;
    /**
     * 人数
     */
    private String rs;
    /**
     * 年
     */
    private String n;
    /**
     * 月
     */
    private String y;
    /**
     * 基础电量
     */
    private String jcdl;
    /**
     * 补助电量
     */
    private String bzdl;
    /**
     * 追补原因
     */
    private String zbyy;

    public FreeInfo() {
    }

    public FreeInfo(String yhbh, String yhm, String yhdz, String rs, String n, String y, String jcdl, String bzdl, String zbyy) {
        this.yhbh = yhbh;
        this.yhm = yhm;
        this.yhdz = yhdz;
        this.rs = rs;
        this.n = n;
        this.y = y;
        this.jcdl = jcdl;
        this.bzdl = bzdl;
        this.zbyy = zbyy;
    }

    @Override
    public String toString() {
        return "FreeInfo{" +
                "用户编号='" + yhbh + '\'' +
                ", 用户名='" + yhm + '\'' +
                ", 用户地址='" + yhdz + '\'' +
                ", 人数='" + rs + '\'' +
                ", 年='" + n + '\'' +
                ", 月='" + y + '\'' +
                ", 基础电量='" + jcdl + '\'' +
                ", 补助电量='" + bzdl + '\'' +
                ", 追补原因='" + zbyy + '\'' +
                '}';
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getYhdz() {
        return yhdz;
    }

    public void setYhdz(String yhdz) {
        this.yhdz = yhdz;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getJcdl() {
        return jcdl;
    }

    public void setJcdl(String jcdl) {
        this.jcdl = jcdl;
    }

    public String getBzdl() {
        return bzdl;
    }

    public void setBzdl(String bzdl) {
        this.bzdl = bzdl;
    }

    public String getZbyy() {
        return zbyy;
    }

    public void setZbyy(String zbyy) {
        this.zbyy = zbyy;
    }
}
