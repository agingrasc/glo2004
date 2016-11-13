package org.glo.giftw.view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class RootLayoutController implements Observer 
{
	@FXML
	private ToggleGroup mode;

	@FXML
	private BorderPane borderPane;

	@FXML
	private ToolBar rootToolBar;

	@FXML
	private void initialize() throws IOException
	{
		openStrategy();
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		try
		{
			switch ((String) arg1)
			{
			case "onActionOpenObstacle":
				openObstacle();
				break;

			case "onActionOpenSport":
				openSport();
				break;

			case "onActionOpenStrategy":
				openStrategy();
				break;

			case "onActionNewObstacle":
				newObstacle();
				break;

			case "onActionNewSport":
				newSport();
				break;

			case "onActionNewStrategy":
				newStrategy();
				break;

			case "onActionWatch":
				watch();
				break;

			case "onActionImageByImage":
				imageByImage();
				break;

			case "onActionRealTime":
				realTime();
				break;

			case "onActionConfigureSport":
				configureSport();
				break;

			case "onActionConfigureObstacle":
				configureObstacle();
				break;

			case "onActionConfigureStrategy":
				configureStrategy();
				break;
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void openObstacle() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/DefaultToolBar.fxml", true);
		addToolBar("/fxml/OpenObstacleToolBar.fxml", true);
		borderPane.setLeft(null);
		changeCenter("/fxml/OpenObstacle.fxml");
		borderPane.setBottom(null);
	}

	private void openSport() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/DefaultToolBar.fxml", true);
		addToolBar("/fxml/OpenSportToolBar.fxml", true);
		borderPane.setLeft(null);
		changeCenter("/fxml/OpenSport.fxml");
		borderPane.setBottom(null);
	}

	private void openStrategy() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/DefaultToolBar.fxml", true);
		addToolBar("/fxml/ModeToolBar.fxml", true);
		addToolBar("/fxml/OpenStrategyToolBar.fxml", true);
		borderPane.setLeft(null);
		changeCenter("/fxml/OpenStrategy.fxml");
		borderPane.setBottom(null);
	}

	private void newObstacle() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/NewObstacle.fxml"));
		DialogPane dialogPane = loader.load();
		NewObstacleController newObstacleController = loader.getController();
		newObstacleController.showDialog(dialogPane);
	}

	private void newSport() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/NewSport.fxml"));
		DialogPane dialogPane = loader.load();
		NewSportController newSportController = loader.getController();
		newSportController.showDialog(dialogPane);
	}

	private void newStrategy() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/NewStrategy.fxml"));
		DialogPane dialogPane = loader.load();
		NewStrategyController newStrategyController = loader.getController();
		newStrategyController.showDialog(dialogPane);
	}

	private void watch() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/DefaultToolBar.fxml", true);
		addToolBar("/fxml/ModeToolBar.fxml", true);
		addToolBar("/fxml/MediaToolBar.fxml", false);
		borderPane.setLeft(null);
		changeCenter("/fxml/MediaContent.fxml");
		changeBottom("/fxml/BottomBar.fxml");
	}

	private void imageByImage() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/DefaultToolBar.fxml", true);
		addToolBar("/fxml/ModeToolBar.fxml", true);
		addToolBar("/fxml/CreationToolBar.fxml", false);
		changeLeft("/fxml/ItemsAccordion.fxml");
		changeCenter("/fxml/CreationStackPane.fxml");
		changeBottom("/fxml/BottomBar.fxml");
	}

	private void realTime() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/DefaultToolBar.fxml", true);
		addToolBar("/fxml/ModeToolBar.fxml", true);
		addToolBar("/fxml/CreationToolBar.fxml", false);
		changeLeft("/fxml/ItemsAccordion.fxml");
		changeCenter("/fxml/CreationStackPane.fxml");
		changeBottom("/fxml/BottomBar.fxml");
	}

	private void configureSport() throws IOException
	{
		newSport();
	}

	private void configureObstacle() throws IOException
	{
		newObstacle();
	}

	private void configureStrategy() throws IOException
	{
		newStrategy();
	}

	private void addToolBar(String url, boolean obs) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(url));
		ToolBar toolBar = loader.load();
		if(obs)
		{	
			((Observable)loader.getController()).addObserver(this);
		}	
		rootToolBar.getItems().addAll(toolBar.getItems());
	}

	private void changeCenter(String url) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(url));
		Node node = loader.load();
		
		borderPane.setCenter(node);
	}

	private void changeLeft(String url) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(url));
		Node node = loader.load();
		
		borderPane.setLeft(node);
	}

	private void changeBottom(String url) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(url));
		Node node = loader.load();
		
		borderPane.setBottom(node);
	}

	@FXML
	void onActionDelete(ActionEvent event)
	{
		System.out.println("onActionDelete");
	}

	@FXML
	void onActionExportJPEG(ActionEvent event)
	{
		System.out.println("onActionExportJPEG");
	}

	@FXML
	void onActionExportPNG(ActionEvent event)
	{
		System.out.println("onActionExportPNG");
	}

	@FXML
	void onActionImageByImage(ActionEvent event)
	{
		System.out.println("onActionImageByImage");
	}

	@FXML
	void onActionInsertObstacle(ActionEvent event)
	{
		System.out.println("onActionInsertObstacle");
	}

	@FXML
	void onActionInsertPlayer(ActionEvent event)
	{
		System.out.println("onActionInsertPlayer");
	}

	@FXML
	void onActionInsertProjectile(ActionEvent event)
	{
		System.out.println("onActionInsertProjectile");
	}

	@FXML
	void onActionNewObstacle(ActionEvent event)
	{
		System.out.println("onActionNewObstacle");
	}

	@FXML
	void onActionNewSport(ActionEvent event)
	{
		System.out.println("onActionNewSport");
	}

	@FXML
	void onActionNewStrategy(ActionEvent event)
	{
		System.out.println("onActionNewStrategy");
	}

	@FXML
	void onActionOpenObstacle(ActionEvent event)
	{
		System.out.println("onActionOpenObstacle");
	}

	@FXML
	void onActionOpenSport(ActionEvent event)
	{
		System.out.println("onActionOpenSport");
	}

	@FXML
	void onActionOpenStrategy(ActionEvent event)
	{
		System.out.println("onActionOpenStrategy");
	}

	@FXML
	void onActionRealTime(ActionEvent event)
	{
		System.out.println("onActionRealTime");
	}

	@FXML
	void onActionRedo(ActionEvent event)
	{
		System.out.println("onActionRedo");
	}

	@FXML
	void onActionSave(ActionEvent event)
	{
		System.out.println("onActionSave");
	}

	@FXML
	void onActionSaveAs(ActionEvent event)
	{
		System.out.println("onActionSaveAs");
	}

	@FXML
	void onActionShowNames(ActionEvent event)
	{
		System.out.println("onActionShowNames");
	}

	@FXML
	void onActionShowRoles(ActionEvent event)
	{
		System.out.println("onActionShowRoles");
	}

	@FXML
	void onActionUndo(ActionEvent event)
	{
		System.out.println("onActionUndo");
	}

	@FXML
	void onActionWatch(ActionEvent event)
	{
		System.out.println("onActionWatch");
	}

	@FXML
	void onActionZoomIn(ActionEvent event)
	{
		System.out.println("onActionZoomIn");
	}

	@FXML
	void onActionZoomOut(ActionEvent event)
	{
		System.out.println("onActionZoomOut");
	}
}