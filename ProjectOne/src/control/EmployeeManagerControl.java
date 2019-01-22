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
	int no;// �ε���

	ObservableList<ManagerVO> data = FXCollections.observableArrayList();
	ObservableList<ManagerVO> selectApplicate;
	int selectedIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cbJob.setItems(FXCollections.observableArrayList("���", "����"));

		// ���̺�
		TableColumn colNo = new TableColumn("NO");
		colNo.setMaxWidth(60);
		colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

		TableColumn clName = new TableColumn("�̸�");
		clName.setMaxWidth(60);
		clName.setCellValueFactory(new PropertyValueFactory<>("m_name"));

		TableColumn clEmployeeNumber = new TableColumn("�����ȣ");
		clEmployeeNumber.setMaxWidth(60);
		clEmployeeNumber.setCellValueFactory(new PropertyValueFactory<>("employeenumber"));

		TableColumn clPosition = new TableColumn("��å");
		clPosition.setMaxWidth(60);
		clPosition.setCellValueFactory(new PropertyValueFactory("position"));

		TableColumn clPhoneNumber = new TableColumn("��ȭ��ȣ");
		clPhoneNumber.setMaxWidth(100);
		clPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("m_phonenumber"));

		TableColumn clEmail = new TableColumn("�̸���");
		clEmail.setMaxWidth(100);
		clEmail.setCellValueFactory(new PropertyValueFactory<>("m_email"));

		TableColumn clId = new TableColumn("���̵�");
		clId.setMaxWidth(100);
		clId.setCellValueFactory(new PropertyValueFactory<>("m_id"));

		TableColumn clPassword = new TableColumn("��й�ȣ");
		clPassword.setMaxWidth(100);
		clPassword.setCellValueFactory(new PropertyValueFactory<>("m_password"));

		tableView.setItems(data);

		tableView.getColumns().addAll(colNo, clName, clPosition, clEmployeeNumber, clPhoneNumber, clEmail, clId,
				clPassword);

		// ������ ���� �̵� ��ư �̺�Ʈ
		btnCancel.setOnAction(event -> {
			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/managerChoice.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setTitle("������ ���� ����â");
				primaryStage.setScene(scene);
				Stage old = (Stage) btnCancel.getScene().getWindow();
				old.close();
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// ���̺� Ŭ�� �̺�Ʈ
		tableView.setOnMouseClicked(event -> handlerTableViewClickAction(event));
		// ������ư �̺�Ʈ
		btnEdite.setOnAction(event -> handlerBtnEditeAction(event));
		// ������ư �̺�Ʈ
		btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));

		// �� �ο�
		totalList();

	}// ���� �޼ҵ�

	// ������ư �޼ҵ�
	public void handlerBtnDeleteAction(ActionEvent event) {
		EmployeeManagerDAO emDAO = new EmployeeManagerDAO();

		try {
			emDAO.DeleteEmployee(no);

			data.removeAll(data);
			totalList();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("����");
			alert.setHeaderText("��������");
			alert.setContentText("������ ���� �Ͽ����ϴ�.");
			alert.showAndWait();

		} catch (Exception e) {

		}

	}

	// ������ư �޼ҵ�
	public void handlerBtnEditeAction(ActionEvent event) {

		ManagerVO mvo = new ManagerVO();
		EmployeeManagerDAO emDAO = new EmployeeManagerDAO();

		mvo = new ManagerVO(txtId.getText(), txtPassword.getText(), txtName.getText(), cbJob.getValue().toString(),
				txtEmployeeNumber.getText(), txtPhoneNumber.getText(), txtEmail.getText());

		emDAO.Edite(mvo, no);
		data.removeAll(data);
		totalList();

	}

	// ���̺� Ŭ�� �޼ҵ�
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

	// �� �ο� �޼ҵ�
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
