package org.glo.giftw.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RootLayoutController
{
	private boolean isSubButtons = false;
	
	@FXML
	private ToggleGroup mode;
	
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private HBox buttons;

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
	void onActionNewObstacle(ActionEvent event) throws IOException
	{
		System.out.println("onActionNewObstacle");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/NewObstacle.fxml"));
		DialogPane newObstacle = loader.load();
		Dialog<Object> dialog = new Dialog<Object>();
		dialog.setDialogPane(newObstacle);
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
	void onActionNewStrategy(ActionEvent event)throws Exception
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
		borderPane.setCenter(openObstacle);
		
		if(isSubButtons)
		{
			buttons.getChildren().remove((buttons.getChildren().size())-1);
			isSubButtons = false;
		}
		
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/fxml/OpenObstacleButtons.fxml"));
		HBox openObstacleButtons = loader2.load();
		buttons.getChildren().add(openObstacleButtons);
		
		isSubButtons = true;
	}

	@FXML
	void onActionOpenSport(ActionEvent event) throws IOException
	{
		System.out.println("onActionOpenSport");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/OpenSport.fxml"));
		VBox openSport = loader.load();
		borderPane.setCenter(openSport);
		
		if(isSubButtons)
		{
			buttons.getChildren().remove((buttons.getChildren().size())-1);
			isSubButtons = false;
		}
		
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/fxml/OpenSportButtons.fxml"));
		HBox openSportButtons = loader2.load();
		buttons.getChildren().add(openSportButtons);
		
		isSubButtons = true;
	}

	@FXML
	void onActionOpenStrategy(ActionEvent event) throws Exception
	{
		System.out.println("onActionOpenStrategy");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/OpenStrategy.fxml"));
		VBox openStrategy = loader.load();
		borderPane.setCenter(openStrategy);
		
		if(isSubButtons)
		{
			buttons.getChildren().remove((buttons.getChildren().size())-1);
			isSubButtons = false;
		}
		
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/fxml/OpenStrategyButtons.fxml"));
		HBox openStrategyButtons = loader2.load();
		buttons.getChildren().add(openStrategyButtons);
		
		isSubButtons = true;
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
	void onActionVisualize(ActionEvent event)
	{
		System.out.println("onActionVisualize");
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