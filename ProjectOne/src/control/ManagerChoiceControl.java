package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ManagerChoiceControl implements Initializable {
	@FXML
	private Button btnApplicant;
	@FXML
	private Button btnEmployee;
	@FXML
	private Button btnCancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//��ư �̺�Ʈ
		btnCancel.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
		btnApplicant.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
		btnEmployee.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());

		// �������� ���ư��� ��ư �̺�Ʈ
		btnCancel.setOnAction(event -> {
			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				Stage oldStage = (Stage) btnCancel.getScene().getWindow();
				primaryStage.setTitle("KHK �������̸�Ʈ ������ �������α׷�");
				oldStage.close();
				primaryStage.show();
			} catch (Exception e) {
			}
		});
		//������ ������ư �̺�Ʈ
		btnApplicant.setOnAction(event -> {
			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/applicantManager.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				Stage oldStage = (Stage) btnApplicant.getScene().getWindow();
				oldStage.close();
				primaryStage.setTitle("������ ���� ���α׷�");
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				System.out.println(e);
			}
		});
		btnEmployee.setOnAction(event -> {
			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/employeeManager.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				Stage oldStage = (Stage) btnApplicant.getScene().getWindow();
				oldStage.close();
				primaryStage.setTitle("���� ���� ���α׷�");
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				System.out.println(e);
			}
		});
	}

}
