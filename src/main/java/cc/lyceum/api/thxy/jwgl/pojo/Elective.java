package cc.lyceum.api.thxy.jwgl.pojo;

/**
 * 选修课程实体类
 *
 * @author LyceumHewun
 * @date 2018-9-25
 */
public class Elective {

    /**
     * 课程任务代码
     */
    private String kcrwdm;
    /**
     * 最大可选人数
     */
    private String pkrs;
    /**
     * 教学班代码
     */
    private String jxbdm;
    private String kcptdm;
    private String xmmc;
    /**
     * 课程代码
     */
    private String kcdm;
    /**
     * 课程名称
     */
    private String kcmc;
    private String rwdm;
    private String xbyqdm;
    private String rs1;
    private String rs2;
    private String wyfjdm;
    private String wyyzdm;
    private String kkxqdm;
    /**
     * 总学时
     */
    private String zxs;
    /**
     * 学分
     */
    private String xf;
    private String kcflmc;
    /**
     * 教师名称
     */
    private String teaxm;
    /**
     * 已选人数
     */
    private String jxbrs;

    public Elective() {
    }

    public Elective(String kcrwdm, String pkrs, String jxbdm, String kcptdm, String xmmc, String kcdm, String kcmc, String rwdm, String xbyqdm, String rs1, String rs2, String wyfjdm, String wyyzdm, String kkxqdm, String zxs, String xf, String kcflmc, String teaxm, String jxbrs) {
        this.kcrwdm = kcrwdm;
        this.pkrs = pkrs;
        this.jxbdm = jxbdm;
        this.kcptdm = kcptdm;
        this.xmmc = xmmc;
        this.kcdm = kcdm;
        this.kcmc = kcmc;
        this.rwdm = rwdm;
        this.xbyqdm = xbyqdm;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.wyfjdm = wyfjdm;
        this.wyyzdm = wyyzdm;
        this.kkxqdm = kkxqdm;
        this.zxs = zxs;
        this.xf = xf;
        this.kcflmc = kcflmc;
        this.teaxm = teaxm;
        this.jxbrs = jxbrs;
    }

    @Override
    public String toString() {
        return "Elective{" +
                "kcrwdm='" + kcrwdm + '\'' +
                ", pkrs='" + pkrs + '\'' +
                ", jxbdm='" + jxbdm + '\'' +
                ", kcptdm='" + kcptdm + '\'' +
                ", xmmc='" + xmmc + '\'' +
                ", kcdm='" + kcdm + '\'' +
                ", kcmc='" + kcmc + '\'' +
                ", rwdm='" + rwdm + '\'' +
                ", xbyqdm='" + xbyqdm + '\'' +
                ", rs1='" + rs1 + '\'' +
                ", rs2='" + rs2 + '\'' +
                ", wyfjdm='" + wyfjdm + '\'' +
                ", wyyzdm='" + wyyzdm + '\'' +
                ", kkxqdm='" + kkxqdm + '\'' +
                ", zxs='" + zxs + '\'' +
                ", xf='" + xf + '\'' +
                ", kcflmc='" + kcflmc + '\'' +
                ", teaxm='" + teaxm + '\'' +
                ", jxbrs='" + jxbrs + '\'' +
                '}';
    }

    public String getKcrwdm() {
        return kcrwdm;
    }

    public void setKcrwdm(String kcrwdm) {
        this.kcrwdm = kcrwdm;
    }

    public String getPkrs() {
        return pkrs;
    }

    public void setPkrs(String pkrs) {
        this.pkrs = pkrs;
    }

    public String getJxbdm() {
        return jxbdm;
    }

    public void setJxbdm(String jxbdm) {
        this.jxbdm = jxbdm;
    }

    public String getKcptdm() {
        return kcptdm;
    }

    public void setKcptdm(String kcptdm) {
        this.kcptdm = kcptdm;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getKcdm() {
        return kcdm;
    }

    public void setKcdm(String kcdm) {
        this.kcdm = kcdm;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getRwdm() {
        return rwdm;
    }

    public void setRwdm(String rwdm) {
        this.rwdm = rwdm;
    }

    public String getXbyqdm() {
        return xbyqdm;
    }

    public void setXbyqdm(String xbyqdm) {
        this.xbyqdm = xbyqdm;
    }

    public String getRs1() {
        return rs1;
    }

    public void setRs1(String rs1) {
        this.rs1 = rs1;
    }

    public String getRs2() {
        return rs2;
    }

    public void setRs2(String rs2) {
        this.rs2 = rs2;
    }

    public String getWyfjdm() {
        return wyfjdm;
    }

    public void setWyfjdm(String wyfjdm) {
        this.wyfjdm = wyfjdm;
    }

    public String getWyyzdm() {
        return wyyzdm;
    }

    public void setWyyzdm(String wyyzdm) {
        this.wyyzdm = wyyzdm;
    }

    public String getKkxqdm() {
        return kkxqdm;
    }

    public void setKkxqdm(String kkxqdm) {
        this.kkxqdm = kkxqdm;
    }

    public String getZxs() {
        return zxs;
    }

    public void setZxs(String zxs) {
        this.zxs = zxs;
    }

    public String getXf() {
        return xf;
    }

    public void setXf(String xf) {
        this.xf = xf;
    }

    public String getKcflmc() {
        return kcflmc;
    }

    public void setKcflmc(String kcflmc) {
        this.kcflmc = kcflmc;
    }

    public String getTeaxm() {
        return teaxm;
    }

    public void setTeaxm(String teaxm) {
        this.teaxm = teaxm;
    }

    public String getJxbrs() {
        return jxbrs;
    }

    public void setJxbrs(String jxbrs) {
        this.jxbrs = jxbrs;
    }
}
