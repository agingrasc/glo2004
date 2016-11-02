package org.glo.giftw.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class RootLayoutController
{
	private int nbToolsAdded;
	
	private ViewController viewController;
	
	@FXML
	private ToggleGroup mode;
	
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private ToolBar rootToolBar;

	@FXML
    private void initialize() throws IOException
	{	
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/BaseToolBar.fxml"));
		ToolBar baseToolBar = loader.load();
		BaseToolBarController baseToolBarController = loader.getController();
		baseToolBarController.setParentController(this);
    	
    	FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/fxml/OpenStrategy.fxml"));
		VBox openStrategy = loader2.load();
		
		FXMLLoader loader3 = new FXMLLoader();
		loader3.setLocation(getClass().getResource("/fxml/OpenStrategyToolBar.fxml"));
		ToolBar openStrategyToolBar = loader3.load();
		
		borderPane.setCenter(openStrategy);
		rootToolBar.getItems().addAll(baseToolBar.getItems());
		rootToolBar.getItems().addAll(openStrategyToolBar.getItems());
		
		nbToolsAdded = openStrategyToolBar.getItems().size();
	}
	
	public int getNbToolsAdded()
	{
		return nbToolsAdded;
	}

	public void setNbToolsAdded(int nbToolsAdded)
	{
		this.nbToolsAdded = nbToolsAdded;
	}

	public BorderPane getBorderPane()
	{
		return borderPane;
	}

	public ToolBar getRootToolBar()
	{
		return rootToolBar;
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

	public ViewController getViewController()
	{
		return viewController;
	}

	public void setViewController(ViewController viewController)
	{
		this.viewController = viewController;
	}
}