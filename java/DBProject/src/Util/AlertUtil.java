package Util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {
	public static void MsgAlert(AlertType type, String title, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.show();
	}
}
