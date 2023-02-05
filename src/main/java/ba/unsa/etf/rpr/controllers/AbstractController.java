package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Abstract class which implements usual methods for controller classes
 */
public abstract class AbstractController {
    /**
     * Sets the field invalid (adds red border on the text field and warning text beneath)
     * @param textField
     * @param checkLabel
     * @param errorMessage
     */
    protected void setFieldInvalid(TextField textField, Label checkLabel, String errorMessage) {
        textField.getStyleClass().add("fieldIsInvalid");
        checkLabel.setText(errorMessage);
    }
    /**
     * Sets the field valid (removes red border on the text field and warning text beneath)
     * @param textField
     * @param checkLabel
     */
    protected void setFieldValid(TextField textField, Label checkLabel) {
        textField.getStyleClass().removeAll("fieldIsInvalid");
        checkLabel.setText("");
    }
    /**
     * Closes the window corresponding to actionEvent
     * @param actionEvent
     */
    protected void closeWindow(ActionEvent actionEvent){
        Node node = (Node) actionEvent.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
    }
    /**
     * Checks if the string n matches regex and sets the responding field valid or invalid
     * @param n
     * @param regex
     * @param textField
     * @param checkLabel
     * @param fieldName
     * @param errorMessage
     * @return
     */
    protected boolean matchesRegex(String n,String regex,TextField textField, Label checkLabel,String fieldName,String errorMessage){
        if(!n.matches(regex)) {
            if (n.isEmpty()) {
                setFieldInvalid(textField, checkLabel,  fieldName + " is empty");
            } else {
                setFieldInvalid(textField, checkLabel, errorMessage);
            }
            return false;
        }
        return true;
    }
    /**
     * Adds listener to the specified text field and checks its validity in real time
     * @param textField
     * @param checkLabel
     * @param errorMessage
     * @param fieldName
     * @param regex
     */
    protected void addListenerToField(TextField textField, Label checkLabel, String errorMessage,String fieldName,String regex){
        textField.textProperty().addListener(((observableValue, o, n) -> {
            if(matchesRegex(n,regex,textField,checkLabel,fieldName,errorMessage))
                setFieldValid(textField,checkLabel);
        }));
    }
}
