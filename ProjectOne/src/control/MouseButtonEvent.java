package control;


import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.MouseEvent;

public class MouseButtonEvent implements EventHandler<MouseEvent> {
	
	@Override
	public void handle(final MouseEvent ME) {

		Object obj = ME.getSource();

		// ��� ��ư�� �����ϴ� Ŭ����
		ButtonBase button = (ButtonBase) obj;
		button.setCursor(Cursor.HAND);

	}
}
