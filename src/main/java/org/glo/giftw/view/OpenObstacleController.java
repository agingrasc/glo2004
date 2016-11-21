package org.glo.giftw.view;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.glo.giftw.controller.Controller;
import org.glo.giftw.domain.Obstacle;

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

public class OpenObstacleController
{
	@FXML
	private VBox rootVBox;

	@FXML
	private TableView<HashMap.Entry<String, String>> tableView;

	@FXML
	private TableColumn<HashMap.Entry<String, String>, String> imageColumn;

	@FXML
	private TableColumn<HashMap.Entry<String, String>, String> nameColumn;

	private ObservableList<HashMap.Entry<String, String>> obstacles;

	@FXML
	private void initialize() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(FXMLPaths.LIST_PLACE_HOLDER.toString()));
		Label listPlaceHolder = loader.load();
		tableView.setPlaceholder(listPlaceHolder);

		obstacles = FXCollections.observableArrayList(Controller.getInstance().getObstacles().entrySet());
		tableView.setItems(obstacles);

		imageColumn.setCellFactory(
		        new Callback<TableColumn<HashMap.Entry<String, String>, String>, TableCell<HashMap.Entry<String, String>, String>>()
		        {
			        @Override
			        public TableCell<HashMap.Entry<String, String>, String> call(
		                    TableColumn<HashMap.Entry<String, String>, String> param)
			        {
				        TableCell<HashMap.Entry<String, String>, String> cell = new TableCell<HashMap.Entry<String, String>, String>()
		                {
			                @Override
			                public void updateItem(String item, boolean empty)
			                {
				                if (item != null)
				                {
				                	System.out.println(item);
				                	File file = new File(item);
					                ImageView imageView = new ImageView(new Image(file.toURI().toString()));
					                imageView.setFitWidth(200);
					                imageView.setFitHeight(100);
					                setGraphic(imageView);
				                }
			                }
		                };
				        return cell;
			        }
		        });

		imageColumn.setCellValueFactory(
		        new Callback<CellDataFeatures<HashMap.Entry<String, String>, String>, ObservableValue<String>>()
		        {
			        public ObservableValue<String> call(CellDataFeatures<HashMap.Entry<String, String>, String> p)
			        {
				        return new ReadOnlyObjectWrapper<String>(p.getValue().getValue());
			        }
		        });

		nameColumn.setCellValueFactory(
		        new Callback<CellDataFeatures<HashMap.Entry<String, String>, String>, ObservableValue<String>>()
		        {
			        public ObservableValue<String> call(CellDataFeatures<HashMap.Entry<String, String>, String> p)
			        {
				        return new ReadOnlyObjectWrapper<String>(p.getValue().getKey());
			        }
		        });
	}

	public VBox getRootVBox()
	{
		return rootVBox;
	}

	public void updateTable()
	{
		// TODO il serait plus efficace de seulement updater l'item qui
		// change...
		obstacles.setAll(Controller.getInstance().getObstacles().entrySet());
	}
}
