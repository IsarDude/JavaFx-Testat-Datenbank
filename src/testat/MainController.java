package testat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;


public class MainController {
    //Variablen

    private ObservableList<Person> items = FXCollections.observableArrayList();
    private Stage mainStage;
    private ArrayList<Person> safeData = new ArrayList<>();
    private Stage dialogStage;
    private FXMLLoader load;
    private ControllerDialog dialog;


    //methode zur verbindung mit main app
    public void setStage(Stage aStage) {
        this.mainStage = aStage;
    }

    //FXML referenzen
    @FXML
    private Person person;
    @FXML
    TextField na;
    @FXML
    TextField vo;
    @FXML
    TextField ps;
    @FXML
    TextField st;
    @FXML
    TextField nr;
    @FXML
    private TextField ort;
    @FXML
    ListView<Person> list;

    @FXML
    public void initialize() {

        list.setItems(items);

        //Anzeigen der personen bei klick
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                person = list.getSelectionModel().getSelectedItem();
                showPerson(person);
            }
        });
    }

    //methode zum löschen
    @FXML
    public void handleDelete() {
        int index = list.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            na.clear();
            ps.clear();
            st.clear();
            nr.clear();
            ort.clear();
            vo.clear();
            list.getItems().remove(index);

        }

    }


    //Methode um dialogfenster aufzurufen
    @FXML
    public void handleChange() {

        Person selectedPerson = list.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showEditDialog(selectedPerson);
            if (okClicked) {
                list.refresh();
                showPerson(selectedPerson);
            }
        } else {
            System.out.println("Nichts ausgewählt");
        }
    }

    //Methode um dialogfenster aufzurufen
    @FXML
    public void handleNew() {
        Person newPerson = new Person();
        boolean okClicked = showEditDialog(newPerson);
        if (okClicked) {
            items.add(newPerson);
            list.refresh();
        } else {
            System.out.println("neue Person konnte nich erstellt werden");
        }
    }
    //Anzeigen der personen bei klick
    public void showPerson(Person aPerson) {
        if(aPerson!=null) {
            na.setText(aPerson.getName());
            ps.setText(aPerson.getZipcode());
            st.setText(aPerson.getStreet());
            nr.setText(String.valueOf(aPerson.getNumber()));
            ort.setText(aPerson.getCity());
            vo.setText(aPerson.getFirstName());
        }
    }

    //dialog fenster
    public boolean showEditDialog(Person aPerson) {
        try {
            load = new FXMLLoader(getClass().getResource("editDialog.fxml"));
            Parent root = load.load();
            this.dialog = load.getController();

            this.dialogStage = new Stage();
            dialogStage.setTitle("Berarbeiten");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainStage);
            dialogStage.setScene(new Scene(root, 500, 475));

            //übergabe der person und stage

            dialog.setPerson(aPerson);
            dialog.setDialogStage(dialogStage);

            dialogStage.showAndWait();


            return dialog.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void handleSave() {
        try {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("C:/Users/Public"));
            File file = fc.showSaveDialog(mainStage);
            if(file!=null) {
                ObjectOutputStream save = new ObjectOutputStream((new FileOutputStream(file)));
                items.forEach((person) -> {
                    safeData.add(person);
                });
                save.writeObject(safeData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handleLoad() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:/Users/Public"));
        File file = fc.showOpenDialog(mainStage);
        if(file !=null) {
            try {
                try {
                    ObjectInputStream load = new ObjectInputStream(new FileInputStream(file));
                    safeData = (ArrayList) load.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(safeData!=null) {
            items.remove(0,items.size());
            safeData.forEach((person) -> {
                items.add(person);
            });
        }

    }

}




