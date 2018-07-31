package cc.lyceum.api.thxy.jwgl.pojo;

/**
 * 考级成绩实体类
 *
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-07-30
 */
public class GradeExamination {

    /**
     * 班级名称
     */
    private String bjmc;
    /**
     * 标志
     */
    private String bz;
    /**
     * 成绩标志
     */
    private String cjbzmc;
    /**
     * 考级成绩代码
     */
    private String kjcjdm;
    /**
     * 考级课程编号
     */
    private String kjkcbh;
    /**
     * 考级课程名称
     */
    private String kjkcmc;
    /**
     * 考试时间
     */
    private String kssj;
    /**
     * TODO 学历
     */
    private String pyccmc;
    private String rownum_;
    /**
     * 身份证号
     */
    private String sfzh;
    /**
     * 听力成绩
     */
    private String xm1cj;
    /**
     * 阅读成绩
     */
    private String xm2cj;
    /**
     * 写作成绩
     */
    private String xm3cj;
    /**
     * 综合成绩
     */
    private String xm4cj;
    /**
     * 考试时间
     */
    private String xnxqmc;
    /**
     * 学生院校名称(学院)
     */
    private String xsyxmc;
    /**
     * 学制
     */
    private String xz;
    /**
     * 准考证号
     */
    private String zkzh;
    /**
     * 专业名称
     */
    private String zymc;

    @Override
    public String toString() {
        return "GradeExamination{" +
                "bjmc='" + bjmc + '\'' +
                ", bz='" + bz + '\'' +
                ", cjbzmc='" + cjbzmc + '\'' +
                ", kjcjdm='" + kjcjdm + '\'' +
                ", kjkcbh='" + kjkcbh + '\'' +
                ", kjkcmc='" + kjkcmc + '\'' +
                ", kssj='" + kssj + '\'' +
                ", pyccmc='" + pyccmc + '\'' +
                ", rownum_='" + rownum_ + '\'' +
                ", sfzh='" + sfzh + '\'' +
                ", xm1cj='" + xm1cj + '\'' +
                ", xm2cj='" + xm2cj + '\'' +
                ", xm3cj='" + xm3cj + '\'' +
                ", xm4cj='" + xm4cj + '\'' +
                ", xnxqmc='" + xnxqmc + '\'' +
                ", xsyxmc='" + xsyxmc + '\'' +
                ", xz='" + xz + '\'' +
                ", zkzh='" + zkzh + '\'' +
                ", zymc='" + zymc + '\'' +
                '}';
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCjbzmc() {
        return cjbzmc;
    }

    public void setCjbzmc(String cjbzmc) {
        this.cjbzmc = cjbzmc;
    }

    public String getKjcjdm() {
        return kjcjdm;
    }

    public void setKjcjdm(String kjcjdm) {
        this.kjcjdm = kjcjdm;
    }

    public String getKjkcbh() {
        return kjkcbh;
    }

    public void setKjkcbh(String kjkcbh) {
        this.kjkcbh = kjkcbh;
    }

    public String getKjkcmc() {
        return kjkcmc;
    }

    public void setKjkcmc(String kjkcmc) {
        this.kjkcmc = kjkcmc;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getPyccmc() {
        return pyccmc;
    }

    public void setPyccmc(String pyccmc) {
        this.pyccmc = pyccmc;
    }

    public String getRownum_() {
        return rownum_;
    }

    public void setRownum_(String rownum_) {
        this.rownum_ = rownum_;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getXm1cj() {
        return xm1cj;
    }

    public void setXm1cj(String xm1cj) {
        this.xm1cj = xm1cj;
    }

    public String getXm2cj() {
        return xm2cj;
    }

    public void setXm2cj(String xm2cj) {
        this.xm2cj = xm2cj;
    }

    public String getXm3cj() {
        return xm3cj;
    }

    public void setXm3cj(String xm3cj) {
        this.xm3cj = xm3cj;
    }

    public String getXm4cj() {
        return xm4cj;
    }

    public void setXm4cj(String xm4cj) {
        this.xm4cj = xm4cj;
    }

    public String getXnxqmc() {
        return xnxqmc;
    }

    public void setXnxqmc(String xnxqmc) {
        this.xnxqmc = xnxqmc;
    }

    public String getXsyxmc() {
        return xsyxmc;
    }

    public void setXsyxmc(String xsyxmc) {
        this.xsyxmc = xsyxmc;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public String getZkzh() {
        return zkzh;
    }

    public void setZkzh(String zkzh) {
        this.zkzh = zkzh;
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }
}
