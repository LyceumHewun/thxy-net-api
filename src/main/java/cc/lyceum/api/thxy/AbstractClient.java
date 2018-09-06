package cc.lyceum.api.thxy;

import java.io.IOException;
import java.util.Map;

/**
 * @author LyceumHewun
 * @date 2018-9-6
 */
public abstract class AbstractClient implements Client {

    /**
     * 默认UTF-8
     */
    private String charsetName = "UTF-8";

    AbstractClient() {
    }

    AbstractClient(String charsetName) {
        this.charsetName = charsetName;
    }

    @Override
    public String getBody(String url, Map<String, String> headers, String charsetName) {
        try {
            return new String(get(url, headers).body().bytes(), charsetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String postBody(String url, Map<String, String> headers, Map<String, String> forms, String charsetName) {
        try {
            return new String(post(url, headers, forms).body().bytes(), charsetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getBody(String url, Map<String, String> headers) {
        return getBody(url, headers, charsetName);
    }

    @Override
    public String postBody(String url, Map<String, String> headers, Map<String, String> forms) {
        return postBody(url, headers, forms, charsetName);
    }

    @Override
    public String getBody(String url) {
        return getBody(url, null, charsetName);
    }

    @Override
    public String postBody(String url, Map<String, String> forms) {
        return postBody(url, null, forms);
    }

    @Override
    public String getBody(String url, String charsetName) {
        return getBody(url, null, charsetName);
    }

    @Override
    public String postBody(String url, Map<String, String> forms, String charsetName) {
        return postBody(url, null, forms, charsetName);
    }
}
