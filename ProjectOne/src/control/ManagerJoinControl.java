package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ManagerVO;

public class ManagerJoinControl implements Initializable {
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtEmployeeNumber;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtPassword;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnOverlep;

	@FXML
	private ComboBox<String> cbList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnOk.setDisable(true);

		// �����ư �̺�Ʈ
		btnCancel.setOnAction(event -> {

			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				Stage oldStage = (Stage) btnCancel.getScene().getWindow();
				oldStage.close();
				primaryStage.setScene(scene);
				primaryStage.setTitle("������ �α���");
				primaryStage.show();
			} catch (Exception e) {
			}
		});// ���� �޼ҵ� ������

		// ��Ϲ�ư �̺�Ʈ
		btnOk.setOnAction(event -> handlerBtnOkAction(event));

		// �ߺ�Ȯ�� ��ư �̺�Ʈ
		btnOverlep.setOnAction(event -> {

			String searchid = "";
			ManagerJoinDAO mjdao = new ManagerJoinDAO();
			boolean searchResult = true;

			try {
				searchid = txtId.getText().trim();
				searchResult = (boolean) mjdao.getIdOverlap(searchid);

				if (searchid.equals("")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("������ ���");
					alert.setHeaderText("���̵� �� �Է�");
					alert.setContentText("���̵� �Է� �ϼ���");
					alert.show();
					txtId.requestFocus();

				} else if (!searchResult) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("������ ���");
					alert.setHeaderText(searchid + "��� ����");
					alert.setContentText("��й�ȣ�� �Է� �� �ּ���.");
					alert.show();
					txtPassword.requestFocus();
					btnOk.setDisable(false);
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("������ ���");
					alert.setHeaderText(searchid + "��� �Ұ�");
					alert.setContentText("�ٸ� ���̵� ����ϼ���");
					alert.show();
					txtId.requestFocus();
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		});// end

		// ��å �޺��ڽ�
		cbList.setItems(FXCollections.observableArrayList("���", "����"));
	}

	// ��Ϲ�ư �̺�Ʈ �޼ҵ�
	public void handlerBtnOkAction(ActionEvent event) {
		ManagerVO mvo = null;
		ManagerJoinDAO mjdao = null;

		try {

			if (event.getSource().equals(btnOk)) {
				mvo = new ManagerVO(txtId.getText(), txtPassword.getText(), txtName.getText(),
						cbList.getValue().toString(), txtEmployeeNumber.getText(), txtPhoneNumber.getText(),
						txtEmail.getText());

				mjdao = new ManagerJoinDAO();
				mjdao.getManagerList(mvo);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("������ ���");
				alert.setHeaderText("������ ��� ����");
				alert.setContentText("����� �����Ͽ����ϴ�.");
				alert.show();
				
				txtId.clear();
				txtEmployeeNumber.clear();
				txtPassword.clear();
				txtName.clear();
				txtPhoneNumber.clear();
				cbList.getSelectionModel().getSelectedItem();

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("������ ���");
			alert.setHeaderText("������ ��� ����");
			alert.setContentText("������ ��� �Է� �� �ּ���.");
			alert.show();
		}

	}

}
