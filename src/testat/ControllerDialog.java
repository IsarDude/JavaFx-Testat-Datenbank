package testat;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;




public class ControllerDialog {
    @FXML
    private TextField name;
    @FXML
    private TextField firstName;
    @FXML
    private TextField adress;
    @FXML
    private TextField number;
    @FXML
    private TextField zip;
    @FXML
    private TextField city;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked=false;

    @FXML
    private void initialize(){

    }


    //Übergabe der person und der stage
    public void setDialogStage(Stage aDialogStage){
        this.dialogStage =aDialogStage;
    }

    public void setPerson(Person aPerson){
        this.person=aPerson;

        name.setText(person.getName());
        firstName.setText(person.getFirstName());
        adress.setText(person.getStreet());
        number.setText(String.valueOf(person.getNumber()));
        zip.setText(person.getZipcode());
        city.setText(person.getCity());
    }
    @FXML
    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    public void handleOk() {
        if (isValid()) {
            person.setName(name.getText());
            person.setFirstName(firstName.getText());
            person.setStreet(adress.getText());
            person.setNumber(Integer.parseInt(number.getText()));
            person.setZipcode(zip.getText());
            person.setCity(city.getText());

            okClicked=true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleCancel(){
        dialogStage.close();
    }


    private boolean isValid(){
        try {
            int temp = Integer.parseInt(number.getText());
        }catch(NumberFormatException e){
            System.out.println("keine gültige eingabe für Hasunummer");
            return false;
        }
        if(name.getText()==null||firstName.getText()==null||adress.getText()==null||number.getText()==null||zip.getText()==null||city.getText()==null){
            System.out.println("keine eingabe für Textfelder");
            return false;
        }
        else{
            System.out.println("valid");
            return true;
        }
    }



}
