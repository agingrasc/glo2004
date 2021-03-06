package org.glo.giftw.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.Sport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class NewStrategyController
{
    @FXML
    private DialogPane rootDialogPane;

    @FXML
    private TextField strategyName;

    @FXML
    private ComboBox<String> sportsComboBox;

    @FXML
    private CheckBox maxPlayers;

    @FXML
    private CheckBox maxTeams;

    @FXML
    private void initialize() throws IOException
    {
        ArrayList<Sport> sports = new ArrayList<Sport>(Controller.getInstance().getSports());
        for (Sport sport : sports)
        {
            sportsComboBox.getItems().add(sport.getName());
        }
    }

    public void showDialog() throws IOException
    {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setDialogPane(rootDialogPane);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.FINISH)
        {
            String stratName = strategyName.getText();
            String selectedSport = sportsComboBox.getValue();
            Controller.getInstance().createStrategy(stratName, selectedSport, maxPlayers.isSelected(),
                                                    maxTeams.isSelected());
            RootLayoutController.getInstance().getOpenStrategyController().updateTree();
            RootLayoutController.getInstance().imageByImage();
        }
    }
}
