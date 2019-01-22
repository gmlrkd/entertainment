package control;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.ApplicantVO;

public class ApplicantManagerControl implements Initializable {

	@FXML
	private TableView<ApplicantVO> tableView = new TableView<>();
	@FXML
	private ComboBox<String> cbList;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAge;
	@FXML
	private TextField txtHeight;
	@FXML
	private TextField txtWeight;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextArea txtSpecialnote;
	@FXML
	private TextField txtEmployeeNumber;
	@FXML
	private Button btnTotal;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnEdite;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnReset;
	@FXML
	private TextField txtNo;
	// �˻�
	@FXML
	private TextField txtSearchName;
	@FXML
	private ComboBox<String> cbSearchJob;
	@FXML
	private Button btnSearchName;
	@FXML
	private Button btnSearchJob;
	@FXML
	private Button btnSearchEmployeeNumber;
	@FXML
	private TextField txtSearchEmployeeNumber;

	int count;// �ο� �� ��������

	int no; // �ε�����ȣ

	// �̹��� ���� ÷�� �ʵ�
	@FXML
	private Button btnFileSave;
	@FXML
	private TextField txtFileName;
	@FXML
	private TextField txtFileAddr;

	private Stage primaryStage;
	String selectFileName = "";
	String localUrl = "";
	Image localImage;
	File selectedFile = null;

	@FXML
	private ImageView imageView;
	// �̹��� ó��
	// �̹��� ������ ������ �Ű������� ���� ��ü ����
	private File dirSave = new File("C:/images");
	// �̹��� �ҷ��� ������ ������ ���� ��ü ����

	// ���̺� ó��
	ObservableList<ApplicantVO> data = FXCollections.observableArrayList();
	ObservableList<ApplicantVO> selectApplicate;
	int selectedIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// ��ư �̺�Ʈ
		btnCancel.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
		txtFileAddr.setEditable(false);
		// �޺��ڽ� ����
		cbList.setItems(FXCollections.observableArrayList("DJ", "��", "�����ռ�"));
		cbSearchJob.setItems(FXCollections.observableArrayList("DJ", "��", "�����ռ�"));
		// ������ȣ ���ڸ� �Է�
		DecimalFormat format = new DecimalFormat("######");
		txtEmployeeNumber.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 7) {
				return null;
			} else {
				return event;
			}
		}));

		// ���̺� Į�� ����
		TableColumn colNo = new TableColumn("NO");
		colNo.setMaxWidth(60);
		colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

		TableColumn colJob = new TableColumn("�����о�");
		colJob.setMaxWidth(60);
		colJob.setCellValueFactory(new PropertyValueFactory<>("job"));

		TableColumn colName = new TableColumn("�̸�");
		colName.setMaxWidth(60);
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn colAge = new TableColumn("�������");
		colAge.setMaxWidth(120);
		colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn colHeight = new TableColumn("Ű");
		colHeight.setMaxWidth(60);
		colHeight.setCellValueFactory(new PropertyValueFactory<>("height"));

		TableColumn colWeight = new TableColumn("������");
		colWeight.setMaxWidth(60);
		colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));

		TableColumn colPhoneNumber = new TableColumn("��ȭ��ȣ");
		colPhoneNumber.setMaxWidth(100);
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		TableColumn colEmail = new TableColumn("�̸���");
		colEmail.setMaxWidth(120);
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn colEmployeeNumber = new TableColumn("�����ȣ");
		colEmployeeNumber.setMaxWidth(60);
		colEmployeeNumber.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));

		TableColumn colImage = new TableColumn("�̹���");
		colImage.setMaxWidth(60);
		colImage.setCellValueFactory(new PropertyValueFactory<>("image"));

		TableColumn colDate = new TableColumn("�����");
		colDate.setMaxWidth(120);
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

		tableView.setItems(data);

		// ���̺� �信 �÷� ���
		tableView.getColumns().addAll(colNo, colJob, colName, colAge, colHeight, colWeight, colPhoneNumber, colEmail,
				colEmployeeNumber, colImage, colDate);

		btnCancel.setOnAction(event -> {
			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/managerChoice.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				Stage oldStage = (Stage) btnCancel.getScene().getWindow();
				oldStage.close();
				primaryStage.setTitle("������ �����޴� ����");
				primaryStage.show();
			} catch (Exception e) {
				System.out.println(e);
			}

		});// ��ҹ�ư �̺�Ʈ

		// ��Ϲ�ư �̺�Ʈ
		btnOk.setOnAction(event -> handlerBtnOkAction(event));

		// �̹��� ��� ��ư �̺�Ʈ
		btnFileSave.setOnAction(event -> handlerBtnFileSaveAction(event));
		// ��� �˻� �̺�Ʈ
		btnSearchEmployeeNumber.setOnAction(event -> handlerBtnSearchEmployeeNumber(event));

		// ���̺� Ŭ�� �̺�Ʈ
		tableView.setOnMousePressed(event -> handlerMousClickAction(event));

		// ������ư �̺�Ʈ
		btnEdite.setOnAction(event -> handlerBtnEditeAction(event));

		// ���� ��ư �̺�Ʈ
		btnDelete.setOnAction(event -> {

			ApplicantManagerDAO amdao = new ApplicantManagerDAO();

			try {
				data.removeAll(data);
				amdao.deleteApplicant(no);
				totalList();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		btnTotal.setOnAction(event -> {
			data.removeAll(data);
			totalList();
		});

		// �ʱ�ȭ ��ư �޼ҵ�
		btnReset.setOnAction(event -> {
			handlerBtnResetAction();
		});

		// �̸����� �˻�
		btnSearchName.setOnAction(event -> handlerBtnSearchNameAction(event));

		// �޺��ڽ� �˻�
		btnSearchJob.setOnAction(event -> handlerBtnSearchJobAction(event));

		// �� �ο� ����Ʈ
		totalList();

	}// ���� �޼ҵ�

	// ������ȣ �˻�
	public void handlerBtnSearchEmployeeNumber(ActionEvent event) {
		ApplicantVO avo = new ApplicantVO();
		ApplicantManagerDAO amdao = new ApplicantManagerDAO();
		ArrayList<ApplicantVO> list;
		String searchEmployeeNumber = "";

		list = amdao.getAppcantTotal();
		int rc = list.size();
		searchEmployeeNumber = txtSearchEmployeeNumber.getText();
		avo = amdao.searchEmployeeNumber(searchEmployeeNumber);

		try {

			if (searchEmployeeNumber != "" && searchEmployeeNumber != null) {
				data.removeAll(data);
				for (int i = 0; i < rc; i++) {
					System.out.println("?");
					if (searchEmployeeNumber.equals(searchEmployeeNumber)) {
						avo = list.get(i);
						if (avo.getEmployeeNumber().equals(searchEmployeeNumber)) {
							data.add(avo);
						} else {
						}
					}
				}
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�˻�");
			alert.setHeaderText("��� �˻� ����");
			alert.setContentText("�˻��� ���� �־��ּ���");
			alert.show();
		}
	}

	// �����о� �˻� ��ư �޼ҵ�
	public void handlerBtnSearchJobAction(ActionEvent event) {
		ApplicantVO avo = new ApplicantVO();
		ApplicantManagerDAO amdao = new ApplicantManagerDAO();
		ArrayList<ApplicantVO> list;
		String searchJob = "";

		list = amdao.getAppcantTotal();
		int rc = list.size();
		searchJob = cbSearchJob.getSelectionModel().getSelectedItem();
		avo = amdao.searchjob(searchJob);
		try {
			if (searchJob != "" && searchJob != null || searchJob.equals(searchJob)) {
				data.removeAll(data);
				for (int i = 0; i < rc; i++) {
					if (searchJob.equals(searchJob)) {
						avo = list.get(i);
						if (avo.getJob().equals(searchJob))
							data.add(avo);
						cbSearchJob.getSelectionModel().clearSelection();
					} else {
					}
				}
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�˻�");
			alert.setHeaderText("�����оߺ� �˻� ����");
			alert.setContentText("�˻��� ���� �־��ּ���");
			alert.show();
		}

	}

	// �̸� �˻� �޼ҵ�
	public void handlerBtnSearchNameAction(ActionEvent event) {
		ApplicantManagerDAO amDAO = new ApplicantManagerDAO();
		ApplicantVO avo = new ApplicantVO();
		String searchName = "";

		try {
			searchName = txtSearchName.getText().trim();
			avo = amDAO.searchName(searchName);
			if (!txtSearchName.equals("") && txtSearchName != null) {

				ArrayList<ApplicantVO> list;

				list = amDAO.getAppcantTotal();
				int rowCount = list.size();

				if (avo.getName().equals(searchName)) {
					data.removeAll(data);
					for (int i = 0; i < rowCount; i++) {
						txtSearchName.clear();
						avo = list.get(i);
						if (avo.getName().equals(searchName)) {
							data.add(avo);
						}

					}
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("�̸� �˻�");
					alert.setHeaderText("�̸� �˻� ����");
					alert.setContentText("�̸��� �Է� �� �ּ���");
					alert.show();
				}
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�̸� �˻�");
			alert.setHeaderText("�̸� �˻� ����");
			alert.setContentText("���� ã���� �����ϴ�.");
			alert.show();
		}
	}

	// ��ü ����Ʈ
	public void totalList() {
		Object[][] totaldate;

		ApplicantManagerDAO amdao = new ApplicantManagerDAO();
		ApplicantVO avo = new ApplicantVO();
		ArrayList<String> title;
		ArrayList<ApplicantVO> list;

		title = amdao.columnName();
		int columnCount = title.size();

		list = amdao.getAppcantTotal();
		int rowCount = list.size();

		totaldate = new Object[rowCount][columnCount];

		for (int i = 0; i < rowCount; i++) {
			avo = list.get(i);
			data.add(avo);
		}
	}

	// ��Ϲ�ư �޼ҵ�
	public void handlerBtnOkAction(ActionEvent event) {

		try {
			ApplicantVO avo = null;
			ApplicantManagerDAO amdao = null;
			File dirMake = new File(dirSave.getAbsolutePath());
			// ���� ����
			if (!dirMake.exists()) {
				dirMake.mkdir();
			}
			// �̹��� ���� ����
			if (event.getSource().equals(btnOk)) {

				avo = new ApplicantVO(cbList.getValue().toString(), txtName.getText(), txtAge.getText(),
						txtHeight.getText(), txtWeight.getText(), txtPhoneNumber.getText(), txtEmail.getText(),
						txtSpecialnote.getText(), txtEmployeeNumber.getText(), txtFileAddr.getText());
				System.out.println(1);
				amdao = new ApplicantManagerDAO();
				amdao.applicant(avo);

				data.removeAll(data);
				totalList();

			}
			if (!txtEmployeeNumber.equals(txtEmployeeNumber)) {
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�Է� ����");
				alert.setHeaderText("����� �����ȣ �� �Է�");
				alert.setContentText("����� �����ȣ�� �Է� �� ��� �ϼ���");
				alert.show();
			} else {
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}// ��� �޼ҵ� end

	// �����޼ҵ�
	public void handlerBtnEditeAction(ActionEvent event) {

		try {
			ApplicantManagerDAO amDAO = new ApplicantManagerDAO();

			ApplicantVO avo = new ApplicantVO();

			// applicantVO ���� �� �ޱ�
			avo = new ApplicantVO(cbList.getValue().toString(), txtName.getText(), txtAge.getText(),
					txtHeight.getText(), txtWeight.getText(), txtPhoneNumber.getText(), txtEmail.getText(),
					txtSpecialnote.getText(), txtEmployeeNumber.getText(), txtFileAddr.getText());

			// dao �� �Ѱ��ֱ�
			System.out.println(no);
			amDAO.applicantUpdate(avo, no);

			data.removeAll(data);
			// ��ü ����
			totalList();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// ���̺� ���콺 �̺�Ʈ �޼ҵ�
	public void handlerMousClickAction(MouseEvent event) {
		selectApplicate = tableView.getSelectionModel().getSelectedItems();
		selectedIndex = tableView.getSelectionModel().getFocusedIndex();

		try {
			if (event.getClickCount() == 1) {
				no = selectApplicate.get(0).getNo();
				cbList.setValue(selectApplicate.get(0).getJob());
				txtName.setText(selectApplicate.get(0).getName());
				txtAge.setText(selectApplicate.get(0).getAge());
				txtPhoneNumber.setText(selectApplicate.get(0).getPhoneNumber());
				txtEmail.setText(selectApplicate.get(0).getEmail());
				txtHeight.setText(selectApplicate.get(0).getHeight());
				txtWeight.setText(selectApplicate.get(0).getWeight());
				txtSpecialnote.setText(selectApplicate.get(0).getSpecialnote());
				txtEmployeeNumber.setText(selectApplicate.get(0).getEmployeeNumber());
				txtFileAddr.setText(selectApplicate.get(0).getImage());

				File file = new File(tableView.getSelectionModel().getSelectedItem().getImage());
				Image img = new Image(file.toURI().toString());
				imageView.setImage(img);

			}
		} catch (Exception e) {
		}
	}

	// �ʱ�ȭ �޼ҵ�
	public void handlerBtnResetAction() {
		txtName.clear();
		txtAge.clear();
		txtPhoneNumber.clear();
		txtEmployeeNumber.clear();
		txtEmail.clear();
		txtSpecialnote.clear();
		txtHeight.clear();
		txtWeight.clear();
		cbList.getSelectionModel().clearSelection();
		txtFileAddr.clear();
		localUrl = "/image/default.png";
		localImage = new Image(localUrl, false);
		imageView.setImage(localImage);
	}

	// �̹��� ���� ÷�ι�ư �޼ҵ�
	public void handlerBtnFileSaveAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				localUrl = selectedFile.toURL().toString();
				txtFileAddr.setText(selectedFile.getPath());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		localImage = new Image(localUrl, false);
		imageView.setImage(localImage);
		imageView.setFitWidth(374);
		imageView.setFitHeight(454);
		if (selectedFile != null) {
			selectFileName = selectedFile.getName();
		}

	}// �̹��� ���� �޼ҵ� end

}// Ŭ���� ����
