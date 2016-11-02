package org.glo.giftw.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

public class BaseToolBarController
{	
	private RootLayoutController parentController;
	
	public void setParentController(RootLayoutController rootLayoutController)
	{
		this.parentController = rootLayoutController;
	}
	
	@FXML
	void onActionNewObstacle(ActionEvent event) throws IOException
	{
		System.out.println("onActionNewObstacle");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/NewObstacle.fxml"));
		DialogPane newSport = loader.load();
		Dialog<Object> dialog = new Dialog<Object>();
		dialog.setDialogPane(newSport);
		dialog.showAndWait();
	}

	@FXML
	void onActionNewSport(ActionEvent event) throws IOException
	{
		System.out.println("onActionNewSport");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/NewSport.fxml"));
		DialogPane newSport = loader.load();
		Dialog<Object> dialog = new Dialog<Object>();
		dialog.setDialogPane(newSport);
		dialog.showAndWait();
	}

	@FXML
	void onActionNewStrategy(ActionEvent event) throws IOException
	{
		System.out.println("onActionNewStrategy");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/NewStrategy.fxml"));
		DialogPane newStrategy = loader.load();
		Dialog<Object> dialog = new Dialog<Object>();
		dialog.setDialogPane(newStrategy);
		dialog.showAndWait();
	}

	@FXML
	void onActionOpenObstacle(ActionEvent event) throws IOException
	{
		System.out.println("onActionOpenObstacle");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/OpenObstacle.fxml"));
		VBox openObstacle = loader.load();
		
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/fxml/OpenObstacleToolBar.fxml"));
		ToolBar openObstacleToolBar = loader2.load();
		
		parentController.getBorderPane().setCenter(openObstacle);
		parentController.getRootToolBar().getItems().remove(parentController.getRootToolBar().getItems().size()-parentController.getNbToolsAdded(),parentController.getRootToolBar().getItems().size());
		parentController.getRootToolBar().getItems().addAll(openObstacleToolBar.getItems());
		
		parentController.setNbToolsAdded(openObstacleToolBar.getItems().size());
	}

	@FXML
	void onActionOpenSport(ActionEvent event) throws IOException
	{
		System.out.println("onActionOpenSport");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/OpenSport.fxml"));
		VBox openSport = loader.load();
		
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/fxml/OpenSportToolBar.fxml"));
		ToolBar openSportToolBar = loader2.load();
		
		parentController.getBorderPane().setCenter(openSport);
		parentController.getRootToolBar().getItems().remove(parentController.getRootToolBar().getItems().size()-parentController.getNbToolsAdded(),parentController.getRootToolBar().getItems().size());
		parentController.getRootToolBar().getItems().addAll(openSportToolBar.getItems());
		
		parentController.setNbToolsAdded(openSportToolBar.getItems().size());
	}

	@FXML
	void onActionOpenStrategy(ActionEvent event) throws IOException
	{
		System.out.println("onActionOpenStrategy");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/OpenStrategy.fxml"));
		VBox openStrategy = loader.load();
		
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/fxml/OpenStrategyToolBar.fxml"));
		ToolBar openStrategyToolBar = loader2.load();
		
		parentController.getBorderPane().setCenter(openStrategy);
		parentController.getRootToolBar().getItems().remove(parentController.getRootToolBar().getItems().size()-parentController.getNbToolsAdded(),parentController.getRootToolBar().getItems().size());
		parentController.getRootToolBar().getItems().addAll(openStrategyToolBar.getItems());
		
		parentController.setNbToolsAdded(openStrategyToolBar.getItems().size());
	}

	@FXML
	void onActionSave(ActionEvent event)
	{
		System.out.println("onActionSave");
	}
}
