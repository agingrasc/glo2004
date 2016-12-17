package org.glo.giftw.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.strategy.Player;

import java.io.IOException;

public class PlayerPropertiesPaneController
{
    @FXML
    private Accordion rootAccordion;

    @FXML
    private TitledPane playerPropertiesTitledPane;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private Slider orientationSlider;
    
    @FXML
    private CheckBox takeProjectileCheckBox;

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

            roleComboBox.getItems().addAll(Controller.getInstance().getStrategyRoles());
            nameTextField.setText(gameObject.getName());
            roleComboBox.setValue(((Player)gameObject).getRole());
            initSlider(selectedUUID, gameObject);
        }
    }

    private void initSlider(String uuid, GameObject gameObject)
    {
    	orientationSlider.setValue(Controller.getInstance().getOrientation(gameObject));
        orientationSlider.valueProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val)
            {
                float orientation = new_val.floatValue();
                try
                {
                    Controller.getInstance().placeGameObject(uuid, orientation);
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
    
    @FXML
    void onTakeProjectile(ActionEvent event) 
    {
    	
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
        ((Player)gameObject).setRole(roleComboBox.getValue());
        try
		{
			RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
