package application;
import java.sql.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.Node;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBUtils {
	public static void changeScene(ActionEvent event, String fxmlFile, String title, String username ,String itemname) {
	    Parent root = null;
	    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
	    try {
	        root = loader.load();
	        if (username != null && itemname == null) {
	        	if(title == "adminmenu!") {
	        		adminmenuController adm = loader.getController();
	        		adm.setusername(username);
	        	}
	        	else if(title == "Add Feedback!") {
	        		addfeedbackController AddfeedbackController = loader.getController();
	        		AddfeedbackController.setusername(username);
	        	}
	        	else if(title=="Welcome!") {
	        		LoggedInController loggedInController = loader.getController();
		            loggedInController.setUserInformation(username);
	        	}
	        	else {
	        		selectmyitemController smiController = loader.getController();
	        		smiController.setusername(username);
	        	}
	            
	        }
	        else if(itemname != null && username == null) {
	        	searchitemresultController sircontroller=loader.getController();
	        	sircontroller.setitemname(itemname);
	        }
	        System.out.println("hello");
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setTitle(title);
	        stage.setScene(new Scene(root, 600, 400));
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void signUpUser(ActionEvent event,String username,String password) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet resultSet = null;
		try {
			String Url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";
            connection = DriverManager.getConnection(Url);
            System.out.println("Connection Successful");
            psCheckUserExists = connection.prepareStatement("Select * From users Where username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();
            
            if(resultSet.isBeforeFirst()) {
            	System.out.println("User already exists!");
            	Alert alert = new Alert(Alert.AlertType.ERROR);
            	alert.show();
            }
            else {
            	psInsert = connection.prepareCall("INSERT INTO users (username, password) VALUES (?, ?)");
            	psInsert.setString(1, username);
            	psInsert.setString(2, password);
            	psInsert.executeUpdate();
            	
            	changeScene(event,"logged-in.fxml","Welcome!",username,null);
            }
		}
		catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
		}
		finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
			if(psCheckUserExists != null){
				try {
					psCheckUserExists.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
			if(psInsert != null) {
				try {
					psInsert.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
		}
	}	
	public static void logInAdmin(ActionEvent event, String username, String password) {
	    System.out.println("LogInUser function");
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	        String Url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";
	        connection = DriverManager.getConnection(Url);
	        System.out.println("Connection Successful");
	        preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
	        preparedStatement.setString(1, username);
	        resultSet = preparedStatement.executeQuery();

	        if (!resultSet.next()) {
	            System.out.println("User not found in the Database");
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Provided credentials are incorrect!");
	            alert.show();
	        } else {
	            String retrievedPassword = resultSet.getString("password");
	            if (retrievedPassword.equals(password)) {
	                changeScene(event, "adminmenu.fxml", "adminmenu!", username,null);
	            } else {
	                System.out.println("Passwords did not match!");
	                Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setContentText("Provided credentials are incorrect!");
	                alert.show();
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("LogInUser function");
	        System.out.println("Connection Failed");
	        e.printStackTrace();
	    } finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
		}
	}
	
	public static void logInUser(ActionEvent event, String username, String password) {
	    System.out.println("LogInUser function");
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	        String Url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";
	        connection = DriverManager.getConnection(Url);
	        System.out.println("Connection Successful");
	        preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
	        preparedStatement.setString(1, username);
	        resultSet = preparedStatement.executeQuery();

	        if (!resultSet.next()) {
	            System.out.println("User not found in the Database");
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText("Provided credentials are incorrect!");
	            alert.show();
	        } else {
	            String retrievedPassword = resultSet.getString("password");
	            if (retrievedPassword.equals(password)) {
	                changeScene(event, "logged-in.fxml", "Welcome!", username,null);
	            } else {
	                System.out.println("Passwords did not match!");
	                Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setContentText("Provided credentials are incorrect!");
	                alert.show();
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("LogInUser function");
	        System.out.println("Connection Failed");
	        e.printStackTrace();
	    } finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {
		            System.out.println("Connection Failed");
		            e.printStackTrace();
				}
			}
		}
	}

}
