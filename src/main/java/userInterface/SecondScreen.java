package userInterface;

import Entity.SecondScreenEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static main.ScreenStatus.clearScreen;

public class SecondScreen {

    static List<SecondScreenEntity> secondScreenEntities = new ArrayList<>();
    public static void createMenu(List<Map<String,String>> results){

        for(Map<String,String> temp : results){

            SecondScreenEntity tmpEntity = new SecondScreenEntity();
            tmpEntity.setTitle(temp.get("Title"));
            tmpEntity.setDescription(temp.get("description"));
            tmpEntity.setPublishDate(temp.get("pubDate"));

            secondScreenEntities.add(tmpEntity);
        }

        updateMenu();

    }

    public static void updateMenu(){

        clearScreen();
        for(SecondScreenEntity sse : secondScreenEntities){
            System.out.println(sse.getsNo() + " " + sse.getPublishDate() + " " +sse.getTitle());
        }

    }

}
