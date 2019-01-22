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
	// 검색
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
	// 날짜
	@FXML
	private DatePicker dpStartDay;
	@FXML
	private DatePicker dpEndDay;

	// 차트
	@FXML
	private PieChart pieChart;
	@FXML
	private BarChart<String, Number> barChart;

	int no; // 인덱스 번호 받아오기
	int count = 0;

	ObservableList<ApplicantVO> data = FXCollections.observableArrayList();
	ObservableList<ApplicantVO> selectApplicate;
	int selectedIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		hBox.setOnMouseClicked(event -> {

		});
		// 콤보박스 정의
		cbList.setItems(FXCollections.observableArrayList("모델", "DJ", "퍼포먼서"));
		// 파이차트 설정
		PieChart();
		// 바 차트
		getBarChart();

		// 테이블 칼럼 설정
		TableColumn colNo = new TableColumn("NO");
		colNo.setMaxWidth(60);
		colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

		TableColumn colJob = new TableColumn("지원분야");
		colJob.setMaxWidth(60);
		colJob.setCellValueFactory(new PropertyValueFactory<>("job"));

		TableColumn colName = new TableColumn("이름");
		colName.setMaxWidth(60);
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn colAge = new TableColumn("생년월일");
		colAge.setMaxWidth(120);
		colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn colHeight = new TableColumn("키");
		colHeight.setMaxWidth(60);
		colHeight.setCellValueFactory(new PropertyValueFactory<>("height"));

		TableColumn colWeight = new TableColumn("몸무게");
		colWeight.setMaxWidth(60);
		colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));

		TableColumn colPhoneNumber = new TableColumn("전화번호");
		colPhoneNumber.setMaxWidth(100);
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		TableColumn colEmail = new TableColumn("이메일");
		colEmail.setMaxWidth(120);
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn colEmployeeNumber = new TableColumn("사원번호");
		colEmployeeNumber.setMaxWidth(60);
		colEmployeeNumber.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));

		TableColumn colImage = new TableColumn("이미지");
		colImage.setMaxWidth(60);
		colImage.setCellValueFactory(new PropertyValueFactory<>("image"));

		TableColumn colDate = new TableColumn("등록일");
		colDate.setMaxWidth(120);
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

		// 테이블에 applicantVO 정보 담기
		tableView.setItems(data);

		// 테이블 뷰에 컬럼 담기
		tableView.getColumns().addAll(colNo, colJob, colName, colAge, colHeight, colWeight, colPhoneNumber, colEmail,
				colEmployeeNumber, colImage, colDate);

		// 메인으로 돌아가기 버튼 이벤트
		btnCancel.setOnAction(event -> {
			try {
				Pane root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.setTitle("KHK 엔터테이먼트 지원자 관리프로그램");
				Stage old = (Stage) btnCancel.getScene().getWindow();
				old.close();
				primaryStage.show();

			} catch (Exception e) {
			}
		});
		String localUrl = "/image/a100.png";
		localImage = new Image(localUrl, false);
		imageView.setImage(localImage);

		// 이름으로 검색
		btnSearchName.setOnAction(event -> handlerBtnSearchNameAction(event));
		// 콤보박스 검색
		btnSearchList.setOnAction(event -> handlerBtnSearchListAction(event));
		// 날자 검색 이벤트
		btnSaerchDate.setOnAction(event -> handlerBtnSearchDateAction(event));
		// 테이블뷰 마우스 클릭 이벤트
		tableView.setOnMouseClicked(event -> handlerTableViewMouseClickAction(event));
		// 전체보기 버튼 이벤트
		btnTotal.setOnAction(event -> handlerBtnTotalAction(event));

		totalList();

	}
	// 전체보기 
	public void handlerBtnTotalAction(ActionEvent event) {
		data.removeAll(data);
		totalList();
	}

	// 바차트 메소드
	public void getBarChart() {
		ApplicantViewDAO aDAO = new ApplicantViewDAO();
		XYChart.Series model = new XYChart.Series();
		model.setName("모델 인원수");
		model.getData().add(new XYChart.Data("모델", aDAO.pieChartModel()));
		XYChart.Series DJ = new XYChart.Series();
		DJ.setName("DJ 인원수");
		DJ.getData().add(new XYChart.Data("DJ", aDAO.pieChartDJ()));
		XYChart.Series performance = new XYChart.Series();
		performance.setName(" 퍼포먼서 인원수");
		performance.getData().add(new XYChart.Data("퍼포먼서", aDAO.pieChartPerformance()));

		barChart.getData().addAll(model, performance, DJ);

	}
	// 파이차트 메소드
	public void PieChart() {
		ApplicantViewDAO amDAO = new ApplicantViewDAO();
		
		//차트에 값 넣기
		pieChart.setData(FXCollections.observableArrayList(new PieChart.Data("모델",  amDAO.pieChartModel()),
				new PieChart.Data("퍼포먼서",  amDAO.pieChartPerformance()), new PieChart.Data("DJ", amDAO.pieChartDJ())));
	}

	// 테이블 클릭 이벤트 메소드
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

	// 지원분야 검색 버튼 메소드
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
			alert.setTitle("검색");
			alert.setHeaderText("지원분야별 검색 실패");
			alert.setContentText("검색할 값을 넣어주세요");
			alert.show();
		}

	}

	// 이름 검색 메소드
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
					alert.setTitle("이름 검색");
					alert.setHeaderText("이름 검색 실패");
					alert.setContentText("이름을 입력 해 주세요");
					alert.show();
				}
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("이름 검색");
			alert.setHeaderText("이름 검색 실패");
			alert.setContentText("값을 찾을수 없습니다.");
			alert.show();
		}
	}

	// 기간 검색 메소드
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
				} // for문 종료
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("검색");
			alert.setHeaderText("날짜 검색 실패");
			alert.setContentText("정확한 날짜를 입력하세요");
			alert.show();
		}
	}

	// 테이블 전체 리스트 메소드
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
