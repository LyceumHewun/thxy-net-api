package cc.lyceum.api.thxy.logistics;

import cc.lyceum.api.thxy.Client;
import cc.lyceum.api.thxy.ClientFactory;
import cc.lyceum.api.thxy.logistics.pojo.Ammeter;
import cc.lyceum.api.thxy.logistics.pojo.FreeInfo;
import cc.lyceum.api.thxy.logistics.pojo.PaymentRecord;
import cc.lyceum.api.thxy.logistics.pojo.UseInfo;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后勤处
 *
 * @author LyceumHewun
 * @date 2018-9-6
 */
public class ThxyHqc {

    private Client client = ClientFactory.creatClient("GB2312");

    /**
     * 保修
     */
    private String host1 = "http://10.0.8.48/";
    /**
     * 查电
     */
    private String host2 = "http://10.0.8.50/";

    public ThxyHqc() {
    }

    public ThxyHqc(String... host) {
        if (!"".equals(host[0]) && !"default".equals(host[0])) {
            this.host1 = host[0];
        }
        if (!"".equals(host[1]) && !"default".equals(host[1])) {
            this.host2 = host[1];
        }
    }

    /**
     * 发送保修单</br>
     * <p>
     * -- Building_ID --
     * 1    1#A学生宿舍</br>
     * 2    1#B学生宿舍</br>
     * 3    1#C学生宿舍</br>
     * 4    2#学生宿舍</br>
     * 5    2#B学生宿舍</br>
     * 6    3#学生宿舍</br>
     * 7    4#学生宿舍</br>
     * 8    5#学生宿舍</br>
     * 9    6#学生宿舍</br>
     * 10   7#A学生宿舍</br>
     * 11   7#B学生宿舍</br>
     * 12   8#学生宿舍</br>
     * 13   9#学生宿舍</br>
     * 14   10#学生宿舍</br>
     * 15   11#A学生宿舍</br>
     * 16   11#B学生宿舍</br>
     * 17   12#学生宿舍</br>
     * 18   13#学生宿舍</br>
     * 19   14#学生宿舍</br>
     * 20   15#学生宿舍</br>
     * 21   16#A学生宿舍</br>
     * 22   16#B学生宿舍</br>
     * 23   16#C学生宿舍</br>
     * 24   16#D学生宿舍</br>
     * 25   17#学生宿舍</br>
     * 26   18#学生宿舍</br>
     * 27   19#A学生宿舍</br>
     * 28   19#B学生宿舍</br>
     * 29   19#C学生宿舍</br>
     * 30   20#A学生宿舍</br>
     * 31   20#B学生宿舍</br>
     * 32   21#学生宿舍</br>
     * 33   22#学生宿舍</br>
     * 34   22#B学生宿舍</br>
     * 35   23#A学生宿舍</br>
     * 36   23#B学生宿舍</br>
     * 37   28#学生宿舍</br>
     * 38   1#教学楼</br>
     * 39   2#教学楼</br>
     * 40   3#教学楼</br>
     * 41   4#教学楼</br>
     * 42   5#教学楼</br>
     * 43   6#教学楼</br>
     * 44   7#教学楼（综合楼）</br>
     * 45   8#教学楼</br>
     * 46   9#教学楼</br>
     * 47   10#教学楼</br>
     * 48   图书馆</br>
     * 49   0#教工宿舍</br>
     * 50   1#教工宿舍</br>
     * 51   2#教工宿舍</br>
     * 52   3#教工宿舍</br>
     * 53   4#教工宿舍</br>
     * 54   汽修楼</br>
     * 55   5#教工宿舍</br>
     * 56   6#教工宿舍</br>
     * 57   2饭堂职工宿舍</br>
     * 58   1#教授楼</br>
     * 59   1#饭堂</br>
     * 60   2#饭堂</br>
     * 61   3#饭堂</br>
     * 62   后勤楼</br>
     * 63   24#A学生宿舍</br>
     * 64   24#B学生宿舍</br>
     * 65   26#学生宿舍</br>
     * 66   27#学生宿舍</br>
     * 67   7#教工楼</br>
     * 68   信息楼（11#教学楼）</br>
     * 69   12#教学楼</br>
     * 70   大运动场</br>
     * 71   小运动场</br>
     * 72   体育馆</br>
     * <p>
     * -- Gzlx_ID --
     * 1   供水（冷）</br>
     * 2   供水（热）</br>
     * 3   电类</br>
     * 4   排水</br>
     * 5   基建类</br>
     * 6   资产类</br>
     * 7   门窗类</br>
     * 8   空调</br>
     *
     * @param username    姓名
     * @param telephone   联系电话
     * @param Building_ID 楼栋
     * @param Room_ID     宿舍号
     * @param Gzlx_ID     故障类型
     * @param description 描述
     * @return 保修单号
     */
    public String sendRepairOrder(String username, String telephone, String Building_ID, String Room_ID, String Gzlx_ID, String description) {
        Map<String, String> forms = new HashMap<>();
        forms.put("username", username);
        forms.put("telephone", telephone);
        forms.put("Building_ID", Building_ID);
        forms.put("Room_ID", Room_ID);
        forms.put("Gzlx_ID", Gzlx_ID);
        forms.put("description", description);
        forms.put("check", "yes");
        Client client = ClientFactory.creatClient();
        String html = client.postBody(host1 + "hq/baoxiu_do.php", forms);
        String repairOrder = html.substring(html.indexOf("您的报修单号是：") + 8);
        repairOrder = repairOrder.substring(0, repairOrder.indexOf("，"));
        return repairOrder;
    }


    /**
     * 登陆电费系统
     * <p>
     * -- sectid --
     * 242   24B</br>
     * 241   24A</br>
     * 230   23栋B</br>
     * 201   20栋B</br>
     * 200   20A栋</br>
     * 192   19栋C</br>
     * 191   19栋B</br>
     * 190   19栋A</br>
     * 163   16栋D</br>
     * 162   16栋C</br>
     * 161   16栋B</br>
     * 110   11栋B</br>
     * 109   10教学楼</br>
     * 102   1栋C</br>
     * 101   1栋B</br>
     * 100   1栋A</br>
     * 41    新区配电</br>
     * 40    图书馆</br>
     * 39    3教学楼</br>
     * 38    1号员工</br>
     * 37    教授楼</br>
     * 36    6栋教工</br>
     * 35    汽训楼</br>
     * 34    4栋教工</br>
     * 33    3栋教工</br>
     * 32    2栋教工</br>
     * 31    1栋教工</br>
     * 30    5栋教工</br>
     * 29    2B栋</br>
     * 28    28栋</br>
     * 23    23栋</br>
     * 22    22栋</br>
     * 21    21栋</br>
     * 20    2号员工</br>
     * 18    18栋</br>
     * 17    17栋</br>
     * 16    16栋A</br>
     * 15    15栋</br>
     * 14    14栋</br>
     * 13    13栋</br>
     * 12    12栋</br>
     * 11    11栋A</br>
     * 10    10栋</br>
     * 9     9栋</br>
     * 8     8栋</br>
     * 7     7栋</br>
     * 6     6栋</br>
     * 5     5栋</br>
     * 4     4栋</br>
     * 3     3栋</br>
     * 2     2栋</br>
     * 0     0栋</br>
     *
     * @param sectid    楼栋
     * @param user_no   宿舍号
     * @param user_pass 密码
     * @return 登陆状态和异常情况
     */
    public String login(String sectid, String user_no, String user_pass) {
        Map<String, String> forms = new HashMap<>();
        forms.put("extentid", "1");
        forms.put("sectid", sectid);
        forms.put("user_no", user_no);
        forms.put("user_pass", user_pass);
        String html = client.postBody(host2 + "login.asp", forms);
        if (html.contains("history.back()")) {
            html = html.substring(html.indexOf("alert('") + 7, html.indexOf("');")).replace("请返回……", "");
            return html;
        } else {
            return "success";
        }
    }

    /**
     * 主界面
     * <p>
     * 查用电情况
     * <p>
     * 先使用 {@link ThxyHqc#login(String, String, String)} 登陆
     *
     * @return Ammeter
     */
    public Ammeter getAmmeter() {
        String html = client.getBody(host2 + "mainn.asp");
        Elements elements = Jsoup.parse(html).select("table").get(1).select("tr").get(1).select("td");
        return new Ammeter(
                elements.get(0).text(),
                elements.get(1).text(),
                elements.get(2).text(),
                elements.get(3).text(),
                elements.get(4).text()
        );
    }

    /**
     * 主界面 -> 历史电量查询
     * <p>
     * 根据年查用电情况
     *
     * @param year 年
     * @return UseInFO
     */
    public List<UseInfo> getUserInfo(String year) {
        Map<String, String> forms = new HashMap<>();
        forms.put("Sp_id", year);
        forms.put("St_class", "");
        forms.put("Submit", "+/v8- +//3//Q- +//3//Q- ");
        String html = client.postBody(host2 + "payhisguest.asp", forms);
        try {
            Elements elements = Jsoup.parse(html).select("table").get(1).select("tr[bgcolor=#FFFFFF]");
            List<UseInfo> list = new ArrayList<>();
            elements.forEach(e -> {
                Elements es = e.select("td");
                list.add(new UseInfo(
                        es.get(0).text(),
                        es.get(1).text(),
                        es.get(2).text(),
                        es.get(3).text(),
                        es.get(4).text(),
                        es.get(5).text(),
                        es.get(6).text(),
                        es.get(7).text(),
                        es.get(8).text()
                ));
            });
            return list;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * 主界面 -> 免费电量查询
     * <p>
     * 根据年查免费送电情况
     *
     * @param year 年
     * @return FreeInfo
     */
    public List<FreeInfo> getFreeInfo(String year) {
        Map<String, String> forms = new HashMap<>();
        forms.put("Sp_id", year);
        forms.put("St_class", "");
        forms.put("Submit", "+/v8- +//3//Q- +//3//Q- ");
        String html = client.postBody(host2 + "payfreeguest.asp", forms);
        try {
            Elements elements = Jsoup.parse(html).select("table").get(1).select("tr[bgcolor=#FFFFFF]");
            List<FreeInfo> list = new ArrayList<>();
            elements.forEach(e -> {
                Elements es = e.select("td");
                list.add(new FreeInfo(
                        es.get(0).text(),
                        es.get(1).text(),
                        es.get(2).text(),
                        es.get(3).text(),
                        es.get(4).text(),
                        es.get(5).text(),
                        es.get(6).text(),
                        es.get(7).text(),
                        es.get(8).text()
                ));
            });
            return list;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * 主界面 -> 缴费查询
     * <p>
     * 根据年查缴费情况
     *
     * @param year 年
     * @return PaymentRecord
     */
    public List<PaymentRecord> getPaymentRecord(String year) {
        Map<String, String> forms = new HashMap<>();
        forms.put("Sp_id", year);
        forms.put("St_class", "");
        forms.put("Submit", "+/v8- +//3//Q- +//3//Q- ");
        String html = client.postBody(host2 + "payguest.asp", forms);
        try {
            Elements elements = Jsoup.parse(html).select("table").get(1).select("tr[bgcolor=#FFFFFF]");
            List<PaymentRecord> list = new ArrayList<>();
            elements.forEach(e -> {
                Elements es = e.select("td");
                list.add(new PaymentRecord(
                        es.get(0).text(),
                        es.get(1).text(),
                        es.get(2).text(),
                        es.get(3).text(),
                        es.get(4).text(),
                        es.get(5).text()
                ));
            });
            return list;
        } catch (Exception ignored) {
            return null;
        }
    }

}
