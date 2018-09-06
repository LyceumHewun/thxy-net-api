package cc.lyceum.api.thxy;

/**
 * @author LyceumHewun
 * @date 2018-9-6
 */
public class ClientFactory {

    public static final int HTTP = 1;
    public static final int HTTPS = 2;
    public static final int HTTPS_POST_JSON = 3;

    public static Client creatClient(int type, String charsetName) {
        if (type == HTTP) {
            return new HttpClient(charsetName);
        } else if (type == HTTPS) {
            return new NotSafeHttpsClient(charsetName);
        } else if (type == HTTPS_POST_JSON) {
            return new RequestJsonBodyNotSafeHttpsClient(charsetName);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Client creatClient(int type) {
        return creatClient(type, "UTF-8");
    }

    public static Client creatClient(String charsetName) {
        return creatClient(HTTP, charsetName);
    }

    public static Client creatClient() {
        return creatClient(HTTP);
    }
}
