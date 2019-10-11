package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    TextField text;

    @FXML
    ListView list;

    ObservableList<ToDoItem> items = FXCollections.observableArrayList();

    public void addItem (){
        String t = text.getText();
        ToDoItem item = new ToDoItem(t, false);
        items.add(item);
        text.clear();
    }

    public void toggleItem(){
        ToDoItem item = (ToDoItem) list.getSelectionModel().getSelectedItem();
        if (item != null) {
        item.isDone = !item.isDone;
        list.refresh();
        }
    }

    public void removeItem(){
        ToDoItem item = (ToDoItem) list.getSelectionModel().getSelectedItem();
        items.remove(item);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(items);
    }
}
