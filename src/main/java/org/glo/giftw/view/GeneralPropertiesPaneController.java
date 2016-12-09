package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.MaxNumberException;

import java.io.IOException;

public class GeneralPropertiesPaneController
{
    @FXML
    private Accordion rootAccordion;
    
    @FXML
    private TitledPane generalPropertiesTitledPane;

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
    public void initialize()
    {
    	rootAccordion.setExpandedPane(generalPropertiesTitledPane);
    	showRolesCheckBox.setSelected(false);
    	showNamesCheckBox.setSelected(false);
    }

    @FXML
    void onActionAddTeam(ActionEvent event) throws IOException
    {
        try
        {
            Color color = colorPicker.getValue();
            String hex = String.format("#%02X%02X%02X",
                                       (int) (color.getRed() * 255),
                                       (int) (color.getGreen() * 255),
                                       (int) (color.getBlue() * 255));
            Controller.getInstance().addTeam(teamNameTextField.getText(), hex);
            RootLayoutController.getInstance().getItemsAccordionController().updateTeamsTable();
        }
        catch (MaxNumberException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void onShowNames(ActionEvent event)
    {
    	try
		{
			RootLayoutController.getInstance().getCreationStackPaneController().setDisplayNames(showNamesCheckBox.isSelected());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void onShowRoles(ActionEvent event)
    {
    	try
		{
			RootLayoutController.getInstance().getCreationStackPaneController().setDisplayRoles(showRolesCheckBox.isSelected());
		} catch (IOException e)
		{
			e.printStackTrace();
		}    
    }

    public Accordion getRootAccordion()
    {
        return rootAccordion;
    }
}
