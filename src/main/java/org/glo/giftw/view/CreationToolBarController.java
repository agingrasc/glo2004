package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.Frame;

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
    void onActionPrevious(ActionEvent event)
    {
        boolean isFirstFrame = Controller.getInstance().isFirstFrame();
        if (!isFirstFrame)
        {
            Frame frame = Controller.getInstance().previousFrame();
            //FIXME: appel au controlleur de creation
        }
    }

    @FXML
    void onActionNext(ActionEvent event)
    {
        boolean isLastFrame = Controller.getInstance().isLastFrame();
        if (isLastFrame)
        {
            Frame frame = Controller.getInstance().createNewFrame();
        }
        else
        {
            Frame frame = Controller.getInstance().nextFrame();
        }
        //FIXME: appel au controlleur de creation
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