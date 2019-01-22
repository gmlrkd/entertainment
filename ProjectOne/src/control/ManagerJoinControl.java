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

		// 종료버튼 이벤트
		btnCancel.setOnAction(event -> {

			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				Stage oldStage = (Stage) btnCancel.getScene().getWindow();
				oldStage.close();
				primaryStage.setScene(scene);
				primaryStage.setTitle("관리자 로그인");
				primaryStage.show();
			} catch (Exception e) {
			}
		});// 종료 메소드 마무리

		// 등록버튼 이벤트
		btnOk.setOnAction(event -> handlerBtnOkAction(event));

		// 중복확인 버튼 이벤트
		btnOverlep.setOnAction(event -> {

			String searchid = "";
			ManagerJoinDAO mjdao = new ManagerJoinDAO();
			boolean searchResult = true;

			try {
				searchid = txtId.getText().trim();
				searchResult = (boolean) mjdao.getIdOverlap(searchid);

				if (searchid.equals("")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("관리자 등록");
					alert.setHeaderText("아이디 미 입력");
					alert.setContentText("아이디를 입력 하세요");
					alert.show();
					txtId.requestFocus();

				} else if (!searchResult) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("관리자 등록");
					alert.setHeaderText(searchid + "사용 가능");
					alert.setContentText("비밀번호를 입력 해 주세요.");
					alert.show();
					txtPassword.requestFocus();
					btnOk.setDisable(false);
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("관리자 등록");
					alert.setHeaderText(searchid + "사용 불가");
					alert.setContentText("다른 아이디를 사용하세요");
					alert.show();
					txtId.requestFocus();
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		});// end

		// 직책 콤보박스
		cbList.setItems(FXCollections.observableArrayList("사원", "팀장"));
	}

	// 등록버튼 이벤트 메소드
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
				alert.setTitle("관리자 등록");
				alert.setHeaderText("관리자 등록 성공");
				alert.setContentText("등록을 성공하였습니다.");
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
			alert.setTitle("관리자 등록");
			alert.setHeaderText("관리자 등록 실패");
			alert.setContentText("정보를 모두 입력 해 주세요.");
			alert.show();
		}

	}

}
