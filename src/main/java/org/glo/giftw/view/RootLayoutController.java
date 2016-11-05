package org.glo.giftw.view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
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
			switch((String) arg1)
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
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void openObstacle() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/BaseToolBar.fxml", true);
		addToolBar("/fxml/OpenObstacleToolBar.fxml", true);
		borderPane.setLeft(null);
		changeCenter("/fxml/OpenObstacle.fxml");
	}
	
	private void openSport() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/BaseToolBar.fxml", true);
		addToolBar("/fxml/OpenSportToolBar.fxml", true);
		borderPane.setLeft(null);
		changeCenter("/fxml/OpenSport.fxml");
	}
	
	private void openStrategy() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/BaseToolBar.fxml", true);
		addToolBar("/fxml/ModeToolBar.fxml", true);
		addToolBar("/fxml/OpenStrategyToolBar.fxml", true);
		borderPane.setLeft(null);
		changeCenter("/fxml/OpenStrategy.fxml");
	}
	
	private void newObstacle() throws IOException
	{
		showDialog("/fxml/NewObstacle.fxml");
	}
	
	private void newSport() throws IOException
	{	
		showDialog("/fxml/NewSport.fxml");
	}
	
	private void newStrategy() throws IOException
	{
		showDialog("/fxml/NewStrategy.fxml");
	}
	
	private void watch() throws IOException
	{	
		rootToolBar.getItems().clear();
		addToolBar("/fxml/BaseToolBar.fxml", true);
		addToolBar("/fxml/ModeToolBar.fxml", true);
		addToolBar("/fxml/MediaToolBar.fxml", false);
		borderPane.setLeft(null);
		changeCenter("/fxml/MediaContent.fxml");
	}
	
	private void imageByImage() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/BaseToolBar.fxml", true);
		addToolBar("/fxml/ModeToolBar.fxml", true);
		addToolBar("/fxml/CreationToolBar.fxml", false);
		changeLeft("/fxml/ItemsAccordion.fxml");
		changeCenter("/fxml/CreationStackPane.fxml");
	}
	
	private void realTime() throws IOException
	{
		rootToolBar.getItems().clear();
		addToolBar("/fxml/BaseToolBar.fxml", true);
		addToolBar("/fxml/ModeToolBar.fxml", true);
		addToolBar("/fxml/CreationToolBar.fxml", false);
		changeLeft("/fxml/ItemsAccordion.fxml");
		changeCenter("/fxml/CreationStackPane.fxml");
	}
	
	private void configureSport() throws IOException
	{
		showDialog("/fxml/NewSport.fxml");
	}
	
	private void configureObstacle() throws IOException
	{
		showDialog("/fxml/NewObstacle.fxml");
	}
	
	private void configureStrategy() throws IOException
	{
		showDialog("/fxml/NewStrategy.fxml");
	}
	
	private void showDialog(String url) throws IOException
	{
		DialogPane dialogPane = (DialogPane) loadNode(url, false);
		Dialog<Object> dialog = new Dialog<Object>();
		dialog.setDialogPane(dialogPane);
		dialog.showAndWait();
	}
	
	private void addToolBar(String url, boolean obs) throws IOException
	{
		ToolBar toolBar = (ToolBar) loadNode(url, obs);
		rootToolBar.getItems().addAll(toolBar.getItems());

		//toolBarArray.add(toolBar.getItems().size());
	}
	
	/*private void removeToolBar(int index)
	{
		int fromIndex = 0;
		int toIndex = 0;
		
		for(int i = 0; i < index; i++)
		{
			fromIndex += toolBarArray.get(i);
		}
		
		toIndex = fromIndex + toolBarArray.get(index);
				
		rootToolBar.getItems().remove(fromIndex, toIndex);
		toolBarArray.remove(index);
	}*/
	
	private void changeCenter(String url) throws IOException
	{
		Node node = loadNode(url, false);
		borderPane.setCenter(node);
	}
	
	private void changeLeft(String url) throws IOException
	{
		Node node = loadNode(url, false);
		borderPane.setLeft(node);
	}
	
	private Node loadNode(String url, boolean obs) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(url));
		Node node = loader.load();
		if(obs)
		{
			((Observable) loader.getController()).addObserver(this);
		}
		return node;
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