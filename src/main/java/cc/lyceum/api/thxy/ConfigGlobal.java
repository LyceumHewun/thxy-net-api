package cc.lyceum.api.thxy;

import java.io.IOException;
import java.util.Properties;

/**
 * @author LyceumHewun
 * @date 2018-9-7
 */
public class ConfigGlobal {

    public static String JWGL_HOST;
    public static String HQC_BX_HOST;
    public static String HQC_CD_HOST;
    public static String IMC_HOST;
    public static String SELF_HOST;

    static {
        try {
            Properties properties = new Properties();
            properties.load(ConfigGlobal.class.getResourceAsStream("/thxy.properties"));
            JWGL_HOST = (String) properties.getOrDefault("host.jwgl", "http://jwgl.thxy.cn/");
            HQC_BX_HOST = (String) properties.getOrDefault("host.hqc.bx", "http://10.0.8.48/");
            HQC_CD_HOST = (String) properties.getOrDefault("host.hqc.cd", "http://10.0.8.50/");
            IMC_HOST = (String) properties.getOrDefault("host.imc", "http://10.0.8.9:8080/");
            SELF_HOST = (String) properties.getOrDefault("host.self", "http://self.thxy.cn:8080/selfservice/");
        } catch (IOException ignored) {
        }
    }
}
