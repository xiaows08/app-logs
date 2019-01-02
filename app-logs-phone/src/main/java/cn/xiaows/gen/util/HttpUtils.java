package cn.xiaows.gen.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class HttpUtils {

    private static HttpClient client = HttpClientBuilder.create().build();

    /**
     * 模拟发送数据
     * @param json
     */
    public static int upload(String json, String url) {
        // System.out.println(json);
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(json, "UTF-8"));
        try {
            HttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            // log.debug("Send log status: {}", statusCode);
            response.getEntity().getContent().close();//关闭资源
            return statusCode;
        } catch (IOException e) {
            // e.printStackTrace();
            log.error(e.getMessage());
        } finally {
        }
        return -1;
    }

    @Deprecated
    private static String kafkaUrl = "http://172.22.1.14/kafka/user";

    @Deprecated
    public static void upload0(String json) {
        System.out.println(json);
        try {
            URL url = new URL(kafkaUrl);
            HttpURLConnection conn = (HttpURLConnection) url.getContent();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("clientTime", String.valueOf(System.currentTimeMillis()));
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();
            System.out.println(conn.getResponseCode());
        } catch (IOException e) {
            // e.printStackTrace();
            log.error("upload FIELD! {}", e.getMessage());
        }
    }

    @Deprecated
    public static void upload1(String json) {
        // InputStream is = ClassLoader.getSystemResourceAsStream("");
        System.out.println(json);
        try {
            URL url = new URL(kafkaUrl);
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("clientTime", String.valueOf(System.currentTimeMillis()));
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            osw.write(json);
            osw.flush();
            osw.close();
        } catch (IOException e) {
            // e.printStackTrace();
            log.error("upload FIELD! {}", e.getMessage());
        }
    }
}
