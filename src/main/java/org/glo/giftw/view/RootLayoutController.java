package org.glo.giftw.view;

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
	
	@FXML
	private ToggleGroup mode;
	
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private HBox hBox;

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
	void onActionNewStrategy(ActionEvent event)throws Exception
	{
		System.out.println("onActionNewStrategy");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/NewStrategy.fxml"));
		DialogPane newStrategy = loader.load();
		Dialog dialog = new Dialog();
		dialog.setDialogPane(newStrategy);
		dialog.showAndWait();

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
	void onActionOpenStrategy(ActionEvent event) throws Exception
	{
		System.out.println("onActionOpenStrategy");
		if(borderPane.getCenter() == null)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/OpenStrategy.fxml"));
			VBox openStrategy = loader.load();
			borderPane.setCenter(openStrategy);
			
			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(getClass().getResource("/fxml/OpenStrategyButtons.fxml"));
			HBox openStrategyButtons = loader2.load();
			hBox.getChildren().add(openStrategyButtons);
		}
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