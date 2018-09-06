package cc.lyceum.api.thxy.network;

import cc.lyceum.api.thxy.Client;
import cc.lyceum.api.thxy.ClientFactory;
import cc.lyceum.api.thxy.network.pojo.TollCycle;
import okhttp3.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自助平台
 *
 * @author LyceumHewun
 * @date 2018-9-6
 */
public class ThxySelfService {

    private Client client = ClientFactory.creatClient();

    private String host = "http://self.thxy.cn:8080/selfservice/";

    public ThxySelfService() {
    }

    public ThxySelfService(String host) {
        this.host = host;
    }

    /**
     * 获取验证码url、cookie、state
     *
     * @return state和验证码url
     */
    private Map<String, String> getState() {
        String body = client.getBody(host + "login.jsf");
        Map<String, String> result = new HashMap<>(2);
        Document document = Jsoup.parse(body);
        // 后面网页上获取的url里面包含/selfservice/
        result.put("codeUrl", host.replaceAll("/selfservice/", "") + document.select("[align=absmiddle]").get(0).empty().attr("src"));
        result.put("state", document.select("[id=javax.faces.ViewState]").get(0).empty().attr("value"));
        return result;
    }

    /**
     * 登陆
     *
     * @param usernumber 学号
     * @param password   校园网密码
     * @return 登陆状态信息
     */
    public String login(String usernumber, String password) {
        Map<String, String> info = this.getState();
        String codeUrl = info.get("codeUrl");
        String state = info.get("state");
        // 识别验证码
        String code = new Discern().getCode(codeUrl);
        // 识别失败，重来
        if (code == null) {
            return login(usernumber, password);
        }
        // headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Referer", host + "login.jsf");
        // forms
        Map<String, String> forms = new HashMap<>();
        forms.put("mainForm:username", usernumber);
        forms.put("mainForm:password", password);
        forms.put("mainForm:code", code);
        forms.put("mainForm:usertype_input", "1");
        forms.put("mainForm:usertype_focus", "");
        forms.put("mainForm:loginCmd", "");
        forms.put("mainForm:ifCheckCode", "true");
        forms.put("mainForm:descContent", "%E8%AE%BF%E5%AE%A2%E7%AE%A1%E7%90%86%E5%91%98%E7%AE%A1%E7%90%86%E8%AE%BF%E5%AE%A2");
        forms.put("mainForm_SUBMIT", "1");
        forms.put("javax.faces.ViewState", state);

        String result = client.postBody(host + "login.jsf", headers, forms);
        String error = "fail";
        Document document = Jsoup.parse(result);

        if (!document.select("span[class=ui-message-error-detail]").isEmpty()) {
            error = document.select("span[class=ui-message-error-detail]").get(0).text();
            if (error.contains("密码错误") || error.contains("黑名单")) {
                return error;
            } else if (error.contains("验证码错误")) {
                return login(usernumber, password);
            }
        }

        for (Map.Entry<String, List<Cookie>> cookies : client.getCookieStore().entrySet()) {
            for (Cookie cookie : cookies.getValue()) {
                if ("oam.Flash.REDIRECT".equals(cookie.name())) {
                    return "success";
                }
            }
        }

        return error;
    }

    /**
     * 用户资料维护 -> 查询用户资料
     * <p>
     * 获取个人信息及帐号余额
     *
     * @return Map<String, String>
     */
    public Map<String, String> getInfo() {
        Map<String, String> result = new HashMap<>();
        String html = client.getBody(host + "maintain/main.jsf");
        try {
            Document document = Jsoup.parse(html);
            result.put("name", document.select("td[class=cmn_wdper_30]").get(4).text());
            result.put("IDNumber", document.select("td[class=cmn_wdper_30]").get(5).text());
            result.put("dorm", document.select("td[class=cmn_wdper_30]").get(6).text());
            result.put("college", document.select("td[class=cmn_wdper_30]").get(10).text().replace(" ", ""));
            result.put("class", document.select("td[class=cmn_wdper_30]").get(11).text().replace(" ", ""));
            result.put("surplusMoney", document.select("td[class=cmn_wdper_30]").get(19).text());
            return result;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * 自助营业厅 -> 在线用户列表
     * <p>
     * 获取在线设备的MAC地址
     *
     * @return String
     */
    public String getOnlineEquipmentMACAddress() {
        String result = client.getBody(host + "fee/onlineDetail.jsf");
        try {
            Document document = Jsoup.parse(result);
            try {
                return document.select("td[role=gridcell]").get(7).text();
            } catch (Exception ignored) {
                return "free";
            }
        } catch (Exception ignored) {
            return "fail";
        }
    }

    /**
     * 终端设备管理 -> 终端设备列表
     * <p>
     * 清空终端设备列表
     *
     * @return String
     */
    public String removeMacList() {
        // get
        String result = client.getBody(host + "snac/macList.jsf");
        try {
            Document document = Jsoup.parse(result);
            String ViewState = document.select("input[name=javax.faces.ViewState]").get(0).attr("value");
            int num = 0;
            StringBuilder data = new StringBuilder();
            for (Element element : document.select("tbody[id=mainForm:data_data]").get(0).children()) {
                data.append(",").append(element.attr("data-rk"));
                // 设备总数
                num++;
            }

            // 去除第一个逗号
            if (!"".equals(data.toString())) {
                data = new StringBuilder(data.substring(1));
            }

            // 绑定设备数为零
            if (num == 0) {
                return "success";
            }

            // post

            // headers
            Map<String, String> headers = new HashMap<>();
            headers.put("Referer", host + "snac/macList.jsf");

            // forms
            Map<String, String> forms = new HashMap<>();
            forms.put("mainForm:data_selection", data.toString());
            forms.put("mainForm_SUBMIT", "1");
            forms.put("javax.faces.ViewState", ViewState);
            forms.put("mainForm:delete", "");
            for (int i = 0; i < num; i++) {
                forms.put("mainForm:data_checkbox", "on");
            }

            client.post(host + "snac/macList.jsf", headers, forms);

            return "success";

        } catch (Exception ignored) {
            return "fail";
        }
    }

    /**
     * 自助营业厅 -> 在线用户列表
     * <p>
     * 清除在线(踢下线)
     *
     * @return String
     */
    public String clearLogin() {
        try {
            // get
            String result = client.getBody(host + "fee/onlineDetail.jsf");
            Document document = Jsoup.parse(result);
            String ViewState = document.select("input[name=javax.faces.ViewState]").get(0).attr("value");
            // post
            // forms
            Map<String, String> forms = new HashMap<>(3);
            forms.put("mainForm_SUBMIT", "1");
            forms.put("javax.faces.ViewState", ViewState);
            forms.put("mainForm:data:0:j_id_3j", "mainForm:data:0:j_id_3j");
            client.postBody(host + "fee/onlineDetail.jsf", forms);
            return "success";
        } catch (Exception ignored) {
        }
        return "fail";
    }

    /**
     * 自助营业厅 -> 查询缴费记录
     * <p>
     * 充值记录
     *
     * @return Map<Integer, Map<String, String>>
     */
    public Map<Integer, Map<String, String>> getPaymentRecord() {
        Map<Integer, Map<String, String>> result = new HashMap<>();
        String html = client.getBody(host + "fee/paymentRecord.jsf");
        try {
            Document document = Jsoup.parse(html);
            document.select("tr[data-ri]").forEach(element ->
                    result.put(Integer.valueOf(element.attr("data-ri")), new HashMap<String, String>() {
                        {
                            put("time", element.child(0).text());
                            put("money", element.child(2).text());
                        }
                    })
            );
            return result;
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * 退出登录
     */
    public void logout() {
        client.getBody(host + "logout.xhtml");
    }

    /**
     * 用户资料维护 -> 查询用户资料 -> 已申请的服务信息
     * <p>
     * 收费周期
     *
     * @return TollCycle
     */
    public TollCycle getTollCycle() {
        String html = client.getBody(host + "maintain/main.jsf");
        Document document = Jsoup.parse(html);
        Elements elements = document.select("tbody[id=mainForm:j_id_4y_data]").select("td");
        return new TollCycle(
                elements.get(0).select("span").text(),
                elements.get(1).select("span").text(),
                elements.get(2).select("span").text(),
                elements.get(3).select("span").text(),
                elements.get(4).select("span").text()
        );
    }
}
