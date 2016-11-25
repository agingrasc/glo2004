package org.glo.giftw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.glo.giftw.controller.Controller;
import org.glo.giftw.view.FXMLPaths;
import org.glo.giftw.view.RootLayoutController;

import java.io.IOException;

public class MainApp extends Application
{
	private Stage primaryStage;
	public static final String TITLE = "VisuaLigue";

	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(TITLE);
		this.primaryStage.setMaximized(true);

		initRootLayout();
	}

	public void initRootLayout()
	{
		try
		{
			Controller.getInstance();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(FXMLPaths.ROOT_LAYOUT_PATH.toString()));
			loader.setController(RootLayoutController.getInstance());
			BorderPane rootLayout = loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
