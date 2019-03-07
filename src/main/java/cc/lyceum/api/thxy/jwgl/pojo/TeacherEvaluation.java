package cc.lyceum.api.thxy.jwgl.pojo;

/**
 * 教师评价实体类
 *
 * @author LyceumHewun
 * @date 2019-01-03
 */
public class TeacherEvaluation {

    private String isdczbzl;
    private String isyjjy;
    private String isyxf;
    /**
     * 教学环节代码
     */
    private String jxhjdm;
    /**
     * 教学环节
     */
    private String jxhjmc;
    /**
     * 课程名称
     */
    private String kcmc;
    private String kcptdm;
    private String pdm;
    /**
     * 教师编号
     */
    private String pjdxbh;
    private String pjdxdm;
    private String pjdxlxdm;
    /**
     * 教师姓名
     */
    private String pjdxmc;
    /**
     * 平均分
     */
    private String pjf;
    /**
     * 评教类型代码
     */
    private String pjlxdm;
    /**
     * 评价人次
     */
    private String pjrc;
    /**
     * 官网上的排列顺序
     */
    private String rownum_;
    /**
     * 问卷安排类型
     * (value == '1') ? '结课周次评' : '上课周次评'
     */
    private String wjaplx;
    private String wjdm;
    private String wjlx;
    private String xnxqdm;
    private String yjjymc;
    private String yxfbl;
    /**
     * 状态
     * (value == '1') ? 'ok' : 'no'
     */
    private String zt;

    @Override
    public String toString() {
        return "TeacherEvaluation{" +
                "isdczbzl='" + isdczbzl + '\'' +
                ", isyjjy='" + isyjjy + '\'' +
                ", isyxf='" + isyxf + '\'' +
                ", jxhjdm='" + jxhjdm + '\'' +
                ", jxhjmc='" + jxhjmc + '\'' +
                ", kcmc='" + kcmc + '\'' +
                ", kcptdm='" + kcptdm + '\'' +
                ", pdm='" + pdm + '\'' +
                ", pjdxbh='" + pjdxbh + '\'' +
                ", pjdxdm='" + pjdxdm + '\'' +
                ", pjdxlxdm='" + pjdxlxdm + '\'' +
                ", pjdxmc='" + pjdxmc + '\'' +
                ", pjf='" + pjf + '\'' +
                ", pjlxdm='" + pjlxdm + '\'' +
                ", pjrc='" + pjrc + '\'' +
                ", rownum_='" + rownum_ + '\'' +
                ", wjaplx='" + wjaplx + '\'' +
                ", wjdm='" + wjdm + '\'' +
                ", wjlx='" + wjlx + '\'' +
                ", xnxqdm='" + xnxqdm + '\'' +
                ", yjjymc='" + yjjymc + '\'' +
                ", yxfbl='" + yxfbl + '\'' +
                ", zt='" + zt + '\'' +
                '}';
    }

    public TeacherEvaluation() {
    }

    public TeacherEvaluation(String isdczbzl, String isyjjy, String isyxf, String jxhjdm, String jxhjmc, String kcmc, String kcptdm, String pdm, String pjdxbh, String pjdxdm, String pjdxlxdm, String pjdxmc, String pjf, String pjlxdm, String pjrc, String rownum_, String wjaplx, String wjdm, String wjlx, String xnxqdm, String yjjymc, String yxfbl, String zt) {
        this.isdczbzl = isdczbzl;
        this.isyjjy = isyjjy;
        this.isyxf = isyxf;
        this.jxhjdm = jxhjdm;
        this.jxhjmc = jxhjmc;
        this.kcmc = kcmc;
        this.kcptdm = kcptdm;
        this.pdm = pdm;
        this.pjdxbh = pjdxbh;
        this.pjdxdm = pjdxdm;
        this.pjdxlxdm = pjdxlxdm;
        this.pjdxmc = pjdxmc;
        this.pjf = pjf;
        this.pjlxdm = pjlxdm;
        this.pjrc = pjrc;
        this.rownum_ = rownum_;
        this.wjaplx = wjaplx;
        this.wjdm = wjdm;
        this.wjlx = wjlx;
        this.xnxqdm = xnxqdm;
        this.yjjymc = yjjymc;
        this.yxfbl = yxfbl;
        this.zt = zt;
    }

    public String getIsdczbzl() {
        return isdczbzl;
    }

    public void setIsdczbzl(String isdczbzl) {
        this.isdczbzl = isdczbzl;
    }

    public String getIsyjjy() {
        return isyjjy;
    }

    public void setIsyjjy(String isyjjy) {
        this.isyjjy = isyjjy;
    }

    public String getIsyxf() {
        return isyxf;
    }

    public void setIsyxf(String isyxf) {
        this.isyxf = isyxf;
    }

    public String getJxhjdm() {
        return jxhjdm;
    }

    public void setJxhjdm(String jxhjdm) {
        this.jxhjdm = jxhjdm;
    }

    public String getJxhjmc() {
        return jxhjmc;
    }

    public void setJxhjmc(String jxhjmc) {
        this.jxhjmc = jxhjmc;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getKcptdm() {
        return kcptdm;
    }

    public void setKcptdm(String kcptdm) {
        this.kcptdm = kcptdm;
    }

    public String getPdm() {
        return pdm;
    }

    public void setPdm(String pdm) {
        this.pdm = pdm;
    }

    public String getPjdxbh() {
        return pjdxbh;
    }

    public void setPjdxbh(String pjdxbh) {
        this.pjdxbh = pjdxbh;
    }

    public String getPjdxdm() {
        return pjdxdm;
    }

    public void setPjdxdm(String pjdxdm) {
        this.pjdxdm = pjdxdm;
    }

    public String getPjdxlxdm() {
        return pjdxlxdm;
    }

    public void setPjdxlxdm(String pjdxlxdm) {
        this.pjdxlxdm = pjdxlxdm;
    }

    public String getPjdxmc() {
        return pjdxmc;
    }

    public void setPjdxmc(String pjdxmc) {
        this.pjdxmc = pjdxmc;
    }

    public String getPjf() {
        return pjf;
    }

    public void setPjf(String pjf) {
        this.pjf = pjf;
    }

    public String getPjlxdm() {
        return pjlxdm;
    }

    public void setPjlxdm(String pjlxdm) {
        this.pjlxdm = pjlxdm;
    }

    public String getPjrc() {
        return pjrc;
    }

    public void setPjrc(String pjrc) {
        this.pjrc = pjrc;
    }

    public String getRownum_() {
        return rownum_;
    }

    public void setRownum_(String rownum_) {
        this.rownum_ = rownum_;
    }

    public String getWjaplx() {
        return wjaplx;
    }

    public void setWjaplx(String wjaplx) {
        this.wjaplx = wjaplx;
    }

    public String getWjdm() {
        return wjdm;
    }

    public void setWjdm(String wjdm) {
        this.wjdm = wjdm;
    }

    public String getWjlx() {
        return wjlx;
    }

    public void setWjlx(String wjlx) {
        this.wjlx = wjlx;
    }

    public String getXnxqdm() {
        return xnxqdm;
    }

    public void setXnxqdm(String xnxqdm) {
        this.xnxqdm = xnxqdm;
    }

    public String getYjjymc() {
        return yjjymc;
    }

    public void setYjjymc(String yjjymc) {
        this.yjjymc = yjjymc;
    }

    public String getYxfbl() {
        return yxfbl;
    }

    public void setYxfbl(String yxfbl) {
        this.yxfbl = yxfbl;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public class EvaluationTopic {
        private String wjwtdm;
        private String wtdm;
        private String wtmc;
        /**
         * 分值
         */
        private String fz;

        @Override
        public String toString() {
            return "EvaluationOption{" +
                    "wjwtdm='" + wjwtdm + '\'' +
                    ", wtdm='" + wtdm + '\'' +
                    ", wtmc='" + wtmc + '\'' +
                    ", fz='" + fz + '\'' +
                    '}';
        }

        public EvaluationTopic() {
        }

        public EvaluationTopic(String wjwtdm, String wtdm, String wtmc, String fz) {
            this.wjwtdm = wjwtdm;
            this.wtdm = wtdm;
            this.wtmc = wtmc;
            this.fz = fz;
        }

        public String getWjwtdm() {
            return wjwtdm;
        }

        public void setWjwtdm(String wjwtdm) {
            this.wjwtdm = wjwtdm;
        }

        public String getWtdm() {
            return wtdm;
        }

        public void setWtdm(String wtdm) {
            this.wtdm = wtdm;
        }

        public String getWtmc() {
            return wtmc;
        }

        public void setWtmc(String wtmc) {
            this.wtmc = wtmc;
        }

        public String getFz() {
            return fz;
        }

        public void setFz(String fz) {
            this.fz = fz;
        }
    }

    public class EvaluationOption {
        private String xmdm;
        private String wtdm;
        private String xmbh;
        private String xmmc;
        private String fzbl;

        @Override
        public String toString() {
            return "EvaluationOption{" +
                    "xmdm='" + xmdm + '\'' +
                    ", wtdm='" + wtdm + '\'' +
                    ", xmbh='" + xmbh + '\'' +
                    ", xmmc='" + xmmc + '\'' +
                    ", fzbl='" + fzbl + '\'' +
                    '}';
        }

        public EvaluationOption() {
        }

        public EvaluationOption(String xmdm, String wtdm, String xmbh, String xmmc, String fzbl) {
            this.xmdm = xmdm;
            this.wtdm = wtdm;
            this.xmbh = xmbh;
            this.xmmc = xmmc;
            this.fzbl = fzbl;
        }

        public String getXmdm() {
            return xmdm;
        }

        public void setXmdm(String xmdm) {
            this.xmdm = xmdm;
        }

        public String getWtdm() {
            return wtdm;
        }

        public void setWtdm(String wtdm) {
            this.wtdm = wtdm;
        }

        public String getXmbh() {
            return xmbh;
        }

        public void setXmbh(String xmbh) {
            this.xmbh = xmbh;
        }

        public String getXmmc() {
            return xmmc;
        }

        public void setXmmc(String xmmc) {
            this.xmmc = xmmc;
        }

        public String getFzbl() {
            return fzbl;
        }

        public void setFzbl(String fzbl) {
            this.fzbl = fzbl;
        }
    }
}
