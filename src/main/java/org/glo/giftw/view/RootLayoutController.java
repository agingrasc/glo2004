package org.glo.giftw.view;

import org.glo.giftw.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class RootLayoutController
{

	@FXML
	private ToggleGroup mode;
	private MainApp mainApp;

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

	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
}