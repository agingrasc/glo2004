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
    void onActionDelete(ActionEvent event)
    {
        System.out.println("onActionDelete");
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
        System.out.println("onActionRedo");
    }

    @FXML
    void onActionUndo(ActionEvent event)
    {
        System.out.println("onActionUndo");
    }

    @FXML
    void onActionZoomIn(ActionEvent event) throws IOException
    {
        System.out.println("onActionZoomIn");
        RootLayoutController.getInstance().getCreationStackPaneController().zoomIn();
    }

    @FXML
    void onActionZoomOut(ActionEvent event) throws IOException
    {
        System.out.println("onActionZoomOut");
        RootLayoutController.getInstance().getCreationStackPaneController().zoomOut();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}