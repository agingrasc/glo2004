package org.glo.giftw.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.util.Vector;

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

    public void updateCoordinate(Vector mouseCoordinate, Vector ratioPixelToUnit)
    {
        Vector fieldCoordinate = Controller.getInstance().getFieldCoordinate(mouseCoordinate);

        if (fieldCoordinate != null)
        {
            String xText = String.format("X: %.2f", fieldCoordinate.getX());
            String yText = String.format("Y: %.2f", fieldCoordinate.getY());
            this.xCoord.setText(xText);
            this.yCoord.setText(yText);
        }
    }
}
