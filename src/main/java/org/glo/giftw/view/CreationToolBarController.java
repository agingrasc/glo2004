package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import org.glo.giftw.domain.Controller;

import java.io.IOException;

public class CreationToolBarController
{
    @FXML
    private ToolBar rootToolBar;

    @FXML
    void onActionDelete(ActionEvent event) throws IOException
    {
    	RootLayoutController.getInstance().getCreationStackPaneController().delete();
    }

    @FXML
    void onActionPrevious(ActionEvent event) throws IOException
    {
        boolean isFirstFrame = Controller.getInstance().isFirstFrame();
        if (!isFirstFrame)
        {
            Controller.getInstance().previousKeyFrame();
            RootLayoutController.getInstance().getCreationStackPaneController().resetDisplay();
            RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
        }
    }

    @FXML
    void onActionNext(ActionEvent event) throws IOException
    {
        boolean isLastFrame = Controller.getInstance().isLastFrame();
        if (isLastFrame)
        {
            Controller.getInstance().createNewFrame();
        }
        else
        {
            Controller.getInstance().nextKeyFrame();
        }
        RootLayoutController.getInstance().getCreationStackPaneController().resetDisplay();
        RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
    }

    @FXML
    void onActionRedo(ActionEvent event)
    {
        Controller.getInstance().redo();
        try
        {
            RootLayoutController.getInstance().getCreationStackPaneController().resetDisplay();
            RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionUndo(ActionEvent event)
    {
        Controller.getInstance().undo();
        try
        {
            RootLayoutController.getInstance().getCreationStackPaneController().resetDisplay();
            RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionZoomIn(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().getCreationStackPaneController().zoomIn();
    }

    @FXML
    void onActionZoomOut(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().getCreationStackPaneController().zoomOut();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}