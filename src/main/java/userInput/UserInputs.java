package userInput;

import main.ScreenStatus;
import org.xml.sax.SAXException;
import userInterface.FirstScreen;
import userInterface.SecondScreen;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public final class UserInputs {

    public void getInput(int screenNo) throws IOException, SAXException, ParserConfigurationException, SQLException,
            ClassNotFoundException {

        Scanner scan = new Scanner(System.in);
        char keyboardValue = scan.next().charAt(0);
        switch (keyboardValue) {
            case 'J':
            case 'j':
                if(screenNo == 1)
                FirstScreen.setMenuValue(FirstScreen.getMenuValue() + 1);
                break;
            case 'K':
            case 'k':
                if(screenNo == 1)
                FirstScreen.setMenuValue(FirstScreen.getMenuValue() - 1);
                break;
            case 'L':
            case 'l':
                if(screenNo == 1)
                    ScreenStatus.status = "Second";
                break;
            case 'H':
            case 'h':
                if(screenNo == 2){
                    ScreenStatus.status = "First";
                }
                break;
            case 'R':
                if(screenNo == 1)
                FirstScreen.reloadAllURL();
                break;
            case 'r':
                if(screenNo == 1)
                FirstScreen.reloadURL();
                break;
            case 'q':
            case 'Q': System.exit(0);
            default:
        }
        if(screenNo == 1)
            FirstScreen.updateMenu();
        else if(screenNo == 2)
            SecondScreen.updateMenu();
    }

}