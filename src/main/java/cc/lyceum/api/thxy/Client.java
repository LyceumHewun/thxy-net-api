package cc.lyceum.api.thxy;

import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public interface Client {

    public Response get(String url, Map<String, String> headers) throws IOException;

    public Response post(String url, Map<String, String> headers, Map<String, String> forms) throws IOException;

    default String getBody(String url, Map<String, String> headers) {
        try {
            return get(url, headers).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    default String postBody(String url, Map<String, String> headers, Map<String, String> forms) {
        try {
            return post(url, headers, forms).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    default String getBody(String url) {
        return getBody(url, null);
    }

    default String postBody(String url, Map<String, String> forms) {
        return postBody(url, null, forms);
    }
}
