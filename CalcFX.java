import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalcFX extends Application {

  TextField txtInfix;
  Button btnCalc, btnClear;
  Label lblPostfix, lblAnswer;

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Make the controls
    txtInfix=new TextField("Enter Infix Notation");
    btnCalc=new Button("Calculate");
    btnClear=new Button("Clear");
    lblPostfix=new Label("Postfix Notation");
    lblAnswer = new Label("Answer");
    // Center text in label
    txtInfix.setAlignment(Pos.CENTER);
    lblPostfix.setAlignment(Pos.CENTER);
    lblAnswer.setAlignment(Pos.CENTER);
    // Apply ccs-like style to label
    lblPostfix.setStyle("-fx-border-color: #000; -fx-padding: 5px;");
    lblAnswer.setStyle("-fx-border-color: #000; -fx-padding: 5px;");


    // Make container for app
    VBox root = new VBox();
    // Put container in middle of scene
    root.setAlignment(Pos.CENTER);
    // Spacing between items
    root.setSpacing(15);
    // Add to VBox
    root.getChildren().add(txtInfix);
    root.getChildren().add(lblPostfix);
    root.getChildren().add(lblAnswer);
    root.getChildren().add(btnCalc);
    root.getChildren().add(btnClear);
    // Set widths
    setWidths();
    //attach buttons to code in separate method
    attachCode();
    // Set the scene
    Scene scene = new Scene(root, 350, 250);
    primaryStage.setTitle("Iuliia Buniak");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

    public void setWidths(){
    // Set widths of all controls
    txtInfix.setMaxWidth(250);
    lblPostfix.setMaxWidth(250);
    lblAnswer.setMaxWidth(100);
    btnCalc.setPrefWidth(100);
    btnClear.setPrefWidth(100);
  }
   
  public void attachCode(){
    // Attach actions to buttons
    btnCalc.setOnAction(e -> btnCalcCode(e));
    btnClear.setOnAction(e -> btnClearCode(e));
  }
  
  // Button takes the infix expression entered, 
  // coverts to postfix and displays in the Postfix Notation Label 
  // then evaluates the postfix expression and puts the value in the Answer Label
  public void btnCalcCode(ActionEvent e){
    if ( txtInfix.getText().equals("Enter Infix Notation") ) {
      lblPostfix.setText("ERROR!");
      lblAnswer.setText("ERROR!");
    }
    else {
      Calculator c = new Calculator();
      String postfix = c.infixToPostfix(txtInfix.getText());
      int answer = c.evaluatePostfix(postfix);
      lblPostfix.setText(postfix);
      lblAnswer.setText (Integer.toString(answer));
      
      
    }  
  }
  
  // Button sets all the fields back to their initial values
  public void btnClearCode(ActionEvent e) {
    txtInfix.setText("Enter Infix notation");
    txtInfix.requestFocus();
    lblPostfix.setText("Postfix Notation");
    lblAnswer.setText("Answer");  
  }

  public static void main(String[] args) {
    launch(args);
  }
}
