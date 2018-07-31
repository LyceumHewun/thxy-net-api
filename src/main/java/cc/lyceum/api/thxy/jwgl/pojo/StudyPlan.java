package cc.lyceum.api.thxy.jwgl.pojo;

/**
 * 学习计划实体类
 *
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-07-30
 */
public class StudyPlan {

    /**
     * 总学分
     */
    private String zxf;
    /**
     * 必修学分
     */
    private String bxxf;
    /**
     * 限选学分
     */
    private String xxxf;
    /**
     * 公选课学分
     */
    private String gxkxf;
    /**
     * 创新学分
     */
    private String cxxf;
    /**
     * 绩点
     */
    private String jd;

    public StudyPlan(String zxf, String bxxf, String xxxf, String gxkxf, String cxxf, String jd) {
        this.zxf = zxf;
        this.bxxf = bxxf;
        this.xxxf = xxxf;
        this.gxkxf = gxkxf;
        this.cxxf = cxxf;
        this.jd = jd;
    }

    public StudyPlan() {
    }

    @Override
    public String toString() {
        return "StudyPlan{" +
                "总学分='" + zxf + '\'' +
                ", 必修学分='" + bxxf + '\'' +
                ", 限选学分='" + xxxf + '\'' +
                ", 公选课学分='" + gxkxf + '\'' +
                ", 创新学分='" + cxxf + '\'' +
                ", 绩点='" + jd + '\'' +
                '}';
    }

    public String getZxf() {
        return zxf;
    }

    public void setZxf(String zxf) {
        this.zxf = zxf;
    }

    public String getBxxf() {
        return bxxf;
    }

    public void setBxxf(String bxxf) {
        this.bxxf = bxxf;
    }

    public String getXxxf() {
        return xxxf;
    }

    public void setXxxf(String xxxf) {
        this.xxxf = xxxf;
    }

    public String getGxkxf() {
        return gxkxf;
    }

    public void setGxkxf(String gxkxf) {
        this.gxkxf = gxkxf;
    }

    public String getCxxf() {
        return cxxf;
    }

    public void setCxxf(String cxxf) {
        this.cxxf = cxxf;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }
}
