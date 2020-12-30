package userInterface;

import java.util.List;
import java.util.Map;

public class SecondScreen {

    public static void show_UI(List<Map<String,String>> results){

        System.out.print("\033\143");

        for(Map<String,String> temp : results){
            System.out.println(temp.get("Title") + " " + temp.get("description") + " " + temp.get("link") + " " + temp.get("pubDate") );
        }

    }

}
