package org.glo.giftw.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.util.Vector;

import java.io.IOException;

public class PlayerPropertiesPaneController
{
    @FXML
    private Accordion rootAccordion;

    @FXML
    private TitledPane playerPropertiesTitledPane;

    @FXML
    private ColorPicker teamColorPicker;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private Slider orientationSlider;


    @FXML
    public void initialize()
    {
        String selectedUUID = null;
        try
        {
            selectedUUID = RootLayoutController.getInstance().getCreationStackPaneController().getSelectedUUID();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        rootAccordion.setExpandedPane(playerPropertiesTitledPane);

        if (selectedUUID != null)
        {
            GameObject gameObject = null;
            try
            {
                gameObject = Controller.getInstance().getGameObjectByUUID(selectedUUID);

            }
            catch (GameObjectNotFound e)
            {
                e.printStackTrace();
            }

            //TODO
            //Afficher les roles du sport
            //roleComboBox.getItems().add(Controller.getInstance().);
            nameTextField.setText(gameObject.getName());
            //Afficher le role du joueur select
            //Afficher l'orientation
            initSlider(selectedUUID, gameObject);
        }
    }

    private void initSlider(String uuid, GameObject gameObject)
    {
        orientationSlider.valueProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val)
            {
                Vector position = Controller.getInstance().getPosition(gameObject);
                float orientation = new_val.floatValue();
                System.out.println(orientation);
                try
                {
                    Controller.getInstance().placeGameObject(uuid, position, orientation);
                }
                catch (GameObjectNotFound e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try
                {
                    RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    public Accordion getRootAccordion()
    {
        return rootAccordion;
    }

    @FXML
    void onAction(ActionEvent event)
    {
        String selectedUUID = null;
        try
        {
            selectedUUID = RootLayoutController.getInstance().getCreationStackPaneController().getSelectedUUID();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        GameObject gameObject = null;
        try
        {
            gameObject = Controller.getInstance().getGameObjectByUUID(selectedUUID);

        }
        catch (GameObjectNotFound e)
        {
            e.printStackTrace();
        }
        gameObject.setName(nameTextField.getText());
        //TODO
        //setter le role
        //setter la team?
        //display strategie
    }
}
