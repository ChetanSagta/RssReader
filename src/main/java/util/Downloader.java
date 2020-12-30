package util;

import Entity.FirstScreenEntity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//to download content based on url or urls
public class Downloader {

    public String getData(String urlString) throws IOException {

        String responseString = "";
        URL obj = new URL(urlString);
//        System.out.println(obj);
        HttpURLConnection conn = (HttpURLConnection)obj.openConnection();
//        System.out.println("Connection Opening");
        conn.setRequestMethod("GET");
        int response = conn.getResponseCode();
//        System.out.println(response);

        if(response == 200){
            InputStream ip = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(ip));
            String tmpString;
            while((tmpString= br.readLine())!=null){
               responseString += tmpString;
            }
        }
        conn.disconnect();
        return responseString;
    }

    public void Reload(FirstScreenEntity entity) throws IOException, ParserConfigurationException, SAXException, SQLException, ClassNotFoundException {

        String rssPageContent = getData(entity.getLink());
        RssXmlParser rssXmlParser = new RssXmlParser();
        List<Map<String,String>> data = rssXmlParser.parseString(rssPageContent);

        DBClient client = new DBClient();
        client.insertIntoDatabase(data,entity.getLink());


    }

}
