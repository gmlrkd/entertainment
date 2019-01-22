package control;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainControl implements Initializable {
	@FXML
	private Button btnManager; // �����ڹ�ư
	@FXML
	private Button btnExit; // �����ư
	@FXML
	private Button btnApplicantView;
	@FXML
	private ImageView imageView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ������ ��ư �̺�Ʈ �޼ҵ�
		btnManager.setOnAction(event -> handlerBtnManagerAction(event));
		// ������ ���� ��ư �̺�Ʈ
		btnApplicantView.setOnAction(event -> handlerBtnApplicantViewAction(event));
		// �̹��� �̺�Ʈ
		imageView.setOnMouseClicked(event -> {
			try {
				Desktop.getDesktop().browse(new URI("https://www.youtube.com/channel/UCfB4aObQAVlTi_CG-vWQfkg"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

		});

		// �����ư �̺�Ʈ
		btnExit.setOnAction(event -> {
			Platform.exit();// ���α׷� ����
		});

		// ���콺 �̺�Ʈ
		btnManager.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
		btnApplicantView.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
		btnExit.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
	}// ���� �޼ҵ�

	// ������ ���� ��ư �޼ҵ�
	public void handlerBtnApplicantViewAction(ActionEvent event) {
		try {
			FXMLLoader root = new FXMLLoader(getClass().getResource("/view/applicantView.fxml"));
			Parent applicantView = (Parent) root.load();
			Scene scene = new Scene(applicantView);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			Stage old = (Stage) btnApplicantView.getScene().getWindow();
			old.close();
			primaryStage.setTitle("�����ں���");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnManagerAction(ActionEvent event) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("������ �α���");
			primaryStage.setScene(scene);
			Stage oldStage = (Stage) btnManager.getScene().getWindow();
			oldStage.close();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// ������ ��ư �̺�Ʈ �޼ҵ� ����

}
