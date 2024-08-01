package com.taskmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TaskController {

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private TextField taskDescriptionField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    private TaskManager taskManager;
    private ObservableList<Task> taskList;

    @FXML
    public void initialize() {
        taskManager = new TaskManager();
        taskManager.createTable(); // Ensure table exists
        taskList = FXCollections.observableArrayList(taskManager.getAllTasks());
        taskListView.setItems(taskList);

        addButton.setOnAction(event -> addTask());
        deleteButton.setOnAction(event -> deleteTask());
    }

    private void addTask() {
        String description = taskDescriptionField.getText();
        if (!description.isEmpty()) {
            taskManager.addTask(description);
            taskList.setAll(taskManager.getAllTasks());
            taskDescriptionField.clear();
        }
    }

    private void deleteTask() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskManager.deleteTask(selectedTask.getId());
            taskList.setAll(taskManager.getAllTasks());
        }
    }
}
