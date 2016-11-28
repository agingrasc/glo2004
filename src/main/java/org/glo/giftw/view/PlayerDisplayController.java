package org.glo.giftw.view;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

public class PlayerDisplayController
{
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
}
