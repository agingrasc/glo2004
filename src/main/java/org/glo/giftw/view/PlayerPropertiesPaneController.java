package org.glo.giftw.view;

import java.io.IOException;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.GameObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

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
    public void initialize()
    {
    	String selectedUUID = null;
		try
		{
			selectedUUID = RootLayoutController.getInstance().getCreationStackPaneController().getSelectedUUID();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
    	rootAccordion.setExpandedPane(playerPropertiesTitledPane);
    	
    	if(selectedUUID != null)
    	{
    		GameObject gameObject = null;
    		try
			{
				gameObject = Controller.getInstance().getGameObjectByUUID(selectedUUID);
				
			} catch (GameObjectNotFound e)
			{
				e.printStackTrace();
			}
    		
    		//TODO
    		//Afficher les roles du sport
    		//roleComboBox.getItems().add(Controller.getInstance().);
    		System.out.println("setting text to" + gameObject.getName());
    		nameTextField.setText(gameObject.getName());
    		//Afficher le role du joueur select
    	}
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
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
    	GameObject gameObject = null;
		try
		{
			gameObject = Controller.getInstance().getGameObjectByUUID(selectedUUID);
			
		} catch (GameObjectNotFound e)
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
