package org.glo.giftw.view;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class OpenSportController
{
	@FXML
	private VBox rootVBox;

	@FXML
	private TableView<HashMap.Entry<String, String>> tableView;

	@FXML
	private TableColumn<HashMap.Entry<String, String>, String> imageColumn;

	@FXML
	private TableColumn<HashMap.Entry<String, String>, String> nameColumn;

	//private ObservableList<HashMap.Entry<String, String>> sports;

	@FXML
	private void initialize() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(FXMLPaths.LIST_PLACE_HOLDER.toString()));
		Label listPlaceHolder = loader.load();
		tableView.setPlaceholder(listPlaceHolder);
		
		//TODO à améliorer avec autre chose qu'un hashMap...
				/*sports = FXCollections.observableArrayList(Controller.getInstance().getSportDescription().entrySet());
				tableView.setItems(sports);
				
				 imageColumn.setCellFactory(new Callback<TableColumn<HashMap.Entry<String, String>, String>, TableCell<HashMap.Entry<String, String>, String>>() {
			            @Override
			            public TableCell<HashMap.Entry<String, String>, String> call(TableColumn<HashMap.Entry<String, String>, String> param) {
			                //Set up the ImageView
			                final ImageView imageview = new ImageView();
			                imageview.setFitHeight(50);
			                imageview.setFitWidth(50);

			                //Set up the Table
			                TableCell<HashMap.Entry<String, String>, String> cell = new TableCell<HashMap.Entry<String, String>, String>() {
			                    public void updateItem(HashMap.Entry<String, String> item, boolean empty) {
			                        if (item != null) {
			                            imageview.setImage(new Image(item.getValue()));
			                        }
			                    }
			                };

			                // Attach the imageview to the cell
			                cell.setGraphic(imageview);
			                return cell;
			            }

			        });
				
				imageColumn.setCellValueFactory(new Callback<CellDataFeatures<HashMap.Entry<String, String>, String>, ObservableValue<String>>() {
				     public ObservableValue<String> call(CellDataFeatures<HashMap.Entry<String, String>, String> p) {
				         return new ReadOnlyObjectWrapper<String>(p.getValue().getValue());
				     }
				  });
				
				nameColumn.setCellValueFactory(new Callback<CellDataFeatures<HashMap.Entry<String, String>, String>, ObservableValue<String>>() {
				     public ObservableValue<String> call(CellDataFeatures<HashMap.Entry<String, String>, String> p) {
				         return new ReadOnlyObjectWrapper<String>(p.getValue().getKey());
				     }
				  });*/
	}

	public void updateTable()
	{
		//TODO à améliorer avec autre chose qu'un hashMap...
		//sports = FXCollections.observableArrayList(Controller.getInstance().getSportDescription().entrySet());
	}

	public VBox getRootVBox()
	{
		return rootVBox;
	}
}