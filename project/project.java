
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import java.io.FileNotFoundException;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;


//Java SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

//Laundryshops class


public class project extends Application{
    Label connstatus = new Label();
	@Override
	public void start (Stage start) throws FileNotFoundException{
		connectToDB();

		//Debug HERE
		stage1();
		//signupStage();
		//customerStage();
	}

	public void stage1() {
        connectToDB();
		Stage login = new Stage();
		GridPane gridPane = new GridPane();
		Scene scene = new Scene(gridPane, 500, 600);
		gridPane.setPadding(new Insets(10, 10, 10, 10));

        Button loginB = new Button("Login");
        Button signupB = new Button("Signup");
		TextField username = new TextField();
		PasswordField password = new PasswordField();
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		Label label1 = new Label("FINDING LAUNDRY");

		label1.setFont(Font.font("Mistral",FontWeight.EXTRA_BOLD, 28));

		gridPane.add(label1,1,30);

		gridPane.add(new Label("Username:"), 0, 40);
		gridPane.add(username, 1, 40);
		gridPane.add(new Label("Password:"), 0, 41);
        gridPane.add(password, 1, 41);

        HBox hbox = new HBox();
        hbox.getChildren().add(loginB);
        hbox.getChildren().add(signupB);
		gridPane.add(hbox, 1, 42);


		username.setEditable(true);
        password.setEditable(true);

		gridPane.setAlignment(Pos.TOP_CENTER);
		username.setAlignment(Pos.BOTTOM_LEFT);

		password.setAlignment(Pos.BOTTOM_LEFT);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.setSpacing(3.0);


		loginB.setOnAction(e -> {
			// loginStage();
			// Home();
			boolean checker = false;
			for(Node i: gridPane.getChildren()){
				if(i instanceof TextField){
					TextField temp = new TextField();
					temp = (TextField)i;
					if(temp.getText().trim().equals("")){
						checker = true;
						break;
					}
				}
			}
			if(checker){
				Alert alert = new Alert(AlertType.INFORMATION,"Please fill-up all text fields!");
				alert.setHeaderText(null);
				alert.showAndWait();
			}else{
				if(isUser(username.getText())){
					if(isLogin(username.getText(), password.getText())){
						if(typeUser(username.getText()).equals("C")){
							Home();
							login.close();
						}
						else{
							LaundryOwnerStage();
							login.close();
						}
					}
					else{
						Alert alert = new Alert(AlertType.INFORMATION,"Password Incorrect!");
						alert.setHeaderText(null);
						alert.showAndWait();
					}
				}
				else{
					Alert alert = new Alert(AlertType.INFORMATION,"There's no username registered like that!");
					alert.setHeaderText(null);
					alert.showAndWait();
				}

			}

        });

        signupB.setOnAction(e -> {
			signupStage();
			login.close();

        });


		login.setTitle("Finding Laundry");
		login.setScene(scene);
		login.show();
	}

	void transactionStage(){

		Stage choices = new Stage();
		GridPane gridPane = new GridPane();
		String services[] = new String[2];
		services[0] = "Pickup";
		services[1] = "Delivery";



		ComboBox <String> service = new ComboBox<>();
		ObservableList<String> items = FXCollections.observableArrayList(services);
		service.getItems().addAll(items);

		CheckBox wash = new CheckBox("Wash");
		CheckBox dry = new CheckBox("Dry");
		CheckBox fold = new CheckBox("Fold");


		Button back = new Button("Back");
		Button submit = new Button("Submit");



		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setAlignment(Pos.CENTER);

		gridPane.add(new Label("Type of service:"), 0, 3);
		gridPane.add(service, 1, 3);
		gridPane.add(wash, 0, 4);
		gridPane.add(dry, 0, 5);
		gridPane.add(fold, 0, 6);



		gridPane.add(back, 0, 10);
		gridPane.add(submit, 5, 10);
		GridPane.setHalignment(back, HPos.LEFT);




		back.setOnAction(e -> {
			stage1();
			choices.close();
		});

		Scene scene1 = new Scene(gridPane, 400, 500);
		choices.setTitle("Finding Laundry - Transaction");
		choices.setScene(scene1);
		choices.show();
    }

    void signupStage(){
		Stage stage = new Stage();
		GridPane gridpane = new GridPane();


		String services[] = new String[2];
		services[0] = "Customer";
		services[1] = "Laundry Owner";


		ComboBox <String> service = new ComboBox<>();
		ObservableList<String> items = FXCollections.observableArrayList(services);
		service.getItems().addAll(items);


		TextField firstname = new TextField();
		firstname.setPromptText("Enter your first name.");
		TextField lastname = new TextField();
		lastname.setPromptText("Enter your last name.");
		TextField address = new TextField();
		address.setPromptText("Complete Address.");
		TextField contact = new TextField();
		contact.setPromptText("Contact Number.");
		TextField landmark = new TextField();
		landmark.setPromptText("Landmark Address.");
		Label llandmark = new Label("Address Landmark: ");
		Label additional = new Label("Additional Information Needed: ");
		Label space = new Label("");
		TextField laundryshopAddress = new TextField();
		laundryshopAddress.setPromptText("Shop Address.");
		TextField laundryshopName = new TextField();
		laundryshopName.setPromptText("Shop Name.");
		TextField latitude = new TextField();
		latitude.setPromptText("X Axis");
		TextField longitude = new TextField();
		longitude.setPromptText("Y Axis.");
		Label laundryshopA = new Label("Laundry Shop Address: ");
		Label laundryshopN = new Label("Laundry Shop Name: ");
		Label lLatitude = new Label("Latitude: ");
		Label llongitude = new Label("Longitude: ");

		Button back = new Button("Back");
		Button sign_up = new Button("Sign Up");

		Label current = new Label();

		TextField username = new TextField();
		PasswordField pass = new PasswordField();

		int pos = 0;
		gridpane.add(new Label("Type of User:"), 0, pos);
		gridpane.add(service, 1, pos++);
		gridpane.add(new Label("First Name:"), 0, pos);
		gridpane.add(firstname, 1, pos++);
		gridpane.add(new Label("Last Name:"), 0, pos);
        gridpane.add(lastname, 1, pos++);
		gridpane.add(new Label("Address:"), 0, pos);
		gridpane.add(address, 1, pos++);
		gridpane.add(new Label("Contact Information:"), 0, pos);
		gridpane.add(contact, 1, pos++);
		gridpane.add(new Label("Username:"), 0, pos);
		gridpane.add(username, 1, pos++);
		gridpane.add(new Label("Password:"), 0, pos);
		gridpane.add(pass, 1, pos++);

		gridpane.add(back, 0, 15);
		gridpane.add(sign_up, 1, 15);

		gridpane.setHgap(5);
		gridpane.setVgap(5);

		firstname.setEditable(true);
        lastname.setEditable(true);
		address.setEditable(true);
        contact.setEditable(true);

		gridpane.setAlignment(Pos.CENTER);
		firstname.setAlignment(Pos.BOTTOM_LEFT);
		lastname.setAlignment(Pos.BOTTOM_LEFT);
		address.setAlignment(Pos.BOTTOM_LEFT);
		contact.setAlignment(Pos.BOTTOM_LEFT);

		GridPane.setHalignment(sign_up, HPos.RIGHT);



		back.setOnAction(e -> {
			stage1();
			stage.close();
		});

		sign_up.setOnAction(e ->{
			boolean checker = false;
			for(Node i: gridpane.getChildren()){
				if(i instanceof TextField){
					TextField temp = new TextField();
					temp = (TextField)i;
					if(temp.getText().trim().equals("")){
						checker = true;
						break;
					}
				}
			}
			if(checker){
				Alert alert = new Alert(AlertType.INFORMATION,"Please fill-up all text fields!");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			else{
				if(current.getText().equals("Customer")){
					inputUsers(firstname.getText(), lastname.getText(), address.getText(), contact.getText(), landmark.getText(), username.getText(), pass.getText());
				}
				else{
					inputUsers(firstname.getText(), lastname.getText(), address.getText(), contact.getText(), laundryshopName.getText(), laundryshopAddress.getText(), latitude.getText(), longitude.getText() , username.getText(), pass.getText());
				}
			}
		});

		service.setOnAction(e ->{
			current.setText(service.getValue());
			if(current.getText().equals("Customer")){
				if(gridpane.getChildren().size() == 26){
					gridpane.getChildren().remove(space);
					gridpane.getChildren().remove(additional);
					gridpane.getChildren().remove(laundryshopA);
					gridpane.getChildren().remove(laundryshopAddress);
					gridpane.getChildren().remove(laundryshopN);
					gridpane.getChildren().remove(laundryshopName);
					gridpane.getChildren().remove(latitude);
					gridpane.getChildren().remove(longitude);
					gridpane.getChildren().remove(llongitude);
					gridpane.getChildren().remove(lLatitude);
				}
				if(gridpane.getChildren().size() == 16){
					gridpane.add(space, 0, 7);
					gridpane.add(additional, 0, 8);
					gridpane.add(llandmark, 0, 9);
					gridpane.add(landmark, 1, 9);
				}
			}
			else if(current.getText().equals("Laundry Owner")){
				if(gridpane.getChildren().size() == 20){
					gridpane.getChildren().remove(llandmark);
					gridpane.getChildren().remove(landmark);
					gridpane.getChildren().remove(additional);
					gridpane.getChildren().remove(space);
				}
				if(gridpane.getChildren().size() == 16){
					gridpane.add(space, 0, 7);
					gridpane.add(additional, 0, 8);
					gridpane.add(laundryshopN, 0, 9);
					gridpane.add(laundryshopName, 1, 9);
					gridpane.add(laundryshopA, 0, 10);
					gridpane.add(laundryshopAddress, 1, 10);
					gridpane.add(lLatitude, 0, 11);
					gridpane.add(latitude, 1, 11);
					gridpane.add(llongitude, 0, 12);
					gridpane.add(longitude, 1, 12);
				}
			}
		});
		Scene scene1 = new Scene(gridpane, 500, 600);
        stage.setTitle("Finding Laundry - Sign-up");
		stage.setScene(scene1);
		stage.show();
	}
	public void Home(){
		Stage choices = new Stage();
		Pane pane = new Pane();
		GridPane gridPane = new GridPane();

		Label info = new Label("Leave the");
		info.setFont(Font.font("Courier New", 15));
		Label info2 = new Label("care to us,");
		info2.setFont(Font.font("Courier New", 15));
		Label mation2 = new Label("have some fun!");
		mation2.setFont(Font.font("Courier New", 15));

		TableView<laundryshops> shops = new TableView<laundryshops>();
		TableColumn<laundryshops, String> laundryName = new TableColumn<laundryshops, String>("Name");
		TableColumn<laundryshops, String> laundryaddress = new TableColumn<laundryshops, String>("Address");
		TableColumn<laundryshops, String> laundrylat = new TableColumn<laundryshops, String>("Latitude");
		TableColumn<laundryshops, String> laundrylong = new TableColumn<laundryshops, String>("Longitude");
		TableColumn<laundryshops, String> laundryrate = new TableColumn<laundryshops, String>("Ratings");



		laundryName.setCellValueFactory(new PropertyValueFactory<>("name"));
		laundryaddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		laundrylat.setCellValueFactory(new PropertyValueFactory<>("latitude"));
		laundrylong.setCellValueFactory(new PropertyValueFactory<>("longitude"));
		laundryrate.setCellValueFactory(new PropertyValueFactory<>("ratings"));

		laundryName.setSortType(TableColumn.SortType.ASCENDING);


		ObservableList<laundryshops> list = getUserList();
		shops.setItems(list);

		shops.getColumns().addAll(laundryName, laundryaddress, laundrylat, laundrylong, laundryrate);

		Pane table = new Pane();
		table.getChildren().add(shops);
		Button home = new Button("  Home  ");
		Button status = new Button("  Status  ");
		Button account = new Button("  Account  ");
		Button logout = new Button("logout");

		home.setStyle("-fx-font-size: 2em; ");
		status.setStyle("-fx-font-size: 2em; ");
		account.setStyle("-fx-font-size: 2em; ");


		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setAlignment(Pos.CENTER);

		gridPane.add(info, 0, 3);
		gridPane.add(info2, 1, 3);
		gridPane.add(mation2, 2, 3);


		gridPane.add(home, 0, 2);
		gridPane.add(status, 1, 2);
		gridPane.add(account, 2, 2);
		gridPane.add(logout, 0, 34);
		GridPane.setHalignment(info, HPos.CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);

		gridPane.setLayoutX(500/2 - 170);
		gridPane.setLayoutY(20);

		table.setLayoutX(58);
		table.setLayoutY(125);
		shops.setOnMouseClicked(e ->{
			laundryshops name;
			if (e.getButton() == MouseButton.PRIMARY) {
				if(e.getClickCount() == 2){
					name = shops.getSelectionModel().getSelectedItem();
					transactionStage();
				}

			}
		});

		pane.getChildren().add(gridPane);
		pane.getChildren().add(table);
		logout.setOnAction(e -> {
			stage1();
			choices.close();
		});
		account.setOnAction(e -> {
			customerStage();
			choices.close();
		});
		status.setOnAction(e -> {
			StatusStage();
			choices.close();
		});

		Scene scene1 = new Scene(pane, 500, 600);
		choices.setTitle("Finding Laundry");
		choices.setScene(scene1);
		choices.show();
	}
public void StatusStage(){
		Stage choices = new Stage();
		GridPane gridPane = new GridPane();

		Label info = new Label("Leave the");
		info.setFont(Font.font("Courier New", 15));
		Label info2 = new Label("care to us,");
		info2.setFont(Font.font("Courier New", 15));
		Label mation2 = new Label("have some fun!");
		mation2.setFont(Font.font("Courier New", 15));
		Hyperlink additionalI =  new Hyperlink("  + Instructions   ");
		Hyperlink view =  new Hyperlink("                view");
		Hyperlink one =   new Hyperlink("             Regular");
		Hyperlink two =   new Hyperlink("             Express");


		Button home = new Button("  Home  ");
		Button status = new Button("  Status  ");
		Button account = new Button("  Account  ");
		Button logout = new Button("logout");

		home.setStyle("-fx-font-size: 2em; ");
		status.setStyle("-fx-font-size: 2em; ");
		account.setStyle("-fx-font-size: 2em; ");

		Label summary = new Label("Order Summary: ");
		summary.setFont(Font.font("Times New Roman", 16));

		Label pick = new Label("Pick Up?");
		pick.setFont(Font.font("Arial", 15));
		Label del = new Label("Delivery Fee?");
		del.setFont(Font.font("Arial", 15));
		Label ret = new Label("Return?");
		ret.setFont(Font.font("Arial", 15));

		Label loc = new Label("Ateneo Ave, Naga,");
		loc.setFont(Font.font("Arial", 13));
		Label locn = new Label("4400 Camarines Sur");
		locn.setFont(Font.font("Arial", 13));

		Label r = new Label("Friday, 22, Nov.2019");
		r.setFont(Font.font("Arial", 13));
		Label re = new Label("7:00-8:00PM");
		re.setFont(Font.font("Arial", 13));

		Label oneD = new Label("2 up to 3 days");
		oneD.setFont(Font.font("Arial", 13));
		Label twoD = new Label("4 up to 5 days");
		twoD.setFont(Font.font("Arial", 13));

		Label D1 = new Label("Php 200.00");
		D1.setFont(Font.font("Arial", 13));
		Label D2 = new Label("Php 120.00");
		D2.setFont(Font.font("Arial", 13));


		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setAlignment(Pos.CENTER);

		gridPane.add(info, 0, 3);
		gridPane.add(info2, 1, 3);
		gridPane.add(mation2, 2, 3);

		gridPane.add(additionalI, 1, 6);
		gridPane.add(summary, 1, 8);
		gridPane.add(pick, 0, 9);
		gridPane.add(del, 0, 11);
		gridPane.add(one, 0, 12);
		gridPane.add(oneD, 1, 13);
		gridPane.add(two, 0, 13);
		gridPane.add(twoD, 1, 12);
		gridPane.add(D2, 2, 12);
		gridPane.add(D1, 2, 13);
		gridPane.add(view, 0, 10);
		gridPane.add(loc, 1, 10);
		gridPane.add(locn, 2, 10);
		gridPane.add(ret, 0, 14);
		gridPane.add(r, 1, 15);
		gridPane.add(re, 2, 15);



		gridPane.add(home, 0, 2);
		gridPane.add(status, 1, 2);
		gridPane.add(account, 2, 2);
		gridPane.add(logout, 0, 34);
		GridPane.setHalignment(info, HPos.CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);




		logout.setOnAction(e -> {
			stage1();
			choices.close();
		});
		account.setOnAction(e -> {
			customerStage();
			choices.close();
		});
		home.setOnAction(e -> {
			Home();
			choices.close();
		});

		Scene scene1 = new Scene(gridPane, 500, 600);
		choices.setTitle("Finding Laundry");
		choices.setScene(scene1);
		choices.show();
	}
	public void customerStage(){

		Stage choices = new Stage();
		GridPane gridPane = new GridPane();
       	String services[] = new String[2];
		services[0] = "Customer";
		services[1] = "Laundry Owner";

		ComboBox <String> service = new ComboBox<>();
		ObservableList<String> items = FXCollections.observableArrayList(services);
		service.getItems().addAll(items);


		Label info = new Label("Leave the");
		info.setFont(Font.font("Courier New", 15));
		Label info2 = new Label("care to us,");
		info2.setFont(Font.font("Courier New", 15));
		Label mation2 = new Label("have some fun!");
		mation2.setFont(Font.font("Courier New", 15));
		Hyperlink about = new Hyperlink("         About          ");
		Hyperlink Help =  new Hyperlink("         Help           ");

		Label edit = new Label("Edit Account: ");
		edit.setFont(Font.font("Times New Roman", 15));
		TextField firstname = new TextField();
		TextField lastname = new TextField();
		TextField address = new TextField();
		TextField contact = new TextField();


		Button home = new Button("  Home  ");
		Button status = new Button("  Status  ");
		Button account = new Button("  Account  ");

		Button logout = new Button("logout");

		home.setStyle("-fx-font-size: 2em; ");
		status.setStyle("-fx-font-size: 2em; ");
		account.setStyle("-fx-font-size: 2em; ");


		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setAlignment(Pos.CENTER);


		//gridPane.add(ib, 1, 50);
		gridPane.add(about, 1, 5);
		gridPane.add(Help, 1, 6);
		gridPane.add(edit, 0, 7);
		gridPane.add(new Label("Type of User:"), 1, 8);
		gridPane.add(service, 2, 8);
		gridPane.add(firstname, 2, 9);
		gridPane.add(new Label("       First Name:"), 1, 9);
		gridPane.add(lastname, 2, 10);
		gridPane.add(new Label("       Last Name:"), 1, 10);
		gridPane.add(address, 2, 11);
		gridPane.add(new Label("       Address:"), 1, 11);
		gridPane.add(contact, 2, 12);
		gridPane.add(new Label("       Contact:"), 1, 12);

		gridPane.add(info, 0, 3);
		gridPane.add(info2, 1, 3);
		gridPane.add(mation2, 2, 3);


		gridPane.add(home, 0, 2);
		gridPane.add(status, 1, 2);
		gridPane.add(account, 2, 2);
		gridPane.add(logout, 0, 34);
		GridPane.setHalignment(info, HPos.CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);


		logout.setOnAction(e -> {
			stage1();
			choices.close();
		});
		home.setOnAction(e -> {
			Home();
			choices.close();
		});
		status.setOnAction(e -> {
			StatusStage();
			choices.close();
		});


		Scene scene1 = new Scene(gridPane, 500, 600);
		choices.setTitle("Finding Laundry");
		choices.setScene(scene1);
		choices.show();
	}
public void LaundryOwnerStage(){

		Stage choices = new Stage();
		Pane pane = new Pane();
		GridPane gridPane = new GridPane();

		Pane table = new Pane();
		TableView<order> orders = new TableView<order>();
		TableColumn<order, String> customer = new TableColumn<order, String>("Customer");
		TableColumn<order, String> caddress = new TableColumn<order, String>("Address");
		TableColumn<order, String> date = new TableColumn<order, String>("Date Received");
		TableColumn<order, Status> status = new TableColumn<order, Status>("Status");
		TableColumn<order, String> additionalinfo = new TableColumn<order, String>("Additional Information");


		customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
		caddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));

		additionalinfo.setCellValueFactory(new PropertyValueFactory<>("additionalinfo"));



		ObservableList<Status> statuslist = FXCollections.observableArrayList(Status.values());
		status.setCellValueFactory(new Callback<CellDataFeatures<order, Status>, ObservableValue<Status>>() {

            @Override
            public ObservableValue<Status> call(CellDataFeatures<order, Status> param) {
                order stat = param.getValue();
                String genderCode = stat.getStatus();
                Status gender = Status.getByCode(genderCode);
                return new SimpleObjectProperty<Status>(gender);
            }
		});
		status.setCellFactory(ComboBoxTableCell.forTableColumn(statuslist));

		status.setOnEditCommit((CellEditEvent<order, Status> event) -> {
            TablePosition<order, Status> pos = event.getTablePosition();

            Status newGender = event.getNewValue();

            int row = pos.getRow();
            order order = event.getTableView().getItems().get(row);

            order.setStatus(newGender.getCode());
		});

		orders.setEditable(true);
		ObservableList<order> list = getOrderList();
		orders.setItems(list);

		orders.getColumns().addAll(customer, caddress, date, status, additionalinfo);
		table.getChildren().add(orders);

		Label info = new Label("Leave the");
		info.setFont(Font.font("Courier New", 15));
		Label mation2 = new Label("work on us!");

		Button home = new Button("  Home  ");
		Button account = new Button("  Account  ");


		home.setStyle("-fx-font-size: 2em; ");
		account.setStyle("-fx-font-size: 2em; ");


		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setAlignment(Pos.CENTER);

		gridPane.add(info, 0, 3);
		gridPane.add(mation2, 2, 3);

		gridPane.add(home, 0, 2);;
		gridPane.add(account, 2, 2);

		GridPane.setHalignment(info, HPos.CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);

		table.setLayoutX(58);
		table.setLayoutY(125);

		pane.getChildren().add(gridPane);
		pane.getChildren().add(table);

		account.setOnAction(e -> {
			AccountL();
			choices.close();
		});


		Scene scene1 = new Scene(pane, 500, 600);
		choices.setTitle("Finding Laundry");
		choices.setScene(scene1);
		choices.show();
	}
	public void AccountL(){

		Stage choices = new Stage();
		GridPane gridPane = new GridPane();
       	String services[] = new String[2];
		services[0] = "Customer";
		services[1] = "Laundry Owner";

		ComboBox <String> service = new ComboBox<>();
		ObservableList<String> items = FXCollections.observableArrayList(services);
		service.getItems().addAll(items);


		Label info = new Label("Leave the");
		info.setFont(Font.font("Courier New", 15));
		Label mation2 = new Label("work on us!");
		mation2.setFont(Font.font("Courier New", 15));
		Hyperlink about = new Hyperlink("         About          ");
		Hyperlink Help =  new Hyperlink("         Help           ");

		Label edit = new Label("Edit Account: ");
		edit.setFont(Font.font("Times New Roman", 15));
		TextField firstname = new TextField();
		TextField lastname = new TextField();
		TextField address = new TextField();
		TextField contact = new TextField();


		Button home = new Button("  Home  ");
		Button account = new Button("  Account  ");

		Button logout = new Button("logout");

		home.setStyle("-fx-font-size: 2em; ");
		account.setStyle("-fx-font-size: 2em; ");


		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setAlignment(Pos.CENTER);


		//gridPane.add(ib, 1, 50);
		gridPane.add(about, 1, 5);
		gridPane.add(Help, 1, 6);
		gridPane.add(edit, 0, 7);
		gridPane.add(new Label("Type of User:"), 1, 8);
		gridPane.add(service, 2, 8);
		gridPane.add(firstname, 2, 9);
		gridPane.add(new Label("       First Name:"), 1, 9);
		gridPane.add(lastname, 2, 10);
		gridPane.add(new Label("       Last Name:"), 1, 10);
		gridPane.add(address, 2, 11);
		gridPane.add(new Label("       Address:"), 1, 11);
		gridPane.add(contact, 2, 12);
		gridPane.add(new Label("       Contact:"), 1, 12);

		gridPane.add(info, 1, 3);
		gridPane.add(mation2, 2, 3);


		gridPane.add(home, 1, 2);;
		gridPane.add(account, 2, 2);
		gridPane.add(logout, 0, 34);
		GridPane.setHalignment(info, HPos.CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);


		logout.setOnAction(e -> {
			stage1();
			choices.close();
		});
		home.setOnAction(e -> {
			LaundryOwnerStage();
			choices.close();
		});


		Scene scene1 = new Scene(gridPane, 500, 600);
		choices.setTitle("Finding Laundry");
		choices.setScene(scene1);
		choices.show();
	}



	//Import Signup data to Database
	//Customer
	public void inputUsers(String fname, String lname, String address, String contactinfo, String addresslandmark, String username, String pass){
		int id = 0;
		String currid = "";
		String getId = "SELECT user_id FROM USERS";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getId);

			while(rs.next()){
				id = rs.getInt("USER_ID");
				currid = Integer.toString(id);
			}
		}
		catch(SQLException e){
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
		}
		if(!currid.equals("")){
			id = Integer.parseInt(currid);
		}
		id++;
		System.out.println(id);
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO USERS VALUES('" + Integer.toString(id) +"','" + fname + "','" + lname + "','" + address + "','C')";
			int rs = stmt.executeUpdate(query);
			if(rs > 0){
				query = "INSERT INTO CUSTOMER VALUES('" + Integer.toString(id) + "','" + addresslandmark + "')";
				rs = stmt.executeUpdate(query);
				if(rs > 0){
					if(!isUser(username)){
						query = "INSERT INTO LOGIN VALUES('" + Integer.toString(id) + "','" + username + "','" + pass + "')";
						rs = stmt.executeUpdate(query);
						if(rs > 0){
							Alert alert = new Alert(AlertType.INFORMATION,"Success!");
							alert.setHeaderText(null);
							alert.showAndWait();
						}
					}
					else{
						Alert alert = new Alert(AlertType.INFORMATION,"Username taken!");
							alert.setHeaderText(null);
							alert.showAndWait();
					}
				}
			}
		}
		catch(SQLException e){
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
		}
	}
	//Laundry Owner
	public void inputUsers(String fname, String lname, String address, String contactinfo, String laundryname, String laundryaddress, String lat, String longi, String username, String pass){
		int id = 0;
		String currid = "";
		String getId = "SELECT user_id FROM USERS";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getId);

			while(rs.next()){
				id = rs.getInt("USER_ID");
				currid = Integer.toString(id);
			}
		}
		catch(SQLException e){
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
		}
		if(!currid.equals("")){
			id = Integer.parseInt(currid);
		}
		id++;
		System.out.println(id);
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO USERS VALUES('" + Integer.toString(id) +"','" + fname + "','" + lname + "','" + address + "','LO')";
			int rs = stmt.executeUpdate(query);
			if(rs > 0){
				query = "INSERT INTO LAUNDRY_SHOP VALUES('" + Integer.toString(id) + "','" + laundryname + "','" + longi + "','" + lat + "','" + laundryaddress + "',' 0 ')";
				rs = stmt.executeUpdate(query);
				if(rs > 0){
					if(!isUser(username)){
						query = "INSERT INTO LOGIN VALUES('" + Integer.toString(id) + "','" + username + "','" + pass + "')";
						rs = stmt.executeUpdate(query);
						if(rs > 0){
							Alert alert = new Alert(AlertType.INFORMATION,"Success!");
							alert.setHeaderText(null);
							alert.showAndWait();
						}
					}
					else{
						Alert alert = new Alert(AlertType.INFORMATION,"Username taken!");
							alert.setHeaderText(null);
							alert.showAndWait();
					}
				}
			}
		}
		catch(SQLException e){
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
		}
	}

	boolean isUser(String username){
		boolean checker = false;
		try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
			Statement stmt = conn.createStatement();
			String query = "SELECT USERNAME FROM LOGIN WHERE USERNAME LIKE '" + username + "'";
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()){
				checker = true;
			}
        }
        catch(SQLException e){
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
		}
		return checker;
	}
	String typeUser(String username){
		int id = 0;
		String type = "";
		try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
			Statement stmt = conn.createStatement();
			String query = "SELECT LOGIN_ID FROM LOGIN WHERE USERNAME LIKE '" + username + "'";
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()){
				id = rs.getInt("LOGIN_ID");
			}
			query = "SELECT USER_TYPE FROM USERS WHERE USER_ID LIKE '" + Integer.toString(id) + "'";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				type = rs.getString("USER_TYPE");
			}

        }
        catch(SQLException e){
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
		}

		return type;
	}

	boolean isLogin(String username, String password){
		boolean checker = false;
		try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM LOGIN WHERE USERNAME LIKE '" + username + "' AND PASSWORD LIKE '" + password + "'";
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()){
				checker = true;
			}

        }
        catch(SQLException e){
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
		}
		return checker;
	}

	ObservableList<order> getOrderList(){
		order example = new order("Lance", Status.BLANK.getCode() , "11/22/2019", "None", "Saban");
		ObservableList<order> list = FXCollections.observableArrayList(example);
		return list;
	}

	ObservableList<laundryshops> importLaundryshops(){
		ObservableList<laundryshops> list = FXCollections.observableArrayList();
		try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
			Statement stmt = conn.createStatement();
			String query = "SELECT name, address, longitude, latitude, average_rating from LAUNDRY_SHOP";
			ResultSet rs = stmt.executeQuery(query);
			laundryshops l[] = new laundryshops[50];
			int i = 0;
			while(rs.next()){
				String name = rs.getString("name");
				String address = rs.getString("address");
				String latitude = rs.getString("latitude");
				String longitude = rs.getString("longitude");
				String rating = rs.getString("average_rating");
				l[i] = new laundryshops(name, address,latitude, longitude, rating);
				i++;
			}
			laundryshops fin[] = new laundryshops[i];
			for(int j = 0; j < i; j++){
				fin[j] = l[j];
			}
			list = FXCollections.observableArrayList(fin);
        }
        catch(SQLException e){
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
		}
		return list;
	}

	ObservableList<laundryshops> getUserList() {
		return importLaundryshops();
    }

    public void connectToDB(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
            if(conn != null)
                connstatus.setText("CONNECTED!");
        }
        catch(SQLException e){
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}



    public static void main(String args[]){
        launch(args);
    }
}
