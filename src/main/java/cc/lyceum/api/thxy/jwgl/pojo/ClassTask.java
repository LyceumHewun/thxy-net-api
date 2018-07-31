package cc.lyceum.api.thxy.jwgl.pojo;

/**
 * 上课任务实体类
 *
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-07-30
 */
public class ClassTask {

    private String cjr;
    private String cjsj;
    /**
     * 教学班名称
     */
    private String jxbmc;
    /**
     * 课程编码
     */
    private String kcbh;
    /**
     * 课程大类
     */
    private String kcdlmc;
    private String kcflmc;
    /**
     * 课程名称
     */
    private String kcmc;
    /**
     * 课程任务代码
     */
    private String kcrwdm;
    /**
     * 考核方式
     */
    private String khfsmc;
    private String rownum_;
    /**
     * 修读方式
     */
    private String xdfsmc;
    /**
     * 网考号
     */
    private String xid;
    private String xmmc;
    /**
     * 总学时
     */
    private String zxs;

    @Override
    public String toString() {
        return "ClassTask{" +
                "cjr='" + cjr + '\'' +
                ", cjsj='" + cjsj + '\'' +
                ", jxbmc='" + jxbmc + '\'' +
                ", kcbh='" + kcbh + '\'' +
                ", kcdlmc='" + kcdlmc + '\'' +
                ", kcflmc='" + kcflmc + '\'' +
                ", kcmc='" + kcmc + '\'' +
                ", kcrwdm='" + kcrwdm + '\'' +
                ", khfsmc='" + khfsmc + '\'' +
                ", rownum_='" + rownum_ + '\'' +
                ", xdfsmc='" + xdfsmc + '\'' +
                ", xid='" + xid + '\'' +
                ", xmmc='" + xmmc + '\'' +
                ", zxs='" + zxs + '\'' +
                '}';
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getJxbmc() {
        return jxbmc;
    }

    public void setJxbmc(String jxbmc) {
        this.jxbmc = jxbmc;
    }

    public String getKcbh() {
        return kcbh;
    }

    public void setKcbh(String kcbh) {
        this.kcbh = kcbh;
    }

    public String getKcdlmc() {
        return kcdlmc;
    }

    public void setKcdlmc(String kcdlmc) {
        this.kcdlmc = kcdlmc;
    }

    public String getKcflmc() {
        return kcflmc;
    }

    public void setKcflmc(String kcflmc) {
        this.kcflmc = kcflmc;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getKcrwdm() {
        return kcrwdm;
    }

    public void setKcrwdm(String kcrwdm) {
        this.kcrwdm = kcrwdm;
    }

    public String getKhfsmc() {
        return khfsmc;
    }

    public void setKhfsmc(String khfsmc) {
        this.khfsmc = khfsmc;
    }

    public String getRownum_() {
        return rownum_;
    }

    public void setRownum_(String rownum_) {
        this.rownum_ = rownum_;
    }

    public String getXdfsmc() {
        return xdfsmc;
    }

    public void setXdfsmc(String xdfsmc) {
        this.xdfsmc = xdfsmc;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getZxs() {
        return zxs;
    }

    public void setZxs(String zxs) {
        this.zxs = zxs;
    }
}
