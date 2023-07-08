package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private TextField inputText;
    @FXML
    private Button searchButton;
    @FXML
    private CheckBox rawInfo;


    @FXML
    private TextArea wedrTextArea;

    WedrData wedrData = new WedrData();

    @FXML
    public void initialize(){
        searchButton.setDisable(true);
    }

    @FXML
    public void onButtonClicked(ActionEvent e){
        if (e.getSource().equals(searchButton)) {
            if (rawInfo.isSelected()) {
                wedrData.setInput(inputText.getText());
                wedrData.wedrInit();
                wedrData.setRawInfo();
                wedrTextArea.setText(wedrData.getRawInfo());
            } else {
                wedrData.setInput(inputText.getText());
                wedrData.wedrInit();
                wedrTextArea.setText(wedrData.compact());
            }
        }
    }

    @FXML
    public void handleKeyReleased(){
        String text = inputText.getText();
        boolean emptyField = text.isEmpty() || text.trim().isEmpty();
        searchButton.setDisable(emptyField);

    }


//    @FXML
//    public void handleChange(){
//            if (rawInfo.isSelected()){
//                wedrTextArea.setText(wedrData.getRawInfo());
//            }
//        }
}
