package userInterface;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public final class UserInputs {

    public void getInput() throws IOException, SAXException, ParserConfigurationException, SQLException, ClassNotFoundException {

        Scanner scan = new Scanner(System.in);
        char keyboardValue = scan.next().charAt(0);
        switch (keyboardValue) {
            case 'J':
            case 'j':
                MainMenu.setMenuValue(MainMenu.getMenuValue() + 1);
                break;
            case 'K':
            case 'k':
                MainMenu.setMenuValue(MainMenu.getMenuValue() - 1);
                break;
            case 'L':
            case 'l':
                MainMenu.getList();
                break;
            case 'H':
            case 'h':
                MainMenu.updateMenu();
                break;
            case 'R': MainMenu.reloadAllURL();
                break;
            case 'r': MainMenu.reloadURL();
                break;
            case 'q':
            case 'Q': System.exit(0);
            default:
        }
        MainMenu.updateMenu();
    }

}