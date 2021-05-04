package Standard.utils.tools;

import Standard.inspect.Inspecionador;
import Standard.utils.others.SeleniumUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyController {

    Robot robot = new Robot();

    public KeyController() throws AWTException {
    }

    public void enviarNumeros(int n1) {
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(n1));
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(n1));
        robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(n1));
    }

    public void moverMouse(String xpath) throws AWTException {
        Robot robot = new Robot();
        WebElement elemento = SeleniumUtils.getWebElement(xpath);
        Point coordinates = elemento.getLocation();
        robot.mouseMove(coordinates.getX() - 10, coordinates.getY());
        System.out.println(coordinates.getX() - 10 + "\n " + coordinates.getY());
    }


}
