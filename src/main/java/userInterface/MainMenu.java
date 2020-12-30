package userInterface;

import Entity.MenuEntity;
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

public final class MainMenu {
    private static int count = 1;
    private static List<MenuEntity> menuItems;
    private static int menuValue = 1;
    private  static Map<String,String> fileContent;

    public static void createMenu() throws IOException {
        YmlParser ymlParser = new YmlParser();
       fileContent = (Map<String, String>) ymlParser.getFileContent("url.yml");

        menuItems = new ArrayList<>();
        Iterator<String> it = fileContent.keySet().iterator();

        while (it.hasNext()) {
            MenuEntity tempEntity = new MenuEntity();
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
        for (MenuEntity tmpEntity : menuItems) {
            if (tmpEntity.getsNo() == menuValue) {
                tmpEntity.setForegroundColor(Color.RED);
                tmpEntity.setSelected(true);
            }
            else {
                tmpEntity.setForegroundColor(Color.CYAN);
                tmpEntity.setSelected(false);
            }
            System.out.println(tmpEntity.getBackgroundColor()+tmpEntity.getForegroundColor()+tmpEntity.getsNo()+" "+tmpEntity.getLength()+tmpEntity.getDate()+tmpEntity.getTitle());
        }
    }

    public static void reloadURL() throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException {

      for(MenuEntity entity: menuItems){
          if(entity.isSelected()){
              Downloader tmpDownloader = new Downloader();
              tmpDownloader.Reload(entity);
          }
      }

    }

    public static void reloadAllURL() throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException {
        for(MenuEntity entity: menuItems){
             Downloader tmpDownloader = new Downloader();
             tmpDownloader.Reload(entity);
        }
    }

    public static void getList() throws SQLException, ClassNotFoundException {

        clearScreen();
        List<Map<String,String>> results = new ArrayList<>();
        for(MenuEntity entity: menuItems){

            if(entity.isSelected()){
                DBClient client = new DBClient();
                results = client.getResults(entity.getLink());
            }

            SecondScreen.show_UI(results);
        }
    }

    private static void clearScreen() {
        System.out.print("\033\143");
    }

    public static int getMenuValue() {
        return menuValue;
    }

    public static void setMenuValue(int menuValue) {
        MainMenu.menuValue = menuValue;
    }
}
