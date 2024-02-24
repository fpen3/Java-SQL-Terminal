import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/*Frank Pena
COMP-271-001RL
Class lab 21*/

public class SQLConsolePane extends BorderPane{

	protected TextArea textArea = new TextArea();
	protected TextField textField = new TextField();

	//BUTTONS
	Button btFire = new Button("Fire!");
	Button btClear = new Button("Clear");

	//HBOX
	HBox paneForTextField = new HBox(5);
	HBox paneForButtons = new HBox(5);

	//SCROLL PANE
	ScrollPane scrollPane = new ScrollPane(textArea);

	public SQLConsolePane() {
		drawSQLConsole();
	}

	private void drawSQLConsole() {
		textField.setPrefColumnCount(55);
		
		textArea.setPrefSize(799, 450);
	
		//PLACEMENT
		paneForTextField.getChildren().addAll(new Label("SQL Statement: "), textField);
		paneForTextField.setPadding(new Insets(40, 10, 10, 10));
	
		
		paneForButtons.getChildren().addAll(btFire, btClear);
		paneForButtons.setAlignment(Pos.CENTER);
		paneForButtons.setPadding(new Insets(5, 0, 5, 0));

	
		setTop(paneForButtons);
		setCenter(scrollPane);
		setBottom(paneForTextField);
	}

}
