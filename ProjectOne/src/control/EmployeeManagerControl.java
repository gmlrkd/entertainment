package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ManagerVO;

public class EmployeeManagerControl implements Initializable {
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmployeeNumber;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtPassword;
	@FXML
	private ComboBox<String> cbJob;
	@FXML
	private Button btnEdite;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private TableView<ManagerVO> tableView;
	int no;// 인덱스

	ObservableList<ManagerVO> data = FXCollections.observableArrayList();
	ObservableList<ManagerVO> selectApplicate;
	int selectedIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cbJob.setItems(FXCollections.observableArrayList("사원", "팀장"));

		// 테이블
		TableColumn colNo = new TableColumn("NO");
		colNo.setMaxWidth(60);
		colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

		TableColumn clName = new TableColumn("이름");
		clName.setMaxWidth(60);
		clName.setCellValueFactory(new PropertyValueFactory<>("m_name"));

		TableColumn clEmployeeNumber = new TableColumn("사원번호");
		clEmployeeNumber.setMaxWidth(60);
		clEmployeeNumber.setCellValueFactory(new PropertyValueFactory<>("employeenumber"));

		TableColumn clPosition = new TableColumn("직책");
		clPosition.setMaxWidth(60);
		clPosition.setCellValueFactory(new PropertyValueFactory("position"));

		TableColumn clPhoneNumber = new TableColumn("전화번호");
		clPhoneNumber.setMaxWidth(100);
		clPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("m_phonenumber"));

		TableColumn clEmail = new TableColumn("이메일");
		clEmail.setMaxWidth(100);
		clEmail.setCellValueFactory(new PropertyValueFactory<>("m_email"));

		TableColumn clId = new TableColumn("아이디");
		clId.setMaxWidth(100);
		clId.setCellValueFactory(new PropertyValueFactory<>("m_id"));

		TableColumn clPassword = new TableColumn("비밀번호");
		clPassword.setMaxWidth(100);
		clPassword.setCellValueFactory(new PropertyValueFactory<>("m_password"));

		tableView.setItems(data);

		tableView.getColumns().addAll(colNo, clName, clPosition, clEmployeeNumber, clPhoneNumber, clEmail, clId,
				clPassword);

		// 관리자 메인 이동 버튼 이벤트
		btnCancel.setOnAction(event -> {
			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/managerChoice.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setTitle("관리자 관리 선택창");
				primaryStage.setScene(scene);
				Stage old = (Stage) btnCancel.getScene().getWindow();
				old.close();
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// 테이블 클릭 이벤트
		tableView.setOnMouseClicked(event -> handlerTableViewClickAction(event));
		// 수정버튼 이벤트
		btnEdite.setOnAction(event -> handlerBtnEditeAction(event));
		// 삭제버튼 이벤트
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));

		// 총 인원
		totalList();

	}// 메인 메소드

	// 삭제버튼 메소드
	public void handlerBtnDeleteAction(ActionEvent event) {
		EmployeeManagerDAO emDAO = new EmployeeManagerDAO();

		try {
			emDAO.DeleteEmployee(no);

			data.removeAll(data);
			totalList();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("삭제");
			alert.setHeaderText("삭제성공");
			alert.setContentText("삭제에 성공 하였습니다.");
			alert.showAndWait();

		} catch (Exception e) {

		}

	}

	// 수정버튼 메소드
	public void handlerBtnEditeAction(ActionEvent event) {

		ManagerVO mvo = new ManagerVO();
		EmployeeManagerDAO emDAO = new EmployeeManagerDAO();

		mvo = new ManagerVO(txtId.getText(), txtPassword.getText(), txtName.getText(), cbJob.getValue().toString(),
				txtEmployeeNumber.getText(), txtPhoneNumber.getText(), txtEmail.getText());

		emDAO.Edite(mvo, no);
		data.removeAll(data);
		totalList();

	}

	// 테이블 클릭 메소드
	public void handlerTableViewClickAction(MouseEvent event) {

		try {
			selectApplicate = tableView.getSelectionModel().getSelectedItems();
			selectedIndex = tableView.getSelectionModel().getFocusedIndex();

			if (event.getClickCount() == 1) {
				no = selectApplicate.get(0).getNo();
				txtName.setText(selectApplicate.get(0).getM_name());
				txtEmployeeNumber.setText(selectApplicate.get(0).getEmployeenumber());
				txtPhoneNumber.setText(selectApplicate.get(0).getM_phonenumber());
				txtEmail.setText(selectApplicate.get(0).getM_email());
				txtId.setText(selectApplicate.get(0).getM_id());
				txtPassword.setText(selectApplicate.get(0).getM_password());
				cbJob.setValue(selectApplicate.get(0).getPosition());
			}
		} catch (Exception e) {
		}
	}

	// 총 인원 메소드
	public void totalList() {
		Object[][] totoalDate;

		EmployeeManagerDAO emDAO = new EmployeeManagerDAO();
		ManagerVO mvo = new ManagerVO();
		ArrayList<String> title;
		ArrayList<ManagerVO> list;

		title = emDAO.columnName();
		int columnCount = title.size();

		list = emDAO.getEmployee();
		int rowCount = list.size();

		totoalDate = new Object[rowCount][columnCount];

		for (int i = 0; i < rowCount; i++) {
			mvo = list.get(i);
			data.add(mvo);
		}
	}
}
