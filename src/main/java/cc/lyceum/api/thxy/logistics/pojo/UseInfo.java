package cc.lyceum.api.thxy.logistics.pojo;

/**
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-08-02
 */
public class UseInfo {

    /**
     * 用户名
     */
    private String yhm;
    /**
     * 表号
     */
    private String bh;
    /**
     * 安装位置
     */
    private String azwz;
    /**
     * 年
     */
    private String n;
    /**
     * 月
     */
    private String y;
    /**
     * 期始表底
     */
    private String qsbd;
    /**
     * 期末表底
     */
    private String qmbd;
    /**
     * 倍率
     */
    private String bl;
    /**
     * 用电量
     */
    private String ydl;

    public UseInfo() {
    }

    public UseInfo(String yhm, String bh, String azwz, String n, String y, String qsbd, String qmbd, String bl, String ydl) {
        this.yhm = yhm;
        this.bh = bh;
        this.azwz = azwz;
        this.n = n;
        this.y = y;
        this.qsbd = qsbd;
        this.qmbd = qmbd;
        this.bl = bl;
        this.ydl = ydl;
    }

    @Override
    public String toString() {
        return "UseInfo{" +
                "用户名='" + yhm + '\'' +
                ", 表号='" + bh + '\'' +
                ", 安装位置='" + azwz + '\'' +
                ", 年='" + n + '\'' +
                ", 月='" + y + '\'' +
                ", 期始表底='" + qsbd + '\'' +
                ", 期末表底='" + qmbd + '\'' +
                ", 倍率='" + bl + '\'' +
                ", 用电量='" + ydl + '\'' +
                '}';
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getAzwz() {
        return azwz;
    }

    public void setAzwz(String azwz) {
        this.azwz = azwz;
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

    public String getQsbd() {
        return qsbd;
    }

    public void setQsbd(String qsbd) {
        this.qsbd = qsbd;
    }

    public String getQmbd() {
        return qmbd;
    }

    public void setQmbd(String qmbd) {
        this.qmbd = qmbd;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public String getYdl() {
        return ydl;
    }

    public void setYdl(String ydl) {
        this.ydl = ydl;
    }
}
