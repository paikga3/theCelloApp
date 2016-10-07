package the.cello;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by topclass on 2016-09-13.
 */
public class QueryString {

    private StringBuilder query = new StringBuilder();
    public QueryString() {

    }

    public synchronized void add(String name, String value) {
        query.append('&');
        encode(name, value);
    }

    private synchronized void encode(String name, String value) {
        try {
            query.append(URLEncoder.encode(name, "UTF-8"))
                    .append('=')
                    .append(URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }

    public synchronized String getQuery() {
        return query.toString();
    }

    @Override
    public String toString() {
        return getQuery();
    }
}
