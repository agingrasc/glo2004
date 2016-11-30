package org.glo.giftw.view;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerDisplayController
{	
	@FXML
	private VBox vbox;
	
	 @FXML
    private Canvas canvas;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

	public Canvas getCanvas()
	{
		return canvas;
	}

	public Label getNameLabel()
	{
		return nameLabel;
	}

	public Label getRoleLabel()
	{
		return roleLabel;
	}

	public VBox getVbox()
	{
		return vbox;
	}
}
