import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComboBox;

import org.junit.Test;

public class TestComboPopup {

	@Test
	public void test() throws AWTException, InvocationTargetException, InterruptedException {
		Main window = new Main();
		waitForSwing();

		JComboBox<String> combo = window.getCombo();
		Dimension comboSizes = combo.getSize();
		Rectangle comboBounds = combo.getBounds();

		Point windowLocation = window.getLocation();
		Point comboLocation = combo.getLocation();

		Rectangle realBounds = new Rectangle((int) comboBounds.getX(), (int) comboBounds.getY(),
				(int) comboSizes.getWidth(), (int) comboSizes.getHeight());

		assertEquals(realBounds, comboBounds);


		int posX = (int) (windowLocation.getX() + comboLocation.getX() + realBounds.getWidth()) -1;
		int posY = (int) (windowLocation.getY() + comboLocation.getY() + realBounds.getHeight()) - 1;

		System.out.println("Window location: " + windowLocation);
		System.out.println("Combo location: " + comboLocation);
		System.out.println("Real Bounds: " + realBounds);
		System.out.println("Clicking at position: " + posX + " " + posY);

		// Click into the corner...
		Robot bot = new Robot();
		bot.mouseMove(posX, posY);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		System.err.println("Mouse pressed.");
		waitForSwing();

		// While we hold down the mouse, the popup should be open
		assertTrue(combo.isPopupVisible());


		// If we release the mouse, it should still open...
		bot.mouseMove(posX, posY); // maybe the mouse got moved while the test was running
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
		System.err.println("Mouse released.");
		waitForSwing();
		assertTrue(combo.isPopupVisible());

	}

	private void waitForSwing() throws InvocationTargetException, InterruptedException{
		Thread.sleep(1000);

	}

}
