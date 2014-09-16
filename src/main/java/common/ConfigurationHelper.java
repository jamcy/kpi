package common;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ConfigurationHelper {

    private static String rootDir;
    private static String moodleBase;
    private static String resourceDir;

    private static String moodleUser = null;
    private static String moodlePassword = null;

    public static void init(String confFile, String rootDir) {
        ConfigurationHelper.rootDir = rootDir;
        File fXmlFile = new File(rootDir + "/config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
            XPath xPath = factory.newXPath();

            XPathExpression expr = xPath.compile("/vmlconfig/moodle-config/base-url");
            NodeList res = (NodeList) expr
                    .evaluate(doc, XPathConstants.NODESET);
            ConfigurationHelper.moodleBase = res.item(0).getTextContent().trim();

            expr = xPath.compile("/vmlconfig/moodle-config/user");
            res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            ConfigurationHelper.moodleUser = res.item(0).getTextContent().trim();

            expr = xPath.compile("/vmlconfig/moodle-config/password");
            res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            ConfigurationHelper.moodlePassword = res.item(0).getTextContent().trim();

            expr = xPath.compile("/vmlconfig/dir-config/resource-dir");
            res = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            ConfigurationHelper.resourceDir = res.item(0).getTextContent().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getRootDir() {
        return rootDir;
    }

    public static String getMoodleUser() {
        return moodleUser;
    }

    public static String getMoodlePassword() {
        return moodlePassword;
    }

    public static String getMoodleBase() {
        return moodleBase;
    }

    public static String getResourceDir() {
        return resourceDir;
    }

}
