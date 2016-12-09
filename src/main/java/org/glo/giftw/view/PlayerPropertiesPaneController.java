package org.glo.giftw.view;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.GameObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PlayerPropertiesPaneController
{
    @FXML
    private Accordion rootAccordion;

    @FXML
    private ColorPicker teamColorPicker;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField nameTextField;
    
    private String uuid;
    
    @FXML
    public void initialize()
    {
    	if(this.uuid != null)
    	{
    		GameObject gameObject = null;
    		try
			{
				gameObject = Controller.getInstance().getGameObjectByUUID(uuid);
				
			} catch (GameObjectNotFound e)
			{
				e.printStackTrace();
			}
    		//TODO
    		//Afficher les roles du sport
    		//roleComboBox.getItems().add(Controller.getInstance().);
    		//Afficher le role du joueur selectionner
    		//Afficher le nom du joueur selectionner
    	}
    }
    
    public Accordion getRootAccordion()
    {
        return rootAccordion;
    }

    @FXML
    void onAction(ActionEvent event) 
    {
    	//TODO setter les attributs du joueur et update le creationStackPane
    }

	public void setSelectedUUID(String uuid)
	{
		this.uuid = uuid;
	}
}
