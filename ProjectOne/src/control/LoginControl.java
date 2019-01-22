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

		// ��ҹ�ư �̺�Ʈ �޼ҵ�
		btnCancel.setOnAction(event -> {
			try {
				Pane root = new FXMLLoader().load(getClass().getResource("/view/main.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setTitle("KHK �������̸�Ʈ ������ �������α׷�");
				Stage oldStage = (Stage) btnCancel.getScene().getWindow();
				oldStage.close();
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});// ��ҹ�ư �̺�Ʈ �޼ҵ� ����

		// ������ ��� ��ư �̺�Ʈ
		btnManagerJoin.setOnAction(event -> handlerBtnManagerJoinAction(event));
		txtPassword.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				login();
			}
		});
		// �α��� ��ư �̺�Ʈ
		btnLogin.setOnAction(event -> handlerLoginAction(event));
	}

	// �α��� ��ư �̺�Ʈ �޼ҵ�
	public void handlerLoginAction(ActionEvent event) {
		login();
	}
	//������ ���â �̵� �޼ҵ�
	public void handlerBtnManagerJoinAction(ActionEvent event) {

		LoginDAO login = new LoginDAO();

		boolean sucess = false;

		// ������ ���â �̵�
		try {
			Pane root = FXMLLoader.load(getClass().getResource("/view/managerJoin.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("������ ���");
			Stage oldStage = (Stage) btnManagerJoin.getScene().getWindow();
			oldStage.close();
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
		}
	}// ������ ��Ϲ�ư �޼ҵ� ����

	//�α��� �޼ҵ�
	public void login() {
		LoginDAO login = new LoginDAO();

		boolean sucess = false;

		try {
			sucess = login.getLogin(txtId.getText(), txtPassword.getText());

			if (txtId.getText().equals("") || txtPassword.getText().equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�α���");
				alert.setHeaderText("�α��� ����");
				alert.setContentText("���̵�� ��й�ȣ�� Ȯ���ϼ���1");
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
				primaryStage.setTitle("�����޴� ����");
				Stage oldStage = (Stage) btnLogin.getScene().getWindow();
				oldStage.close();
				primaryStage.show();

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�α���");
			alert.setHeaderText("�α��� ����");
			alert.setContentText("���̵�� ��й�ȣ�� ��� �Ǿ����� Ȯ���ϼ���");
			alert.show();
			
			// �α��� ���н� textFeild Ŭ���� �� ���̵� ��Ŀ��
			txtId.clear();
			txtPassword.clear();
			txtId.requestFocus();
		}
	}
}
