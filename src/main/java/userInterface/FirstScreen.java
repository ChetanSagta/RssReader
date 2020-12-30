package userInterface;

import Entity.FirstScreenEntity;
import org.xml.sax.SAXException;
import util.Color;
import util.DBClient;
import util.Downloader;
import util.YmlParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import static main.ScreenStatus.clearScreen;

public final class FirstScreen {
    private static int count = 1;
    private static List<FirstScreenEntity> menuItems;
    private static int menuValue = 1;
    private  static Map<String,String> fileContent;

    public static void createMenu() throws IOException {
        YmlParser ymlParser = new YmlParser();
       fileContent = (Map<String, String>) ymlParser.getFileContent("url.yml");

        menuItems = new ArrayList<>();
        Iterator<String> it = fileContent.keySet().iterator();

        while (it.hasNext()) {
            FirstScreenEntity tempEntity = new FirstScreenEntity();
            tempEntity.setsNo(count);
            tempEntity.setDate("");
            tempEntity.setTitle(it.next());
            tempEntity.setBackgroundColor(Color.BG_BLACK);
            tempEntity.setForegroundColor(Color.CYAN);
            tempEntity.setLink(fileContent.get(tempEntity.getTitle()));
            menuItems.add(tempEntity);
            ++count;
        }
        updateMenu();
    }

    public static void updateMenu() {

        clearScreen();
        if(menuValue > fileContent.size())   --menuValue;
        else if(menuValue < 1) ++menuValue;
        System.out.println(Color.BG_BLACK+Color.BLUE+"------------Main Menu--------------");
        for (FirstScreenEntity tmpEntity : menuItems) {
            if (tmpEntity.getsNo() == menuValue) {
                tmpEntity.setForegroundColor(Color.RED);
                tmpEntity.setSelected(true);
            }
            else {
                tmpEntity.setForegroundColor(Color.CYAN);
                tmpEntity.setSelected(false);
            }
            System.out.println(tmpEntity.getBackgroundColor()+tmpEntity.getForegroundColor()+tmpEntity.getsNo()+" "+tmpEntity.getDate()+tmpEntity.getTitle());
        }
    }

    public static void reloadURL() throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException {

      for(FirstScreenEntity entity: menuItems){
          if(entity.isSelected()){
              Downloader tmpDownloader = new Downloader();
              tmpDownloader.Reload(entity);
          }
      }
    }

    public static void reloadAllURL() throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException {
        for(FirstScreenEntity entity: menuItems){
             Downloader tmpDownloader = new Downloader();
             tmpDownloader.Reload(entity);
        }
    }

    public static void getList() throws SQLException, ClassNotFoundException {

        clearScreen();
        List<Map<String,String>> results = new ArrayList<>();
        for(FirstScreenEntity entity: menuItems){

            if(entity.isSelected()){
                DBClient client = new DBClient();
                results = client.getResults(entity.getLink());
            }

            SecondScreen.createMenu(results);
        }
    }


    public static int getMenuValue() {
        return menuValue;
    }

    public static void setMenuValue(int menuValue) {
        FirstScreen.menuValue = menuValue;
    }
}
