package org.glo.giftw.view;

import com.sun.javafx.binding.StringFormatter;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.util.Vector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BottomToolBarController
{
	@FXML
	private ToolBar rootToolBar;

	@FXML
    private Label xCoord;

	@FXML
    private Label yCoord;

	public ToolBar getRootToolBar()
	{
		return rootToolBar;
	}

	public BottomToolBarController getController()
    {
        return this;
    }

    public void updateCoordinate(MouseEvent event, Vector ratioPixelToUnit)
    {
        Vector mouseCoordinate = new Vector(event.getX(), event.getY());
        Vector fieldCoordinate = Controller.getInstance().getFieldCoordinate(mouseCoordinate, ratioPixelToUnit);

        if (fieldCoordinate != null)
        {
            String xText = String.format("X: %f", fieldCoordinate.getX());
            String yText = String.format("Y: %f", fieldCoordinate.getY());
            this.xCoord.setText(xText);
            this.yCoord.setText(yText);
        }
        else
        {
            this.xCoord.setText("N/A");
            this.yCoord.setText("N/A");
        }
    }
}
