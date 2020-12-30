package main;

import org.xml.sax.SAXException;
import userInput.UserInputs;
import userInterface.FirstScreen;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException,
            SQLException, ClassNotFoundException{
        FirstScreen.createMenu();
        UserInputs inputs = new UserInputs();
        while(true) {
            if(ScreenStatus.status.equals("First")){
                inputs.getInput(1);
            }
            else if(ScreenStatus.status.equals("Second")) {
                inputs.getInput(2);
            }
        }
    }
}
