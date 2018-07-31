package cc.lyceum.api.thxy.jwgl.pojo;

/**
 * 考试安排实体类
 *
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-07-30
 */
public class ExamArrange {

    private String ccdm;
    private String ccmc;
    private String cddm;
    /**
     * 对应星期
     */
    private String dyxq;
    /**
     * 对应周次
     */
    private String dyzc;
    /**
     * 考试id
     */
    private String examid;
    /**
     * 考试名称
     */
    private String examname;
    /**
     * 考试场地
     */
    private String jxcdmc;
    /**
     * 考试名称
     * 比 {@link ExamArrange#examname} 少"考试"两个字
     */
    private String kcmc;
    /**
     * 考试日期
     */
    private String ksrq;
    /**
     * 考试时长(/小时)
     */
    private String kssc;
    /**
     * 考试时间
     */
    private String kssj;
    /**
     * 考试性质
     */
    private String ksxz;
    private String pcmc;
    private String zkzh;
    private String zwh;
    /**
     * 考试节次(第几节)
     */
    private String zyjc;

    @Override
    public String toString() {
        return "ExamArrange{" +
                "ccdm='" + ccdm + '\'' +
                ", ccmc='" + ccmc + '\'' +
                ", cddm='" + cddm + '\'' +
                ", dyxq='" + dyxq + '\'' +
                ", dyzc='" + dyzc + '\'' +
                ", examid='" + examid + '\'' +
                ", examname='" + examname + '\'' +
                ", jxcdmc='" + jxcdmc + '\'' +
                ", kcmc='" + kcmc + '\'' +
                ", ksrq='" + ksrq + '\'' +
                ", kssc='" + kssc + '\'' +
                ", kssj='" + kssj + '\'' +
                ", ksxz='" + ksxz + '\'' +
                ", pcmc='" + pcmc + '\'' +
                ", zkzh='" + zkzh + '\'' +
                ", zwh='" + zwh + '\'' +
                ", zyjc='" + zyjc + '\'' +
                '}';
    }

    public String getCcdm() {
        return ccdm;
    }

    public void setCcdm(String ccdm) {
        this.ccdm = ccdm;
    }

    public String getCcmc() {
        return ccmc;
    }

    public void setCcmc(String ccmc) {
        this.ccmc = ccmc;
    }

    public String getCddm() {
        return cddm;
    }

    public void setCddm(String cddm) {
        this.cddm = cddm;
    }

    public String getDyxq() {
        return dyxq;
    }

    public void setDyxq(String dyxq) {
        this.dyxq = dyxq;
    }

    public String getDyzc() {
        return dyzc;
    }

    public void setDyzc(String dyzc) {
        this.dyzc = dyzc;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {
        this.examname = examname;
    }

    public String getJxcdmc() {
        return jxcdmc;
    }

    public void setJxcdmc(String jxcdmc) {
        this.jxcdmc = jxcdmc;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getKsrq() {
        return ksrq;
    }

    public void setKsrq(String ksrq) {
        this.ksrq = ksrq;
    }

    public String getKssc() {
        return kssc;
    }

    public void setKssc(String kssc) {
        this.kssc = kssc;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getKsxz() {
        return ksxz;
    }

    public void setKsxz(String ksxz) {
        this.ksxz = ksxz;
    }

    public String getPcmc() {
        return pcmc;
    }

    public void setPcmc(String pcmc) {
        this.pcmc = pcmc;
    }

    public String getZkzh() {
        return zkzh;
    }

    public void setZkzh(String zkzh) {
        this.zkzh = zkzh;
    }

    public String getZwh() {
        return zwh;
    }

    public void setZwh(String zwh) {
        this.zwh = zwh;
    }

    public String getZyjc() {
        return zyjc;
    }

    public void setZyjc(String zyjc) {
        this.zyjc = zyjc;
    }
}
