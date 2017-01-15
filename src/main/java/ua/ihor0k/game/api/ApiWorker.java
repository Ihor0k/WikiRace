package ua.ihor0k.game.api;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ihor0k.game.model.Page;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

@Service
public class ApiWorker {
    private static final String URL = "https://ru.wikipedia.org";
    private static final String GET_BODY_HTML = "/w/api.php?action=parse&prop=text&page={0}&redirects=1&format=json&utf8=1";
    private static final String GET_RANDOM_PAGE = "/w/api.php?action=query&prop=info|extracts&exsentences=5&explaintext=1&generator=random&inprop=url&grnnamespace=0&grnfilterredir=nonredirects&formatversion=2&format=json&utf8=1";
    private static final String GET_PAGE = "/w/api.php?action=query&prop=info&titles={0}&inprop=url&redirects=1&&formatversion=2&format=json&utf8=1";

    private HttpConnector httpConnector;

    public Page getPage(String pageName) {
        String urn = MessageFormat.format(GET_PAGE, pageName);
        JSONParser parser = new JSONParser();
        try (Reader reader = getReader(urn)) {
            JSONObject object = (JSONObject) parser.parse(reader);
            object = (JSONObject) object.get("query");
            JSONArray array = (JSONArray) object.get("pages");
            object = (JSONObject) array.get(0);
            String title = (String) object.get("title");
            String url = (String) object.get("fullurl");
            return new Page(title, url, pageName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Page getRandomPage() {
        JSONParser parser = new JSONParser();
        try (Reader reader = getReader(GET_RANDOM_PAGE)) {
            JSONObject object = (JSONObject) parser.parse(reader);
            object = (JSONObject) object.get("query");
            JSONArray array = (JSONArray) object.get("pages");
            object = (JSONObject) array.get(0);
            String title = (String) object.get("title");
            String pageName = title.replace(' ', '_');
            String url = (String) object.get("fullurl");
            String description = (String) object.get("extract");
            return new Page(title, url, pageName, description);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getBodyHtml(String pageName) {
        String urn = MessageFormat.format(GET_BODY_HTML, pageName);
        JSONParser parser = new JSONParser();
        try (Reader reader = httpConnector.getReader(URL + urn)) {
            JSONObject object = (JSONObject) parser.parse(reader);
            object = (JSONObject) object.get("parse");
            object = (JSONObject) object.get("text");
            return object.get("*").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Reader getReader(String urn) {
        try {
            URL url = new URL(URL + urn);
            URLConnection conn = url.openConnection();
            return new InputStreamReader(conn.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Autowired
    public void setHttpConnector(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    @Service
    protected static class HttpConnector {
        private CloseableHttpClient httpClient;
        private RequestConfig localConfig;

        @PostConstruct
        private void init() {
            RequestConfig globalConfig = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.DEFAULT)
                    .build();
            localConfig = RequestConfig.copy(globalConfig)
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .build();
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(globalConfig)
                    .build();
        }

        private Reader getReader(String url) throws IOException {
            HttpGet request = new HttpGet(url);
            request.setConfig(localConfig);
            CloseableHttpResponse response = httpClient.execute(request);
            return new InputStreamReader(response.getEntity().getContent(), "UTF-8");

        }
    }
}
