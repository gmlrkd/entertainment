package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginControl implements Initializable {
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnManagerJoin;
	@FXML
	private Button btnLogin;
	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtPassword;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 취소버튼 이벤트 메소드
		btnCancel.setOnAction(event -> {
			try {
				Pane root = new FXMLLoader().load(getClass().getResource("/view/main.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setTitle("KHK 엔터테이먼트 지원자 관리프로그램");
				Stage oldStage = (Stage) btnCancel.getScene().getWindow();
				oldStage.close();
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});// 취소버튼 이벤트 메소드 종료

		// 관리자 등록 버튼 이벤트
		btnManagerJoin.setOnAction(event -> handlerBtnManagerJoinAction(event));
		txtPassword.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				login();
			}
		});
		// 로그인 버튼 이벤트
		btnLogin.setOnAction(event -> handlerLoginAction(event));
	}

	// 로그인 버튼 이벤트 메소드
	public void handlerLoginAction(ActionEvent event) {
		login();
	}
	//관리자 등록창 이동 메소드
	public void handlerBtnManagerJoinAction(ActionEvent event) {

		LoginDAO login = new LoginDAO();

		boolean sucess = false;

		// 관리자 등록창 이동
		try {
			Pane root = FXMLLoader.load(getClass().getResource("/view/managerJoin.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("관리자 등록");
			Stage oldStage = (Stage) btnManagerJoin.getScene().getWindow();
			oldStage.close();
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
		}
	}// 관리자 등록버튼 메소드 종료

	//로그인 메소드
	public void login() {
		LoginDAO login = new LoginDAO();

		boolean sucess = false;

		try {
			sucess = login.getLogin(txtId.getText(), txtPassword.getText());

			if (txtId.getText().equals("") || txtPassword.getText().equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("로그인");
				alert.setHeaderText("로그인 실패");
				alert.setContentText("아이디와 비밀번호를 확인하세요1");
				alert.showAndWait();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (sucess) {
			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/managerChoice.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.setTitle("관리메뉴 선택");
				Stage oldStage = (Stage) btnLogin.getScene().getWindow();
				oldStage.close();
				primaryStage.show();

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인");
			alert.setHeaderText("로그인 실패");
			alert.setContentText("아이디와 비밀번호가 등록 되었는지 확인하세요");
			alert.show();
			
			// 로그인 실패시 textFeild 클리어 후 아이디에 포커스
			txtId.clear();
			txtPassword.clear();
			txtId.requestFocus();
		}
	}
}
