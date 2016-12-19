package org.glo.giftw.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.TreeViewable;
import org.glo.giftw.domain.exceptions.StrategyNotFound;
import org.glo.giftw.domain.strategy.Sport;
import org.glo.giftw.domain.strategy.Strategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenStrategyController
{
    @FXML
    private VBox rootVBox;

    @FXML
    private TreeTableView<TreeViewable> treeTableView;

    @FXML
    private TreeTableColumn<TreeViewable, String> imageColumn;

    @FXML
    private TreeTableColumn<TreeViewable, String> nameColumn;

    private TreeItem<TreeViewable> root;

    @FXML
    private void initialize() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXMLPaths.LIST_PLACE_HOLDER.toString()));
        Label listPlaceHolder = loader.load();
        treeTableView.setPlaceholder(listPlaceHolder);

        root = new TreeItem<TreeViewable>();
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

        imageColumn.setCellFactory(
                new Callback<TreeTableColumn<TreeViewable, String>, TreeTableCell<TreeViewable, String>>()
                {
                    @Override
                    public TreeTableCell<TreeViewable, String> call(
                            TreeTableColumn<TreeViewable, String> param)
                    {
                        TreeTableCell<TreeViewable, String> cell = new TreeTableCell<TreeViewable, String>()
                        {
                            @Override
                            public void updateItem(String item, boolean empty)
                            {
                                super.updateItem(item, empty);
                                if (item != null && !empty)
                                {
                                    File file = new File(item);
                                    ImageView imageView = new ImageView(new Image(file.toURI().toString()));
                                    imageView.setFitWidth(200);
                                    imageView.setFitHeight(100);
                                    setGraphic(imageView);
                                }
                                else
                                {
                                    setGraphic(null);
                                }
                            }
                        };
                        return cell;
                    }
                });

        imageColumn.setCellValueFactory(
                new Callback<CellDataFeatures<TreeViewable, String>, ObservableValue<String>>()
                {
                    public ObservableValue<String> call(CellDataFeatures<TreeViewable, String> p)
                    {
                        return new ReadOnlyObjectWrapper<String>(p.getValue().getValue().getImagePath());
                    }
                });

        nameColumn.setCellValueFactory(
                new Callback<CellDataFeatures<TreeViewable, String>, ObservableValue<String>>()
                {
                    public ObservableValue<String> call(CellDataFeatures<TreeViewable, String> p)
                    {
                        return new ReadOnlyObjectWrapper<String>(p.getValue().getValue().getDisplayName());
                    }
                });
        updateTree();
    }

    public void updateTree()
    {
        ArrayList<Strategy> strategies = new ArrayList<Strategy>(Controller.getInstance().getStrategies());
        this.updatePreview(strategies);
        ArrayList<Sport> sports = new ArrayList<Sport>(Controller.getInstance().getSports());
        root.getChildren().clear();
        for (Sport sport : sports)
        {
            TreeItem<TreeViewable> sportItem = new TreeItem<>(sport);
            root.getChildren().add(sportItem);
            for (Strategy strategy : strategies)
            {
                if (strategy.getSport().getName().equals(sport.getName()))
                {
                    sportItem.getChildren().add(new TreeItem<TreeViewable>(strategy));
                }
            }
        }
    }

    public void updatePreview(List<Strategy> strategies)
    {
        for (Strategy strategy : strategies)
        {
            String stratName = strategy.getName();
            try
            {
                Controller.getInstance().openStrategy(stratName);
            }
            catch (StrategyNotFound strategyNotFound)
            {
                strategyNotFound.printStackTrace();
            }

            String filePath = "./data/" + stratName + ".png";
            StrategyImageExporter stratExporter = new StrategyImageExporter();
            stratExporter.saveTo(filePath);
        }
    }

    public TreeTableView<TreeViewable> getTreeTableView()
    {
        return treeTableView;
    }

    public VBox getRootVBox()
    {
        return rootVBox;
    }
}
