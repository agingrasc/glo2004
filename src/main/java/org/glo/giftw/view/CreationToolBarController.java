package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class CreationToolBarController
{
	@FXML
	private ToolBar rootToolBar;
	
	@FXML
	void onActionDelete(ActionEvent event)
	{
		System.out.println("onActionDelete");
	}

	@FXML
	void onActionPrevious(ActionEvent event)
	{
		System.out.println("onActionPrevious");
	}

	@FXML
	void onActionNext(ActionEvent event)
	{
		System.out.println("onActionNext");
	}

	@FXML
	void onActionRedo(ActionEvent event)
	{
		System.out.println("onActionRedo");
	}

	@FXML
	void onActionUndo(ActionEvent event)
	{
		System.out.println("onActionUndo");
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

	public ToolBar getRootToolBar()
	{
		return rootToolBar;
	}
}