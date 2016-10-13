package org.glo.giftw;

import java.io.IOException;

import org.glo.giftw.view.CreationGUIController;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainApp extends Application
{

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("VisuaLigue");

		initRootLayout();

		showCreationGUI();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout()
	{
		try
		{
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/fxml/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Shows the CreationGUI inside the root layout.
	 */
	public void showCreationGUI()
	{

		try
		{
			// Load CreationGui.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/fxml/CreationGUI.fxml"));
			BorderPane creationGUI = (BorderPane) loader.load();

			// Set CreationGUI into the center of root layout.
			rootLayout.setCenter(creationGUI);

			// Give the controller access to the main app.
			CreationGUIController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
