package cc.lyceum.api.thxy.jwgl;

import cc.lyceum.api.thxy.Client;
import cc.lyceum.api.thxy.ClientFactory;
import cc.lyceum.api.thxy.jwgl.pojo.*;
import com.google.gson.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static cc.lyceum.api.thxy.ConfigGlobal.JWGL_HOST;
import static cc.lyceum.api.thxy.jwgl.tools.Escape.escape;

/**
 * 教务系统
 *
 * @author LyceumHewun
 * @date 2018-9-6
 */
public class ThxyJwgl {

    private Client client = ClientFactory.creatClient();

    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();

    private String host = JWGL_HOST;

    public ThxyJwgl() {
    }

    public ThxyJwgl(String host) {
        this.host = host;
    }

    /**
     * 登陆
     *
     * @param userNumber 学号
     * @param password   教务系统密码
     * @return 登陆状态
     */
    public String login(String userNumber, String password) {
        // 验证码识别
        String code = Dictionary.contrast(new ImageHelper(getCodeImge())
                .filling()
                .spilt());
        Map<String, String> forms = new HashMap<>();
        forms.put("account", userNumber);
        forms.put("pwd", password);
        forms.put("verifycode", code);
        String json = client.postBody(host + "login!doLogin.action", forms);
        System.out.println(json);
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        boolean status = jsonObject.get("status").getAsString().equals("y");
        String message = jsonObject.get("msg").getAsString();
        if (status) {
            // 登录成功后访问一次login!welcome.action
            // 2019-01-10 更新的低级反爬策略
            client.getBody(host + "login!welcome.action");
            return "success";
        } else if (message.contains("验证码不正确")) {
            return login(userNumber, password);
        } else if (message.contains("连接已过期")) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            return login(userNumber, password);
        } else {
            return message;
        }
    }

    /**
     * 获取验证码
     *
     * @return BufferedImage
     */
    private BufferedImage getCodeImge() {
        client.getBody(host);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(client.get(host + "yzm", null).body().bytes());
            return ImageIO.read(in);
        } catch (IOException ignored) {
            // 输出错误
            String html = client.getBody(host + "yzm");
            System.err.println("/////////////////////////////");
            System.err.println("/////////thxy-net-api////////");
            System.err.println("/////////////////////////////");
            System.err.println(html);
        }
        return null;
    }

    /**
     * 退出登陆
     * <p>
     * 建议每次调用完该类后使用
     *
     * @return boolean
     */
    public boolean logout() {
        return client.getBody(host + "login!logout.action").equals("1");
    }

    /**
     * 我的桌面 -> 学习情况
     * <p>
     * 主页学习情况，可以看到学分、绩点，但是没有学习计划准确</br>
     * 建议使用 {@link ThxyJwgl#getStudyPlan()}
     *
     * @return tjmc1 对应 tjz1, tjmc2、tjmc3亦如此
     */
    @Deprecated
    public Map<String, String> getStudyStatus() {
        client.getBody(host + "xxzyxx!reGet.action");
        String json = client.postBody(host + "xxzyxx!xxzyList.action", null);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        if (jsonArray.size() != 0) {
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
            String tjmc1 = jsonObject.get("tjmc1").getAsString();
            String tjmc2 = jsonObject.get("tjmc2").getAsString();
            String tjmc3 = jsonObject.get("tjmc3").getAsString();
            String tjz1 = jsonObject.get("tjz1").getAsString();
            String tjz2 = jsonObject.get("tjz2").getAsString();
            String tjz3 = jsonObject.get("tjz3").getAsString();
            Map<String, String> map = new HashMap<>();
            map.put("tjmc1", tjmc1);
            map.put("tjmc2", tjmc2);
            map.put("tjmc3", tjmc3);
            map.put("tjz1", tjz1);
            map.put("tjz2", tjz2);
            map.put("tjz3", tjz3);
            return map;
        } else {
            return null;
        }
    }

    /**
     * 信息查询 -> 课程成绩
     * <p>
     * 查成绩
     * </br>
     * value=""        全部</br>
     * value="202201"  2022-2023-1</br>
     * value="202102"  2021-2022-2</br>
     * value="202101"  2021-2022-1</br>
     * value="202002"  2020-2021-2</br>
     * value="202001"  2020-2021-1</br>
     * value="201902"  2019-2020-2</br>
     * value="201901"  2019-2020-1</br>
     * value="201802"  2018-2019-2</br>
     * value="201801"  2018-2019-1</br>
     * value="201702"  2017-2018-2</br>
     * value="201701"  2017-2018-1</br>
     * value="20162"   2016-2017-2</br>
     * value="20161"   2016-2017-1</br>
     * value="20152"   2015-2016-2</br>
     * value="20151"   2015-2016-1</br>
     * value="20142"   2014-2015-2</br>
     * value="20141"   2014-2015-1</br>
     * value="20132"   2013-2014-2</br>
     * value="20131"   2013-2014-1</br>
     * value="20122"   2012-2013-2</br>
     * value="20121"   2012-2013-1</br>
     * value="20112"   2011-2012-2</br>
     * value="20111"   2011-2012-1</br>
     * value="20102"   2010-2011-2</br>
     * value="20101"   2010-2011-1</br>
     *
     * @param value 学年学期代码
     * @return 考试成绩实体类
     */
    public List<ExamResults> getExamResults(String value) {
        Map<String, String> forms = new HashMap<>();
        forms.put("xnxqdm", value);
        forms.put("page", "1");
        forms.put("rows", "999");
        forms.put("sort", "xnxqdm,kcdm");
        forms.put("order", "asc");
        String json = client.postBody(host + "xskccjxx!getDataList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().getAsJsonArray("rows");
        return parseJsonArray(jsonArray, ExamResults.class);
    }

    /**
     * 信息查询 -> 学习计划 -> 预审
     * <p>
     * 查详细学分、平均绩点
     *
     * @return 学习计划实体类
     */
    public StudyPlan getStudyPlan() {
        // first
        Map<String, String> forms = new HashMap<>();
        forms.put("page", "1");
        forms.put("rows", "10");
        forms.put("sort", "jxjhbh");
        forms.put("order", "asc");
        String json = client.postBody(host + "xsjxjhxx!getDataList1.action", forms);
        JsonObject jsonObject = (JsonObject) jsonParser.parse(json).getAsJsonObject().get("rows").getAsJsonArray().get(0);
        String jxjhdm = jsonObject.get("jxjhdm").getAsString();
        String jhlxdm = jsonObject.get("jhlxdm").getAsString();
        // second
        String html = client.getBody(host + "xsjxjhxx!xsxxjhMain.action?jxjhdm=" + jxjhdm + "&jhlxdm=" + jhlxdm);
        Document document = Jsoup.parse(html);
        Elements elements = document.select("table[class=ctdheader2]").get(0).select("td[align=left]");
        String jd = elements.get(2).text();
        String xf = elements.get(5).text();
        xf = xf.replaceAll("   ", "");
        String zxf = xf.substring(0, xf.indexOf("必修学分："));
        String bxxf = xf.substring(xf.indexOf("必修学分：") + 5, xf.indexOf("限选学分："));
        String xxxf = xf.substring(xf.indexOf("限选学分：") + 5, xf.indexOf("公选课学分："));
        String gxxf = xf.substring(xf.indexOf("公选课学分：") + 6, xf.indexOf("创新学分："));
        String cxxf = xf.substring(xf.indexOf("创新学分：") + 5, xf.length());
        return new StudyPlan(zxf, bxxf, xxxf, gxxf, cxxf, jd);
    }

    /**
     * 我的桌面 -> 课表查询
     * <p>
     * 根据日期查当天课程, 查的是个人课表
     *
     * @param date yyyy-MM-dd  2018-07-30
     * @return 课程实体类集合
     */
    public List<Curriculum> getCurriculumByDate(String date) {
        String json = client.getBody(host + "desktop!xskb.action?rq=" + date);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        return parseJsonArray(jsonArray, Curriculum.class);
    }

    /**
     * 我的桌面 -> 课表查询
     * <p>
     * 获取今天的课程
     *
     * @return 课程实体类集合
     */
    public List<Curriculum> getCurriculumByDate() {
        return getCurriculumByDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    /**
     * 信息查询 -> 课表查询 -> 我的课表
     * <p>
     * 根据学期和周次查课程
     *
     * @param value 参考 {@link ThxyJwgl#getExamResults(String)} 方法的参数value
     * @param week  第几周, 空字符串查全部
     * @return 课程实体类集合
     */
    public List<Curriculum> getSelfCurriculum(String value, String week) {
        String json = client.getBody(host + "xsgrkbcx!getKbRq.action?xnxqdm=" + value + "&zc=" + week);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray().get(0).getAsJsonArray();
        return parseJsonArray(jsonArray, Curriculum.class);
    }

    /**
     * 信息查询 -> 班级课表
     * <p>
     * 根据学期查课程
     *
     * @param value 参考 {@link ThxyJwgl#getExamResults(String)} 方法的参数value
     * @param week  第几周, 空字符串查全部
     * @return 课程实体类集合
     */
    public List<Curriculum> getClassCurriculum(String value, String week) {
        // 获取班级代码
        String html0 = client.getBody(host + "xsbjkbcx!xsbjkbMain.action");
        String bjdm = Jsoup.parse(html0).select("select[id=bjdm]").get(0).children().select("option[selected]").attr("value");
        String json = client.getBody(host + "xsbjkbcx!getKbRq.action?xnxqdm=" + value + "&zc=" + week + "&bjdm=" + bjdm);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray().get(0).getAsJsonArray();
        return parseJsonArray(jsonArray, Curriculum.class);
    }

    /**
     * 信息查询 -> 班级课表 -> 列表展示
     * <p>
     * 列表展示, 查课程表
     *
     * @param value 参考 {@link ThxyJwgl#getExamResults(String)} 方法的参数value
     * @return 课程实体类集合
     */
    public List<Curriculum> getCurriculumList(String value) {
        // 获取班级代码
        String html = client.getBody(host + "xsbjkbcx!xsbjkbMain.action");
        String bjdm = Jsoup.parse(html).select("select[id=bjdm]").get(0).children().select("option[selected]").attr("value");
        // 查课表
        Map<String, String> forms = new HashMap<>();
        forms.put("zc", "");
        forms.put("xnxqdm", value);
        forms.put("bjdm", bjdm);
        forms.put("page", "1");
        forms.put("rows", "999");
        forms.put("sort", "kxh");
        forms.put("order", "asc");
        String json = client.postBody(host + "xsbjkbcx!getDataList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().get("rows").getAsJsonArray();
        return parseJsonArray(jsonArray, Curriculum.class);
    }

    /**
     * 信息查询 -> 考级成绩
     * <p>
     * 查考级成绩
     *
     * @return 考级成绩实体类集合
     */
    public List<GradeExamination> getGradeExamination() {
        Map<String, String> forms = new HashMap<>();
        forms.put("page", "1");
        forms.put("rows", "999");
        forms.put("sort", "xnxqdm");
        forms.put("order", "asc");
        String json = client.postBody(host + "xskjcjxx!getDataList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().getAsJsonArray("rows");
        return parseJsonArray(jsonArray, GradeExamination.class);
    }

    /**
     * 信息查询 -> 考试信息查询
     * <p>
     * 根据学期查考试安排
     *
     * @param value 参考 {@link ThxyJwgl#getExamResults(String)} 方法的参数value
     * @return 考试安排实体类集合
     */
    public List<ExamArrange> getExamArrange(String value) {
        Map<String, String> forms = new HashMap<>();
        forms.put("xnxqdm", value);
        forms.put("examname", "");
        forms.put("placename", "");
        forms.put("ksxz", "");
        String json = client.postBody(host + "ksxxgl!getMyExam.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        return parseJsonArray(jsonArray, ExamArrange.class);
    }

    /**
     * 信息查询 -> 上课任务 -> 查询
     * <p>
     * 根据学期查课程
     *
     * @param value 参考 {@link ThxyJwgl#getExamResults(String)} 方法的参数value
     * @return 上课任务实体类集合
     */
    public List<ClassTask> getClassTask(String value) {
        Map<String, String> forms = new HashMap<>();
        forms.put("xnxqdm", value);
        forms.put("kcdldm", "");
        forms.put("kcfldm", "");
        forms.put("kcmc", "");
        forms.put("page", "1");
        forms.put("rows", "999");
        forms.put("sort", "kcbh");
        forms.put("order", "asc");
        String json = client.postBody(host + "xskktzd!getDataList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().getAsJsonArray("rows");
        return parseJsonArray(jsonArray, ClassTask.class);
    }

    /**
     * 信息查询 -> 学籍卡片
     * <p>
     * 查个人信息
     *
     * @return 学生个人信息实体类
     */
    public StudentInfo getStudentInfo() {
        String html = client.getBody(host + "xjkpxx!xjkpList.action");
        StudentInfo studentInfo = new StudentInfo();
        Document document = Jsoup.parse(html);
        Element form = document.select("form[id=ff]").get(0);
        // 个人校内信息
        Element table1 = form.child(0);
        Elements label = table1.select("label");
        studentInfo.setXh(label.get(0).text());
        studentInfo.setXm(label.get(1).text());
        studentInfo.setRxnf(label.get(2).text());
        studentInfo.setYxmc(label.get(3).text());
        studentInfo.setZy(label.get(4).text());
        studentInfo.setBj(label.get(5).text());
        studentInfo.setSznj(label.get(6).text());
        studentInfo.setFxzy(label.get(7).text());
        studentInfo.setSzxq(label.get(8).text());
        studentInfo.setXszt(label.get(9).text());
        studentInfo.setXjzt(label.get(10).text());
        studentInfo.setWyyz(label.get(11).text());
        studentInfo.setXb(table1.select("select[id=xbdm]").select("option[selected]").text());
        Elements input = table1.select("input");
        studentInfo.setXmpy(input.get(0).attr("value"));
        studentInfo.setYwmz(input.get(1).attr("value"));
        studentInfo.setLxdh(input.get(2).attr("value"));
        studentInfo.setLxqq(input.get(3).attr("value"));
        studentInfo.setEmail(input.get(4).attr("value"));
        // 个人生源信息
        Element table2 = form.child(1);
        input = table2.select("input");
        studentInfo.setKsh(input.get(0).attr("value"));
        studentInfo.setCym(input.get(1).attr("value"));
        studentInfo.setCsrq(input.get(2).attr("value"));
        studentInfo.setJgjtdz(input.get(3).attr("value"));
        // 证件号, 千万不要在违法犯罪的边缘疯狂试探
//        input.get(4).attr("value")
        studentInfo.setYzbm(input.get(5).attr("value"));
        studentInfo.setRxrq(input.get(6).attr("value"));
        Elements select = table2.select("select");
        studentInfo.setMz(select.get(1).select("option[selected]").text());
        studentInfo.setZzmm(select.get(2).select("option[selected]").text());
        studentInfo.setJgsf(select.get(3).select("option[selected]").text());
        studentInfo.setJgsq(select.get(4).select("option[selected]").text());
        studentInfo.setLysf(select.get(6).select("option[selected]").text());
        return studentInfo;
    }

    /**
     * 信息查询 -> 学籍卡片 -> 照片
     * <p>
     * 获取学生照片
     *
     * @return BufferedImage
     */
    public BufferedImage getStudentImage() {
        String html = client.getBody(host + "xjkpxx!xjkpList.action");
        Document document = Jsoup.parse(html);
        String imageUrl = host + document.select("img").attr("src");
        try {
            byte[] b = client.get(imageUrl, null).body().bytes();
            if ("文件不存在".equals(new String(b))) {
                return null;
            } else {
                return ImageIO.read(new ByteArrayInputStream(b));
            }
        } catch (IOException ignored) {
            return null;
        }
    }

    /**
     * 选课报名 -> 个人选课 -> 可选课程
     * <p>
     * 获取可选选修列表
     *
     * @return List<Elective>
     */
    public List<Elective> getCanChooseElective() {
        Map<String, String> forms = new HashMap<>();
        forms.put("page", "1");
        forms.put("rows", "9999");
        forms.put("sort", "kcrwdm");
        forms.put("order", "asc");
        String json = client.postBody(host + "xsxklist!getDataList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().get("rows").getAsJsonArray();
        return parseJsonArray(jsonArray, Elective.class);
    }

    /**
     * 选课报名 -> 个人选课 -> 已选课程
     * <p>
     * 获取已选选修列表
     *
     * @return List<Elective>
     */
    public List<Elective> getChoseElective() {
        Map<String, String> forms = new HashMap<>();
        forms.put("sort", "kcrwdm");
        forms.put("order", "asc");
        String json = client.postBody(host + "xsxklist!getXzkcList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        return parseJsonArray(jsonArray, Elective.class);
    }

    /**
     * 选课报名 -> 个人选课
     * <p>
     * 选选修
     * <p>
     * 成功返回 1(String)
     *
     * @param kcrwdm 课程任务代码
     * @param kcmc   课程名称
     * @return String
     */
    public String addElective(String kcrwdm, String kcmc) {
        Map<String, String> forms = new HashMap<>();
        forms.put("kcrwdm", kcrwdm);
        forms.put("kcmc", kcmc);
        return client.postBody(host + "xsxklist!getAdd.action", forms);
    }

    /**
     * 选课报名 -> 个人选课
     * <p>
     * 取消已选选修
     * <p>
     * 成功返回 1(String)
     *
     * @param jxbdm  教学班代码
     * @param kcrwdm 课程任务代码
     * @param kcmc   课程名称
     * @return String
     */
    public String removeElective(String jxbdm, String kcrwdm, String kcmc) {
        Map<String, String> forms = new HashMap<>();
        forms.put("jxbdm", jxbdm);
        forms.put("kcrwdm", kcrwdm);
        forms.put("kcmc", kcmc);
        return client.postBody(host + "xsxklist!getCancel.action", forms);
    }

    /**
     * 选课报名 -> 问卷调查 -> 教师评价
     * <p>
     * 获取教师评价列表
     *
     * @param value 参考 {@link ThxyJwgl#getExamResults(String)} 方法的参数value
     * @return 教师评价实体类
     */
    public List<TeacherEvaluation> getTeacherEvaluationList(String value) {
        Map<String, String> forms = new HashMap<>();
        forms.put("xnxqdm", value);
        forms.put("page", "1");
        forms.put("rows", "999");
        forms.put("sort", "pdm");
        forms.put("order", "asc");
        String json = client.postBody(host + "xswjxx!getTeaDataList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().get("rows").getAsJsonArray();
        List<TeacherEvaluation> teacherEvaluationList = parseJsonArray(jsonArray, TeacherEvaluation.class);
        // 获取评教状态
        String html = client.getBody(host + "xswjxx!teaList.action");
        int index = html.indexOf("var finished = JSON.parse('");
        int index2 = html.indexOf(";", index);
        json = html.substring(index + 27, index2 - 2);
        jsonParser.parse(json).getAsJsonArray().forEach(jsonElement -> {
            String pdm = jsonElement.getAsJsonObject().get("pdm").getAsString();
            teacherEvaluationList.forEach(t -> {
                if (pdm.equals(t.getPdm())) {
                    t.setZt("1");
                }
            });
        });
        return teacherEvaluationList;
    }

    /**
     * 一键评教, 满分
     *
     * @param teacherEvaluation 教师评价实体类
     * @param xmbh              评分(A/B/C/D/E), 最多十个选项, 默认A
     * @return "1" = success
     */
    public String teacherEvaluation(TeacherEvaluation teacherEvaluation, String... xmbh) {
        if ("1".equals(teacherEvaluation.getZt())) {
            return "该教师已评价";
        }
        int length = xmbh.length;
        // 大写
        for (int i = 0; i < length; i++) {
            xmbh[i] = xmbh[i].toUpperCase();
        }
        if (length < 10) {
            // 扩容
            xmbh = Arrays.copyOf(xmbh, 10);
            // 补全
            for (int i = length; i < 10; i++) {
                xmbh[i] = "A";
            }
        }
        Map<String, String> parameter = new HashMap<>();
        parameter.put("jxhjmc", jwglEncode(teacherEvaluation.getJxhjmc()));
        parameter.put("kcmc", jwglEncode(teacherEvaluation.getKcmc()));
        parameter.put("pdm", teacherEvaluation.getPdm());
        parameter.put("xnxqdm", teacherEvaluation.getXnxqdm());
        parameter.put("wjdm", teacherEvaluation.getWjdm());
        parameter.put("isyxf", teacherEvaluation.getIsyxf());
        parameter.put("yxfbl", teacherEvaluation.getYxfbl());
        parameter.put("isyjjy", teacherEvaluation.getIsyjjy());
        parameter.put("yjjymc", jwglEncode(teacherEvaluation.getYjjymc()));
        parameter.put("teaxm", jwglEncode(teacherEvaluation.getPjdxmc()));
        parameter.put("wjlx", teacherEvaluation.getWjlx());
        parameter.put("pjdxdm", teacherEvaluation.getPjdxdm());
        parameter.put("pjdxlxdm", teacherEvaluation.getPjdxlxdm());
        parameter.put("jxhjdm", teacherEvaluation.getJxhjdm());
        parameter.put("kcptdm", teacherEvaluation.getKcptdm());
        parameter.put("pjlxdm", teacherEvaluation.getPjlxdm());
        parameter.put("pjdxbh", teacherEvaluation.getPjdxbh());
        parameter.put("isdczbzl", teacherEvaluation.getIsdczbzl());
        parameter.put("pjdxbh", teacherEvaluation.getPjdxbh());
        parameter.put("_", String.valueOf(System.currentTimeMillis()));
        String html = client.getBody(host + "xswjxx!pjTea.action", parameter, (Map<String, String>) null);
        // 解析评价题目
        int index = html.indexOf("var wt = JSON.parse('");
        int index2 = html.indexOf(";", index);
        String json = html.substring(index + 21, index2 - 2);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        List<TeacherEvaluation.EvaluationTopic> evaluationTopicList = parseJsonArray(jsonArray, TeacherEvaluation.EvaluationTopic.class);
        // 解析评价选项
        index = html.indexOf("var wtxm = JSON.parse('");
        index2 = html.indexOf(";", index);
        json = html.substring(index + 23, index2 - 2);
        jsonArray = jsonParser.parse(json).getAsJsonArray();
        List<TeacherEvaluation.EvaluationOption> evaluationOptionList = parseJsonArray(jsonArray, TeacherEvaluation.EvaluationOption.class);
        // 一键评分
        int wtpf = 0;
        String wtdms = "";
        String xmdmvals = "";
        String xmmcs = "";
        String xzfzs = "";

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        System.out.println(teacherEvaluation.getPjdxmc() + " " + teacherEvaluation.getKcmc());
        for (int i = 0; i < evaluationTopicList.size(); i++) {
            for (TeacherEvaluation.EvaluationOption option : evaluationOptionList) {
                if (evaluationTopicList.get(i).getWtdm().equals(option.getWtdm())
                        && xmbh[i].equals(option.getXmbh())) {
                    double fzbl = Double.valueOf(option.getFzbl()) / evaluationTopicList.size();
                    wtpf += fzbl;
                    wtdms += option.getWtdm() + ",";
                    xmdmvals += option.getXmdm() + ",";
                    xmmcs += option.getXmmc() + ",";
                    xzfzs += decimalFormat.format(fzbl) + ",";

                    System.out.println(option.getXmmc() + "-----" + evaluationTopicList.get(i).getWtmc());
                }
            }
        }
        System.out.println();

        // 删除最后一个逗号
        wtdms = wtdms.substring(0, wtdms.length() - 1);
        xmdmvals = xmdmvals.substring(0, xmdmvals.length() - 1);
        xmmcs = xmmcs.substring(0, xmmcs.length() - 1);
        xzfzs = xzfzs.substring(0, xzfzs.length() - 1);

        // 构造表单
        Map<String, String> forms = new HashMap<>();
        forms.put("pdm", teacherEvaluation.getPdm());
        forms.put("wjdm", teacherEvaluation.getWjdm());
        forms.put("pjdxlxdm", teacherEvaluation.getPjdxlxdm());
        forms.put("pjlxdm", teacherEvaluation.getPjlxdm());
        forms.put("kcptdm", teacherEvaluation.getKcptdm());
        forms.put("pjdxbh", teacherEvaluation.getPjdxbh());
        forms.put("pjdxdm", teacherEvaluation.getPjdxdm());
        forms.put("xnxqdm", teacherEvaluation.getXnxqdm());
        forms.put("pjdxmc", teacherEvaluation.getPjdxmc());
        // 总评分
        forms.put("wtpf", String.valueOf(wtpf));
        // 滑块评分 禁用的
        forms.put("yxf", "");
        // 建议
        forms.put("jy", "");
        forms.put("wtdms", wtdms);
        forms.put("xmdmvals", xmdmvals);
        forms.put("xmmcs", xmmcs);
        forms.put("xzfzs", xzfzs);
        return client.postBody(host + "xswjxx!savePj.action", forms);
    }

    /**
     * JsonArray转换成指定的类
     *
     * @param jsonArray jsonArray
     * @param clazz     指定的类
     * @param <T>       T
     * @return List<T>
     */
    private <T> List<T> parseJsonArray(JsonArray jsonArray, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Object e : jsonArray) {
            list.add(gson.fromJson((JsonElement) e, clazz));
        }
        return list;
    }

    private String jwglEncode(String str) {
        return urlEncoder(escape(str));
    }

    private String urlEncoder(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }
}
