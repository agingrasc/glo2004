package org.glo.giftw.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.glo.giftw.controller.Controller;
import org.glo.giftw.domain.ObjectPool;
import org.glo.giftw.domain.Obstacle;
import org.glo.giftw.domain.Sport;
import org.glo.giftw.domain.Strategy;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class OpenStrategyController
{
	@FXML
	private VBox rootVBox;
	
	@FXML
	private TreeTableView<Displayable> treeTableView;

	@FXML
	private TreeTableColumn<Displayable, String> imageColumn;

	@FXML
	private TreeTableColumn<Displayable, String> nameColumn;
	
	private TreeItem<Displayable> root;

	@FXML
	private void initialize() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(FXMLPaths.LIST_PLACE_HOLDER.toString()));
		Label listPlaceHolder = loader.load();
		treeTableView.setPlaceholder(listPlaceHolder);
		
		root = new TreeItem<Displayable>();
		treeTableView.setRoot(root);
		treeTableView.setShowRoot(false);
		
		imageColumn.setCellFactory(
		        new Callback<TreeTableColumn<Displayable, String>, TreeTableCell<Displayable, String>>()
		        {
			        @Override
			        public TreeTableCell<Displayable, String> call(
		                    TreeTableColumn<Displayable, String> param)
			        {
				        TreeTableCell<Displayable, String> cell = new TreeTableCell<Displayable, String>()
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
		
		imageColumn.setCellValueFactory(new Callback<CellDataFeatures<Displayable, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Displayable, String> p) {
		         // p.getValue() returns the TreeItem<Person> instance for a particular TreeTableView row,
		         // p.getValue().getValue() returns the Person instance inside the TreeItem<Person>
		         return p.getValue().getValue().getDisplayableImagePath();
		     }
		});
		
		nameColumn.setCellValueFactory(new Callback<CellDataFeatures<Displayable, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Displayable, String> p) {
		         // p.getValue() returns the TreeItem<Person> instance for a particular TreeTableView row,
		         // p.getValue().getValue() returns the Person instance inside the TreeItem<Person>
		         return p.getValue().getValue().getDisplayableName();
		     }
		});
	}
	
	public void updateTreeTable()
	{
		ArrayList<Strategy> strategies = new ArrayList<Strategy>(Controller.getInstance().getStrategies());
		ArrayList<Sport> sports = new ArrayList<Sport>(Controller.getInstance().getSports());
		root.getChildren().clear();
		for (Sport sport : sports) 
		{
			TreeItem<Displayable> sportItem = new TreeItem<>(sport);
			root.getChildren().add(sportItem);
			for(Strategy strategy: strategies)
			{
				if(strategy.getSport().getName() == sport.getName())
				{
					sportItem.getChildren().add(strategy);
				}
			}
		}
	}

	public VBox getRootVBox()
	{
		return rootVBox;
	}
}
