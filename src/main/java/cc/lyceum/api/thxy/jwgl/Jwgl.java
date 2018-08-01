package cc.lyceum.api.thxy.jwgl;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教务系统接口
 *
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-08-01
 */
public class Jwgl extends JwglClient {

    private String host = "http://jwgl.thxy.cn/";

    public void setHost(String host) {
        this.host = "http://" + host + "/";
    }

    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();

    public String login(String userNumber, String password) {
        // 验证码识别
        String code = Dictionary.contrast(new ImageHelper(getCodeImge())
                .filling()
                .spilt());
        Map<String, String> forms = new HashMap<>();
        forms.put("account", userNumber);
        forms.put("pwd", password);
        forms.put("verifycode", code);
        String json = super.postBody(host + "login!doLogin.action", forms);
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        boolean status = jsonObject.get("status").getAsString().equals("y");
        String message = jsonObject.get("msg").getAsString();
        if (status) {
            return "登陆成功";
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

    private BufferedImage getCodeImge() {
        super.getBody(host);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(super.get(host + "yzm", null).body().bytes());
            return ImageIO.read(in);
        } catch (IOException ignored) {
            return null;
        }
    }

    /**
     * 主页学习情况，可以看到学分、绩点，但是没有学习计划准确</br>
     * 建议使用 {@link Jwgl#getStudyPlan()}
     *
     * @return tjmc1 对应 tjz1, tjmc2、tjmc3亦如此
     */
    @Deprecated
    public Map<String, String> getStudyStatus() {
        super.getBody(host + "xxzyxx!reGet.action");
        String json = super.postBody(host + "xxzyxx!xxzyList.action", null);
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

    public boolean logout() {
        return super.getBody(host + "login!logout.action").equals("1");
    }

    /**
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
        String json = super.postBody(host + "xskccjxx!getDataList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().getAsJsonArray("rows");
        return parseJsonArray(jsonArray, ExamResults.class);
    }

    /**
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
        String json = super.postBody(host + "xsjxjhxx!getDataList1.action", forms);
        JsonObject jsonObject = (JsonObject) jsonParser.parse(json).getAsJsonObject().get("rows").getAsJsonArray().get(0);
        String jxjhdm = jsonObject.get("jxjhdm").getAsString();
        String jhlxdm = jsonObject.get("jhlxdm").getAsString();
        // second
        String html = super.getBody(host + "xsjxjhxx!xsxxjhMain.action?jxjhdm=" + jxjhdm + "&jhlxdm=" + jhlxdm);
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
     * 根据日期查当天课程
     *
     * @param date yyyy-MM-dd  2018-07-30
     * @return 课程实体类集合
     */
    public List<Curriculum> getCurriculumByDate(String date) {
        String json = super.getBody(host + "desktop!xskb.action?rq=" + date);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        return parseJsonArray(jsonArray, Curriculum.class);
    }

    /**
     * 根据学期和周次查课程
     *
     * @param value 参考 {@link Jwgl#getExamResults(String)} 方法的参数value
     * @param week  第几周
     * @return 课程实体类集合
     */
    public List<Curriculum> getCurriculumByWeek(String value, String week) {
        String josn = super.getBody(host + "xsgrkbcx!getKbRq.action?xnxqdm=" + value + "&zc=" + week);
        JsonArray jsonArray = jsonParser.parse(josn).getAsJsonArray().get(0).getAsJsonArray();
        return parseJsonArray(jsonArray, Curriculum.class);
    }

    /**
     * 根据学期查课程
     *
     * @param value 参考 {@link Jwgl#getExamResults(String)} 方法的参数value
     * @return 课程实体类集合
     */
    public List<Curriculum> getCurriculum(String value) {
        String html = super.getBody(host + "xsgrkbcx!xsAllKbList.action?xnxqdm=" + value, null);
        html = html.substring(html.indexOf("var kbxx = "));
        String json = html.substring(11, html.indexOf(";"));
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        return parseJsonArray(jsonArray, Curriculum.class);
    }

    /**
     * 查考级成绩
     *
     * @return 考级成绩实体类集合
     */
    public List<GradeExamination> getGradeExamination() {
        Map<String, String> forms = new HashMap<>();
        forms.put("page", "1");
        forms.put("rows", "20");
        forms.put("sort", "xnxqdm");
        forms.put("order", "asc");
        String json = super.postBody(host + "xskjcjxx!getDataList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().getAsJsonArray("rows");
        return parseJsonArray(jsonArray, GradeExamination.class);
    }

    /**
     * 根据学期查考试安排
     *
     * @param value 参考 {@link Jwgl#getExamResults(String)} 方法的参数value
     * @return 考试安排实体类集合
     */
    public List<ExamArrange> getExamArrange(String value) {
        Map<String, String> forms = new HashMap<>();
        forms.put("xnxqdm", value);
        forms.put("examname", "");
        forms.put("placename", "");
        forms.put("ksxz", "");
        String json = super.postBody(host + "ksxxgl!getMyExam.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        return parseJsonArray(jsonArray, ExamArrange.class);
    }

    /**
     * 根据学期查课程
     *
     * @param value 参考 {@link Jwgl#getExamResults(String)} 方法的参数value
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
        String json = super.postBody(host + "xskktzd!getDataList.action", forms);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().getAsJsonArray("rows");
        return parseJsonArray(jsonArray, ClassTask.class);
    }

    /**
     * 查个人信息
     *
     * @return 学生个人信息实体类
     */
    public StudentInfo getStudentInfo() {
        String html = super.getBody(host + "xjkpxx!xjkpList.action");
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
        studentInfo.setWyyz(label.get(5).text());
        studentInfo.setBj(label.get(6).text());
        studentInfo.setSznj(label.get(7).text());
        studentInfo.setFxzy(label.get(8).text());
        studentInfo.setSzxq(label.get(9).text());
        studentInfo.setXszt(label.get(10).text());
        studentInfo.setXjzt(label.get(11).text());
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
        //证件号
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

    private <T> List<T> parseJsonArray(JsonArray jsonArray, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Object e : jsonArray) {
            list.add(gson.fromJson((JsonElement) e, clazz));
        }
        return list;
    }
}
