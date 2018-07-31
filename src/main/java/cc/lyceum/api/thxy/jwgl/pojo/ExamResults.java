package cc.lyceum.api.thxy.jwgl.pojo;

/**
 * 考试成绩实体类
 *
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-07-30
 */
public class ExamResults {
    private String bkbmkzdm;
    private String bz;
    private String c12cjdm;
    private String cjbzdm;
    /**
     * 成绩代码
     */
    private String cjdm;
    /**
     * 成绩方式
     */
    private String cjfsmc;
    /**
     * 成绩绩点
     */
    private String cjjd;
    /**
     * 有效
     */
    private String isactive;
    private String kcbh;
    /**
     * 课程大类
     */
    private String kcdlmc;
    /**
     * 课程代码
     */
    private String kcdm;
    /**
     * 课程分类
     */
    private String kcflmc;
    /**
     * 课程名称
     */
    private String kcmc;
    private String kcptbh;
    /**
     * 考试性质代码
     */
    private String ksxzdm;
    /**
     * 考试性质
     */
    private String ksxzmc;
    private String maxksxzdm;
    private String maxzcj;
    private String rownum_;
    private String rwdm;
    private String wpj;
    private String wpjbz;
    private String wzc;
    private String wzcbz;
    /**
     * 修读方式
     */
    private String xdfsmc;
    /**
     * 学分
     */
    private String xf;
    private String xnxqdm;
    /**
     * 学年学期
     */
    private String xnxqmc;
    private String xsbh;
    /**
     * 学生代码
     */
    private String xsdm;
    /**
     * 学生姓名
     */
    private String xsxm;
    /**
     * 总成绩
     */
    private String zcj;
    /**
     * 总学时
     */
    private String zxs;

    public ExamResults(String bkbmkzdm, String bz, String c12cjdm, String cjbzdm, String cjdm, String cjfsmc, String cjjd, String isactive, String kcbh, String kcdlmc, String kcdm, String kcflmc, String kcmc, String kcptbh, String ksxzdm, String ksxzmc, String maxksxzdm, String maxzcj, String rownum_, String rwdm, String wpj, String wpjbz, String wzc, String wzcbz, String xdfsmc, String xf, String xnxqdm, String xnxqmc, String xsbh, String xsdm, String xsxm, String zcj, String zxs) {
        this.bkbmkzdm = bkbmkzdm;
        this.bz = bz;
        this.c12cjdm = c12cjdm;
        this.cjbzdm = cjbzdm;
        this.cjdm = cjdm;
        this.cjfsmc = cjfsmc;
        this.cjjd = cjjd;
        this.isactive = isactive;
        this.kcbh = kcbh;
        this.kcdlmc = kcdlmc;
        this.kcdm = kcdm;
        this.kcflmc = kcflmc;
        this.kcmc = kcmc;
        this.kcptbh = kcptbh;
        this.ksxzdm = ksxzdm;
        this.ksxzmc = ksxzmc;
        this.maxksxzdm = maxksxzdm;
        this.maxzcj = maxzcj;
        this.rownum_ = rownum_;
        this.rwdm = rwdm;
        this.wpj = wpj;
        this.wpjbz = wpjbz;
        this.wzc = wzc;
        this.wzcbz = wzcbz;
        this.xdfsmc = xdfsmc;
        this.xf = xf;
        this.xnxqdm = xnxqdm;
        this.xnxqmc = xnxqmc;
        this.xsbh = xsbh;
        this.xsdm = xsdm;
        this.xsxm = xsxm;
        this.zcj = zcj;
        this.zxs = zxs;
    }

    public ExamResults() {
    }

    // 这么长我就不一个个翻译了，自己看注释
    @Override
    public String toString() {
        return "ExamResults{" +
                "bkbmkzdm='" + bkbmkzdm + '\'' +
                ", bz='" + bz + '\'' +
                ", c12cjdm='" + c12cjdm + '\'' +
                ", cjbzdm='" + cjbzdm + '\'' +
                ", cjdm='" + cjdm + '\'' +
                ", cjfsmc='" + cjfsmc + '\'' +
                ", cjjd='" + cjjd + '\'' +
                ", isactive='" + isactive + '\'' +
                ", kcbh='" + kcbh + '\'' +
                ", kcdlmc='" + kcdlmc + '\'' +
                ", kcdm='" + kcdm + '\'' +
                ", kcflmc='" + kcflmc + '\'' +
                ", kcmc='" + kcmc + '\'' +
                ", kcptbh='" + kcptbh + '\'' +
                ", ksxzdm='" + ksxzdm + '\'' +
                ", ksxzmc='" + ksxzmc + '\'' +
                ", maxksxzdm='" + maxksxzdm + '\'' +
                ", maxzcj='" + maxzcj + '\'' +
                ", rownum_='" + rownum_ + '\'' +
                ", rwdm='" + rwdm + '\'' +
                ", wpj='" + wpj + '\'' +
                ", wpjbz='" + wpjbz + '\'' +
                ", wzc='" + wzc + '\'' +
                ", wzcbz='" + wzcbz + '\'' +
                ", xdfsmc='" + xdfsmc + '\'' +
                ", xf='" + xf + '\'' +
                ", xnxqdm='" + xnxqdm + '\'' +
                ", xnxqmc='" + xnxqmc + '\'' +
                ", xsbh='" + xsbh + '\'' +
                ", xsdm='" + xsdm + '\'' +
                ", xsxm='" + xsxm + '\'' +
                ", zcj='" + zcj + '\'' +
                ", zxs='" + zxs + '\'' +
                '}';
    }

    public String getBkbmkzdm() {
        return bkbmkzdm;
    }

    public void setBkbmkzdm(String bkbmkzdm) {
        this.bkbmkzdm = bkbmkzdm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getC12cjdm() {
        return c12cjdm;
    }

    public void setC12cjdm(String c12cjdm) {
        this.c12cjdm = c12cjdm;
    }

    public String getCjbzdm() {
        return cjbzdm;
    }

    public void setCjbzdm(String cjbzdm) {
        this.cjbzdm = cjbzdm;
    }

    public String getCjdm() {
        return cjdm;
    }

    public void setCjdm(String cjdm) {
        this.cjdm = cjdm;
    }

    public String getCjfsmc() {
        return cjfsmc;
    }

    public void setCjfsmc(String cjfsmc) {
        this.cjfsmc = cjfsmc;
    }

    public String getCjjd() {
        return cjjd;
    }

    public void setCjjd(String cjjd) {
        this.cjjd = cjjd;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
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

    public String getKcdm() {
        return kcdm;
    }

    public void setKcdm(String kcdm) {
        this.kcdm = kcdm;
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

    public String getKcptbh() {
        return kcptbh;
    }

    public void setKcptbh(String kcptbh) {
        this.kcptbh = kcptbh;
    }

    public String getKsxzdm() {
        return ksxzdm;
    }

    public void setKsxzdm(String ksxzdm) {
        this.ksxzdm = ksxzdm;
    }

    public String getKsxzmc() {
        return ksxzmc;
    }

    public void setKsxzmc(String ksxzmc) {
        this.ksxzmc = ksxzmc;
    }

    public String getMaxksxzdm() {
        return maxksxzdm;
    }

    public void setMaxksxzdm(String maxksxzdm) {
        this.maxksxzdm = maxksxzdm;
    }

    public String getMaxzcj() {
        return maxzcj;
    }

    public void setMaxzcj(String maxzcj) {
        this.maxzcj = maxzcj;
    }

    public String getRownum_() {
        return rownum_;
    }

    public void setRownum_(String rownum_) {
        this.rownum_ = rownum_;
    }

    public String getRwdm() {
        return rwdm;
    }

    public void setRwdm(String rwdm) {
        this.rwdm = rwdm;
    }

    public String getWpj() {
        return wpj;
    }

    public void setWpj(String wpj) {
        this.wpj = wpj;
    }

    public String getWpjbz() {
        return wpjbz;
    }

    public void setWpjbz(String wpjbz) {
        this.wpjbz = wpjbz;
    }

    public String getWzc() {
        return wzc;
    }

    public void setWzc(String wzc) {
        this.wzc = wzc;
    }

    public String getWzcbz() {
        return wzcbz;
    }

    public void setWzcbz(String wzcbz) {
        this.wzcbz = wzcbz;
    }

    public String getXdfsmc() {
        return xdfsmc;
    }

    public void setXdfsmc(String xdfsmc) {
        this.xdfsmc = xdfsmc;
    }

    public String getXf() {
        return xf;
    }

    public void setXf(String xf) {
        this.xf = xf;
    }

    public String getXnxqdm() {
        return xnxqdm;
    }

    public void setXnxqdm(String xnxqdm) {
        this.xnxqdm = xnxqdm;
    }

    public String getXnxqmc() {
        return xnxqmc;
    }

    public void setXnxqmc(String xnxqmc) {
        this.xnxqmc = xnxqmc;
    }

    public String getXsbh() {
        return xsbh;
    }

    public void setXsbh(String xsbh) {
        this.xsbh = xsbh;
    }

    public String getXsdm() {
        return xsdm;
    }

    public void setXsdm(String xsdm) {
        this.xsdm = xsdm;
    }

    public String getXsxm() {
        return xsxm;
    }

    public void setXsxm(String xsxm) {
        this.xsxm = xsxm;
    }

    public String getZcj() {
        return zcj;
    }

    public void setZcj(String zcj) {
        this.zcj = zcj;
    }

    public String getZxs() {
        return zxs;
    }

    public void setZxs(String zxs) {
        this.zxs = zxs;
    }
}
