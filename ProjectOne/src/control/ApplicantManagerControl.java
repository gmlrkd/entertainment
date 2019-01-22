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
	// 검색
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

	int count;// 로우 값 가져오기

	int no; // 인덱스번호

	// 이미지 파일 첨부 필드
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
	// 이미지 처리
	// 이미지 저장할 폴더를 매개변수로 파일 객체 생성
	private File dirSave = new File("C:/images");
	// 이미지 불러올 파일을 저장할 파일 객체 선언

	// 테이블 처리
	ObservableList<ApplicantVO> data = FXCollections.observableArrayList();
	ObservableList<ApplicantVO> selectApplicate;
	int selectedIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 버튼 이벤트
		btnCancel.addEventHandler(MouseEvent.MOUSE_ENTERED, new MouseButtonEvent());
		txtFileAddr.setEditable(false);
		// 콤보박스 정의
		cbList.setItems(FXCollections.observableArrayList("DJ", "모델", "퍼포먼서"));
		cbSearchJob.setItems(FXCollections.observableArrayList("DJ", "모델", "퍼포먼서"));
		// 직원번호 숫자만 입력
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

		tableView.setItems(data);

		// 테이블 뷰에 컬럼 담기
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
				primaryStage.setTitle("관리자 관리메뉴 선택");
				primaryStage.show();
			} catch (Exception e) {
				System.out.println(e);
			}

		});// 취소버튼 이벤트

		// 등록버튼 이벤트
		btnOk.setOnAction(event -> handlerBtnOkAction(event));

		// 이미지 등록 버튼 이벤트
		btnFileSave.setOnAction(event -> handlerBtnFileSaveAction(event));
		// 사번 검색 이벤트
		btnSearchEmployeeNumber.setOnAction(event -> handlerBtnSearchEmployeeNumber(event));

		// 테이블 클릭 이벤트
		tableView.setOnMousePressed(event -> handlerMousClickAction(event));

		// 수정버튼 이벤트
		btnEdite.setOnAction(event -> handlerBtnEditeAction(event));

		// 삭제 버튼 이벤트
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

		// 초기화 버튼 메소드
		btnReset.setOnAction(event -> {
			handlerBtnResetAction();
		});

		// 이름으로 검색
		btnSearchName.setOnAction(event -> handlerBtnSearchNameAction(event));

		// 콤보박스 검색
		btnSearchJob.setOnAction(event -> handlerBtnSearchJobAction(event));

		// 총 인원 리스트
		totalList();

	}// 메인 메소드

	// 직원번호 검색
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
			alert.setTitle("검색");
			alert.setHeaderText("사번 검색 실패");
			alert.setContentText("검색할 값을 넣어주세요");
			alert.show();
		}
	}

	// 지원분야 검색 버튼 메소드
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

	// 전체 리스트
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

	// 등록버튼 메소드
	public void handlerBtnOkAction(ActionEvent event) {

		try {
			ApplicantVO avo = null;
			ApplicantManagerDAO amdao = null;
			File dirMake = new File(dirSave.getAbsolutePath());
			// 파일 저장
			if (!dirMake.exists()) {
				dirMake.mkdir();
			}
			// 이미지 파일 저장
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
				alert.setTitle("입력 오류");
				alert.setHeaderText("등록자 사원번호 미 입력");
				alert.setContentText("등록자 사원번호를 입력 후 등록 하세요");
				alert.show();
			} else {
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}// 등록 메소드 end

	// 수정메소드
	public void handlerBtnEditeAction(ActionEvent event) {

		try {
			ApplicantManagerDAO amDAO = new ApplicantManagerDAO();

			ApplicantVO avo = new ApplicantVO();

			// applicantVO 에서 값 받기
			avo = new ApplicantVO(cbList.getValue().toString(), txtName.getText(), txtAge.getText(),
					txtHeight.getText(), txtWeight.getText(), txtPhoneNumber.getText(), txtEmail.getText(),
					txtSpecialnote.getText(), txtEmployeeNumber.getText(), txtFileAddr.getText());

			// dao 에 넘겨주기
			System.out.println(no);
			amDAO.applicantUpdate(avo, no);

			data.removeAll(data);
			// 전체 정보
			totalList();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 테이블 마우스 이벤트 메소드
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

	// 초기화 메소드
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

	// 이미지 파일 첨부버튼 메소드
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

	}// 이미지 파일 메소드 end

}// 클래스 종료
