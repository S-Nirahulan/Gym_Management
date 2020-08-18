package com.gym_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class UI {
    public static Pane table(){

        // Table view to show default member details in table
        TableView<DefaultMember>defaultMemberTableView;

        // Column for Membership number
        TableColumn<DefaultMember, Integer> memNoColumn = new TableColumn<>("Membership No");
            // Setting minimum width for column
            memNoColumn.setMinWidth(100);
            memNoColumn.setCellValueFactory(new PropertyValueFactory<>("memberNo"));

        // Column for Member Name
        TableColumn<DefaultMember,String> nameColumn = new TableColumn<>("Name");
            nameColumn.setMinWidth(200);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Column for Membership started date
        TableColumn<DefaultMember,String> startDateColumn = new TableColumn<>("Started Date");
            startDateColumn.setMinWidth(100);
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startMemDate"));

        // Column for Membership type
        TableColumn<DefaultMember,String> memTypeColumn = new TableColumn<>("Membership Type");
            memTypeColumn.setMinWidth(200);
            memTypeColumn.setCellValueFactory(new PropertyValueFactory<>("memberType"));

        defaultMemberTableView = new TableView<>();

        TextField nameSearchField = new TextField();
        nameSearchField.setPromptText("Name");
        nameSearchField.setMaxWidth(100);
        nameSearchField.setLayoutX(50);
        nameSearchField.setLayoutY(430);

        Button nameSearchBtn = new Button("Search");
        nameSearchBtn.setStyle("-fx-background-radius: 30;");
        nameSearchBtn.setLayoutX(160);
        nameSearchBtn.setLayoutY(430);

        TextField memNoSearchField = new TextField();
        memNoSearchField.setPromptText("Membership No");
        memNoSearchField.setMaxWidth(110);
        memNoSearchField.setLayoutX(250);
        memNoSearchField.setLayoutY(430);

        Button memNoSearchBtn = new Button("Search");
        memNoSearchBtn.setStyle("-fx-background-radius: 30;");
        memNoSearchBtn.setLayoutX(370);
        memNoSearchBtn.setLayoutY(430);

        // Setting items from observable list which have DefaultMember objects
        defaultMemberTableView.setItems(MyGymManager.observableList());

        defaultMemberTableView.getColumns().addAll(nameColumn,memNoColumn,startDateColumn,memTypeColumn);

        nameSearchBtn.setOnAction(event -> {
            String memberName = nameSearchField.getText().trim().toLowerCase();
            if (!nameSearchField.getText().trim().isEmpty()) {
                defaultMemberTableView.setItems(searchAlgorithm(MyGymManager.observableList(), memberName));
            }else{
                defaultMemberTableView.setItems(MyGymManager.observableList());
            }
        });

        memNoSearchBtn.setOnAction(event -> {
            int memberNo=0;
            try {
                memberNo = Integer.parseInt(memNoSearchField.getText().trim());
            }catch(NumberFormatException e){
                defaultMemberTableView.setItems(MyGymManager.observableList());
            }
            if (!memNoSearchField.getText().trim().isEmpty()) {
                defaultMemberTableView.setItems(searchAlgorithm(MyGymManager.observableList(), memberNo));
            }else{
                defaultMemberTableView.setItems(MyGymManager.observableList());
            }
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(defaultMemberTableView, nameSearchField, nameSearchBtn,memNoSearchField,memNoSearchBtn);
        return pane;
    }

    public  static ObservableList<DefaultMember> searchAlgorithm(ObservableList<DefaultMember> list, String searchValue){
        ArrayList<DefaultMember> resultList = new ArrayList<>();
        ObservableList<DefaultMember> observableList = FXCollections.observableList(resultList);

        // Searching until finding
        for (DefaultMember defaultMember : list) {
            if (defaultMember.getName().toLowerCase().equals(searchValue)) {
                resultList.add(defaultMember);
            }
        }

        return observableList;
    }

    public  static ObservableList<DefaultMember> searchAlgorithm(ObservableList<DefaultMember> list, int searchValue){
        ArrayList<DefaultMember> resultList = new ArrayList<>();
        ObservableList<DefaultMember> observableList = FXCollections.observableList(resultList);

        // Searching until finding
        for (DefaultMember defaultMember : list) {
            if (defaultMember.getMemberNo()==(searchValue)) {
                resultList.add(defaultMember);
            }
        }

        return observableList;
    }
}
