import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/*Frank Pena
COMP-271-001RL
Class lab 21*/

public class SQLConsoleDemo extends Application {
	SQLConsolePane pane = new SQLConsolePane();
	
		@Override // Override the start method in the Application class
		public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {


/////////////////////////////////////////////////////////////SQL////////////////////////////////////////////////////////////////////////////			
		
		//LOAD JBDC	
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");

		//ESTABLISH CONNECTION
                //MODIFY '(port)/database' & 'user' & 'password' to connect to a specific database 
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:(port)/database", "user", "password");
		System.out.println("Database connected!");

		//CREATE STATEMENT
		Statement statement = connection.createStatement();

/////////////////////////////////////////////////////////////SQL////////////////////////////////////////////////////////////////////////////	
		
		//TEXT AREA DEFAULTS
		pane.textArea.setWrapText(false);
		pane.textArea.setEditable(false);
		pane.textField.setEditable(true);		


		//ACTION EVENT
		pane.btFire.setOnAction(e -> {
			
			
			if(pane.textField.getText().trim().toUpperCase().startsWith("SELECT")) {
				//SELECT
				try {
					selectTable(statement, pane.textField.getText(), pane.textArea);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}else{										
			try {
				insertTable(statement, pane.textField);
				if(pane.textField.getText().trim().toUpperCase().startsWith("INSERT")) {
					System.out.println("INSERT successful");
				}
				
				if(pane.textField.getText().trim().toUpperCase().startsWith("UPDATE")) {
					System.out.println("UPDATE successful");
				}
				
				if(pane.textField.getText().trim().toUpperCase().startsWith("DELETE")) {
					System.out.println("DELETE successful");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
	});//btFire SET ON ACTION EVENT
		
		pane.btClear.setOnAction(e -> {
		
			pane.textField.clear();
			pane.textArea.clear();  
			
    });//btClear SET ON ACTION EVENT
	
	   //SCENE
	   Scene scene = new Scene(pane, 800,770);
	   primaryStage.setTitle("SQL Console");
	   primaryStage.setScene(scene);
	   primaryStage.show();
		
				
}//START STAGE
	
	

	private void selectTable(Statement statement, String t, TextArea textArea) throws SQLException {
		ResultSet resultSet = statement.executeQuery(t);
		
		//CREATE META OBJECT
		ResultSetMetaData rsmd = resultSet.getMetaData();
		
		//PRINT ROW HEADERS
		int columnCount = rsmd.getColumnCount();
		

		
		
		for(int i=1; i<=columnCount; i++) {				
			textArea.appendText(String.format("%-23s", rsmd.getColumnName(i) + "\t"));
		}
		
		while(resultSet.next()) {		
			textArea.appendText("\n");
			for(int i=1; i<=columnCount; i++) {

				textArea.appendText(String.format("%-25s", resultSet.getString(i) + "\t"));
				
				}
			textArea.appendText("\n");
			}
		
		
		
		}//SELECT TABLE
		
	
	private void insertTable(Statement statement, TextField textField) throws SQLException{
		String t = textField.getText();
		
		statement.executeUpdate(t);
		
		}//INSERT / UPDATE / DELETE TABLE

	public static void main(String[] args) {			
		Application.launch(args);
		
	}//MAIN

}//CLASS
