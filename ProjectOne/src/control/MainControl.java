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
	private Button btnManager; // 관리자버튼
	@FXML
	private Button btnExit; // 종료버튼
	@FXML
	private Button btnApplicantView;
	@FXML
	private ImageView imageView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 관리자 버튼 이벤트 메소드
		btnManager.setOnAction(event -> handlerBtnManagerAction(event));
		// 지원자 보기 버튼 이벤트
		btnApplicantView.setOnAction(event -> handlerBtnApplicantViewAction(event));
		// 이미지 이벤트
		imageView.setOnMouseClicked(event -> {
			try {
				Desktop.getDesktop().browse(new URI("https://www.youtube.com/channel/UCfB4aObQAVlTi_CG-vWQfkg"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

		});

		// 종료버튼 이벤트
		btnExit.setOnAction(event -> {
			Platform.exit();// 프로그램 종료
		});

		// 마우스 이벤트
		btnManager.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
		btnApplicantView.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
		btnExit.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
	}// 메인 메소드

	// 지원자 보기 버튼 메소드
	public void handlerBtnApplicantViewAction(ActionEvent event) {
		try {
			FXMLLoader root = new FXMLLoader(getClass().getResource("/view/applicantView.fxml"));
			Parent applicantView = (Parent) root.load();
			Scene scene = new Scene(applicantView);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			Stage old = (Stage) btnApplicantView.getScene().getWindow();
			old.close();
			primaryStage.setTitle("지원자보기");
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
			primaryStage.setTitle("관리자 로그인");
			primaryStage.setScene(scene);
			Stage oldStage = (Stage) btnManager.getScene().getWindow();
			oldStage.close();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// 관리자 버튼 이벤트 메소드 종료

}
