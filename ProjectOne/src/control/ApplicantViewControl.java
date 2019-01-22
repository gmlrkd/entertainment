package control;

import java.io.File;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ApplicantVO;

public class ApplicantViewControl implements Initializable {
	@FXML
	private Button btnCancel;
	@FXML
	private TableView<ApplicantVO> tableView;
	@FXML
	private Tab tab1;
	@FXML
	private Tab tab2;
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
	private TextArea txtSpecialNote;
	@FXML
	private ImageView imageView;
	Image localImage;
	// �˻�
	@FXML
	private TextField txtSearchName;
	@FXML
	private Button btnSearchName;
	@FXML
	private ComboBox<String> cbList;
	@FXML
	private Button btnSearchList;
	@FXML
	private HBox hBox;
	@FXML
	private Button btnSaerchDate;
	@FXML
	private Button btnTotal;
	// ��¥
	@FXML
	private DatePicker dpStartDay;
	@FXML
	private DatePicker dpEndDay;

	// ��Ʈ
	@FXML
	private PieChart pieChart;
	@FXML
	private BarChart<String, Number> barChart;

	int no; // �ε��� ��ȣ �޾ƿ���
	int count = 0;

	ObservableList<ApplicantVO> data = FXCollections.observableArrayList();
	ObservableList<ApplicantVO> selectApplicate;
	int selectedIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		hBox.setOnMouseClicked(event -> {

		});
		// �޺��ڽ� ����
		cbList.setItems(FXCollections.observableArrayList("��", "DJ", "�����ռ�"));
		// ������Ʈ ����
		PieChart();
		// �� ��Ʈ
		getBarChart();

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

		// ���̺� applicantVO ���� ���
		tableView.setItems(data);

		// ���̺� �信 �÷� ���
		tableView.getColumns().addAll(colNo, colJob, colName, colAge, colHeight, colWeight, colPhoneNumber, colEmail,
				colEmployeeNumber, colImage, colDate);

		// �������� ���ư��� ��ư �̺�Ʈ
		btnCancel.setOnAction(event -> {
			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.setTitle("KHK �������̸�Ʈ ������ �������α׷�");
				Stage old = (Stage) btnCancel.getScene().getWindow();
				old.close();
				primaryStage.show();

			} catch (Exception e) {
			}
		});
		String localUrl = "/image/a100.png";
		localImage = new Image(localUrl, false);
		imageView.setImage(localImage);

		// �̸����� �˻�
		btnSearchName.setOnAction(event -> handlerBtnSearchNameAction(event));
		// �޺��ڽ� �˻�
		btnSearchList.setOnAction(event -> handlerBtnSearchListAction(event));
		// ���� �˻� �̺�Ʈ
		btnSaerchDate.setOnAction(event -> handlerBtnSearchDateAction(event));
		// ���̺�� ���콺 Ŭ�� �̺�Ʈ
		tableView.setOnMouseClicked(event -> handlerTableViewMouseClickAction(event));
		// ��ü���� ��ư �̺�Ʈ
		btnTotal.setOnAction(event -> handlerBtnTotalAction(event));

		totalList();

	}
	// ��ü���� 
	public void handlerBtnTotalAction(ActionEvent event) {
		data.removeAll(data);
		totalList();
	}

	// ����Ʈ �޼ҵ�
	public void getBarChart() {
		ApplicantViewDAO aDAO = new ApplicantViewDAO();
		XYChart.Series model = new XYChart.Series();
		model.setName("�� �ο���");
		model.getData().add(new XYChart.Data("��", aDAO.pieChartModel()));
		XYChart.Series DJ = new XYChart.Series();
		DJ.setName("DJ �ο���");
		DJ.getData().add(new XYChart.Data("DJ", aDAO.pieChartDJ()));
		XYChart.Series performance = new XYChart.Series();
		performance.setName(" �����ռ� �ο���");
		performance.getData().add(new XYChart.Data("�����ռ�", aDAO.pieChartPerformance()));

		barChart.getData().addAll(model, performance, DJ);

	}
	// ������Ʈ �޼ҵ�
	public void PieChart() {
		ApplicantViewDAO amDAO = new ApplicantViewDAO();
		
		//��Ʈ�� �� �ֱ�
		pieChart.setData(FXCollections.observableArrayList(new PieChart.Data("��",  amDAO.pieChartModel()),
				new PieChart.Data("�����ռ�",  amDAO.pieChartPerformance()), new PieChart.Data("DJ", amDAO.pieChartDJ())));
	}

	// ���̺� Ŭ�� �̺�Ʈ �޼ҵ�
	public void handlerTableViewMouseClickAction(MouseEvent event) {
		selectApplicate = tableView.getSelectionModel().getSelectedItems();
		selectedIndex = tableView.getSelectionModel().getFocusedIndex();
		try {
			if (event.getClickCount() == 1) {
				no = selectApplicate.get(0).getNo();
				txtName.setText(selectApplicate.get(0).getName());
				txtAge.setText(selectApplicate.get(0).getAge());
				txtWeight.setText(selectApplicate.get(0).getWeight());
				txtHeight.setText(selectApplicate.get(0).getHeight());
				txtPhoneNumber.setText(selectApplicate.get(0).getPhoneNumber());
				txtEmail.setText(selectApplicate.get(0).getEmail());
				txtSpecialNote.setText(selectApplicate.get(0).getSpecialnote());

				File file = new File(tableView.getSelectionModel().getSelectedItem().getImage());
				Image img = new Image(file.toURI().toString());
				imageView.setImage(img);
			}
		} catch (Exception e) {
		}
	}

	// �����о� �˻� ��ư �޼ҵ�
	public void handlerBtnSearchListAction(ActionEvent event) {
		ApplicantVO avo = new ApplicantVO();
		ApplicantManagerDAO amdao = new ApplicantManagerDAO();
		ArrayList<ApplicantVO> list;
		String searchJob = "";

		list = amdao.getAppcantTotal();
		int rc = list.size();
		searchJob = cbList.getSelectionModel().getSelectedItem();
		avo = amdao.searchjob(searchJob);
		try {
			if (searchJob != "" && searchJob != null || searchJob.equals(searchJob)) {
				data.removeAll(data);
				for (int i = 0; i < rc; i++) {
					if (searchJob.equals(searchJob)) {
						avo = list.get(i);
						if (avo.getJob().equals(searchJob))

							data.add(avo);
						cbList.getSelectionModel().clearSelection();
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

	// �Ⱓ �˻� �޼ҵ�
	public void handlerBtnSearchDateAction(ActionEvent event) {

		ApplicantManagerDAO amDAO = new ApplicantManagerDAO();
		ApplicantVO avo = new ApplicantVO();
		ArrayList<ApplicantVO> list;

		try {
			list = amDAO.getAppcantTotal();
			ApplicantViewDAO avDAO = new ApplicantViewDAO();
			avo = avDAO.searchDate(dpStartDay.getValue().toString(), dpEndDay.getValue().toString());
			int rc = list.size();

			if (!dpStartDay.equals("") && !dpEndDay.equals("")) {
				data.removeAll(data);
				for (int i = 0; i < rc; i++) {
					avo = list.get(i);
					if (dpEndDay != null && dpStartDay != null) {
						data.add(avo);
					} 
				} // for�� ����
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�˻�");
			alert.setHeaderText("��¥ �˻� ����");
			alert.setContentText("��Ȯ�� ��¥�� �Է��ϼ���");
			alert.show();
		}
	}

	// ���̺� ��ü ����Ʈ �޼ҵ�
	public void totalList() {
		ApplicantManagerDAO adao = new ApplicantManagerDAO();
		ArrayList<ApplicantVO> list = new ArrayList<>();
		ApplicantVO avo = new ApplicantVO();

		list = adao.getAppcantTotal();
		int rowCount = list.size();

		for (int i = 0; i < rowCount; i++) {
			avo = list.get(i);
			data.add(avo);
		}
	}

}
