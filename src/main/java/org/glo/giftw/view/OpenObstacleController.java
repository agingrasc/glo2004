package org.glo.giftw.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.Obstacle;

import java.io.File;
import java.io.IOException;

public class OpenObstacleController
{
    @FXML
    private VBox rootVBox;

    @FXML
    private TableView<Obstacle> tableView;

    @FXML
    private TableColumn<Obstacle, String> imageColumn;

    @FXML
    private TableColumn<Obstacle, String> nameColumn;

    private ObservableList<Obstacle> obstacles;

    @FXML
    private void initialize() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXMLPaths.LIST_PLACE_HOLDER.toString()));
        Label listPlaceHolder = loader.load();
        tableView.setPlaceholder(listPlaceHolder);
        obstacles = FXCollections.observableArrayList(Controller.getInstance().getObstacles());
        tableView.setItems(obstacles);
        imageColumn.setCellFactory(
                new Callback<TableColumn<Obstacle, String>, TableCell<Obstacle, String>>()
                {
                    @Override
                    public TableCell<Obstacle, String> call(
                            TableColumn<Obstacle, String> param)
                    {
                        TableCell<Obstacle, String> cell = new TableCell<Obstacle, String>()
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
                new Callback<CellDataFeatures<Obstacle, String>, ObservableValue<String>>()
                {
                    public ObservableValue<String> call(CellDataFeatures<Obstacle, String> p)
                    {
                        return new ReadOnlyObjectWrapper<String>(p.getValue().getImagePath());
                    }
                });

        nameColumn.setCellValueFactory(
                new Callback<CellDataFeatures<Obstacle, String>, ObservableValue<String>>()
                {
                    public ObservableValue<String> call(CellDataFeatures<Obstacle, String> p)
                    {
                        return new ReadOnlyObjectWrapper<String>(p.getValue().getName());
                    }
                });
    }

    public VBox getRootVBox()
    {
        return rootVBox;
    }

    public TableView<Obstacle> getTableView()
	{
		return tableView;
	}

	public void updateTable()
    {
        obstacles.clear();
        obstacles.addAll(Controller.getInstance().getObstacles());
    }
}
