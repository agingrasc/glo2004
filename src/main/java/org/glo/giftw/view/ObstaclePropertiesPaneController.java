package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;

public class ObstaclePropertiesPaneController
{
    @FXML
    private Accordion rootAccordion;

    public Accordion getRootAccordion()
    {
        return rootAccordion;
    }

    @FXML
    void onActionConfigure(ActionEvent event)
    {
        System.out.println("onActionConfigure");
    }
}
