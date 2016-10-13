package org.glo.giftw.view;

import org.glo.giftw.MainApp;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class CreationGUIController
{

	// Reference to the main application.
	private MainApp mainApp;

	@FXML
	void mouseClickedDelete(MouseEvent event)
	{
		System.out.println("mouseClickedDelete");
	}

	@FXML
	void mouseClickedNext(MouseEvent event)
	{
		System.out.println("mouseClickedNext");
	}

	@FXML
	void mouseClickedPrevious(MouseEvent event)
	{
		System.out.println("mouseClickedPrevious");
	}

	@FXML
	void mouseClickedRedo(MouseEvent event)
	{
		System.out.println("mouseClickedRedo");
	}

	@FXML
	void mouseClickedUndo(MouseEvent event)
	{
		System.out.println("mouseClickedUndo");
	}

	@FXML
	void mouseClickedZoomIn(MouseEvent event)
	{
		System.out.println("mouseClickedZoomIn");
	}

	@FXML
	void mouseClickedZoomOut(MouseEvent event)
	{
		System.out.println("mouseClickedZoomOut");
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
}
