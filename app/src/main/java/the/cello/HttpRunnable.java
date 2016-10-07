package the.cello;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by topclass on 2016-09-24.
 */

public class HttpRunnable implements Runnable {
    private QueryString params;
    private String suvURL;
    private URL url;

    private final String serverURL = "http://cellomotel.com/reservation";
    public HttpRunnable(QueryString params, String suvURL) {
        this.params = params;
        this.suvURL = suvURL;

    }

    @Override
    public void run() {
        OutputStreamWriter out = null;
        try {
            url = new URL(serverURL + suvURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            conn.setDoOutput(true);
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(params.toString());
            out.flush();
            int responseCode = conn.getResponseCode();
            if(responseCode < 200 || responseCode >= 300) {
                return;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
