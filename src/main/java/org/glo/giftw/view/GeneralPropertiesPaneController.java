package org.glo.giftw.view;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.MaxNumberException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class GeneralPropertiesPaneController
{
	@FXML
    private Accordion rootAccordion;

    @FXML
    private CheckBox showRolesCheckBox;

    @FXML
    private CheckBox showNamesCheckBox;

    @FXML
    private Button addTeamButton;

    @FXML
    private ColorPicker colorPicker;
    
    @FXML
    private TextField teamNameTextField;

    @FXML
    void onActionAddTeam(ActionEvent event) 
    {
    	try
		{
    		Color color = colorPicker.getValue();
    		String hex = String.format( "#%02X%02X%02X",
    	            (int)( color.getRed() * 255 ),
    	            (int)( color.getGreen() * 255 ),
    	            (int)( color.getBlue() * 255 ) );
			Controller.getInstance().addTeam(teamNameTextField.getText(),hex);
			RootLayoutController.getInstance().getItemsAccordionController().updateTeamsTable();
		} catch (MaxNumberException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void onActionColorPicker(ActionEvent event) {

    }

    @FXML
    void onShowNames(ActionEvent event) {

    }

    @FXML
    void onShowRoles(ActionEvent event) {

    }

	public Accordion getRootAccordion()
	{
		return rootAccordion;
	}
}
