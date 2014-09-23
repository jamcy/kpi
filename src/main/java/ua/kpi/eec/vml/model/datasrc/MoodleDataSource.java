package ua.kpi.eec.vml.model.datasrc;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import ua.kpi.eec.vml.common.ConfigurationHelper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class MoodleDataSource {

    private static MoodleDataSource instance = null;

    private String moodleToken = "283b0a6a47b8da9e3a55f7ab87cc86e8";
    private String moodleBase = "http://moodle.vml.eec.kpi.ua";

    public static final String FUNCTION_GET_USER_BY_TOKEN = "core_webservice_get_site_info";
    public static final String FUNCTION_GET_USER_BY_ID = "core_user_get_users_by_id";
    public static final String FUNCTION_GET_COURSES = "core_course_get_courses";
    public static final String FUNCTION_GET_COURSE_CONTENTS = "core_course_get_contents";

    private MoodleDataSource() {
        this.moodleBase = ConfigurationHelper.getMoodleBase();
        try {
            this.moodleToken = this.getToken(ConfigurationHelper.getMoodleUser(), ConfigurationHelper.getMoodlePassword(), "vml");
        } catch (MoodleRequestException e) {

        }
    }

    public static MoodleDataSource getInstance() {
        if (instance == null) {
            instance = new MoodleDataSource();
        }
        return instance;
    }

    public String getToken(String username, String password, String service)
            throws MoodleRequestException {
        String url = this.moodleBase + "/login/token.php" + "?username="
                + username + "&password=" + password + "&service=" + service;
        System.out.println(url);
        String result = null;
        try {
            URL u = new URL(url);
            InputStream in = u.openStream();
            InputStreamReader reader = new InputStreamReader(in);
            JsonReader jreader = new JsonReader(reader);
            JsonParser parser = new JsonParser();
            JsonObject moodleResponse = (JsonObject) parser.parse(jreader);
            JsonElement token = moodleResponse.get("token");
            if (token == null) {
                String error = moodleResponse.get("error").getAsString();
                throw new MoodleRequestException(error);
            } else {
                result = token.getAsString();
            }
            jreader.close();
            reader.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    public Document request(String function, HashMap<String, String> parameters)
            throws MoodleRequestException {
        String token;
        if (parameters.containsKey("token")) {
            token = parameters.get("token");
            parameters.remove("token");
        } else {
            token = this.moodleToken;
        }
        String param_list = "";
        for (String k : parameters.keySet()) {
            param_list += "&" + k + "=" + parameters.get(k);
        }
        String url = this.moodleBase + "/webservice/rest/server.php?wstoken="
                + token + "&wsfunction=" + function + param_list;

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        InputStream is = null;
        try {
            URL request = new URL(url);
            builder = documentBuilderFactory.newDocumentBuilder();
            is = request.openStream();
            doc = builder.parse(is);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MoodleRequestException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return doc;
    }
}
