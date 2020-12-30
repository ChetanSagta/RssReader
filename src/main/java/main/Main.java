package main;

import org.xml.sax.SAXException;
import userInterface.MainMenu;
import userInterface.UserInputs;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException,
            SQLException, ClassNotFoundException{
        MainMenu.createMenu();
        while(true) {
            new UserInputs().getInput();
        }

    }
}
