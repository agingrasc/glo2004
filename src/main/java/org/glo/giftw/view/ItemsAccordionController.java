package org.glo.giftw.view;

import java.io.File;
import java.io.IOException;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.Obstacle;
import org.glo.giftw.domain.strategy.Projectile;
import org.glo.giftw.domain.strategy.Team;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class ItemsAccordionController
{
	@FXML
	private Accordion rootAccordion;
	
	@FXML
    private TableView<Team> teamsTableView;

	@FXML
    private TableColumn<Team, String> teamImageColumn;

    @FXML
    private TableColumn<Team, String> teamNameColumn;

    @FXML
    private TableView<Obstacle> obstaclesTableView;
    
    @FXML
    private TableColumn<Obstacle, String> obstacleImageColumn;

    @FXML
    private TableColumn<Obstacle, String> obstacleNameColumn;

    @FXML
    private TableView<Projectile> projectilesTableView;
    
    @FXML
    private TableColumn<Projectile, String> projectileImageColumn;

    @FXML
    private TableColumn<Projectile, String> projectileNameColumn;
    
    private ObservableList<Obstacle> obstacles;
    
    private ObservableList<Team> teams;
    
    private ObservableList<Projectile> projectiles;
    

	@FXML
	private void initialize() throws IOException
	{
		obstacles = FXCollections.observableArrayList(Controller.getInstance().getObstacles());
		obstaclesTableView.setItems(obstacles);
		obstacleImageColumn.setCellFactory(
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
					                imageView.setFitWidth(32);
					                imageView.setFitHeight(32);
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

		obstacleImageColumn.setCellValueFactory(
		        new Callback<CellDataFeatures<Obstacle, String>, ObservableValue<String>>()
		        {
			        public ObservableValue<String> call(CellDataFeatures<Obstacle, String> p)
			        {
				        return new ReadOnlyObjectWrapper<String>(p.getValue().getImagePath());
			        }
		        });

		obstacleNameColumn.setCellValueFactory(
		        new Callback<CellDataFeatures<Obstacle, String>, ObservableValue<String>>()
		        {
			        public ObservableValue<String> call(CellDataFeatures<Obstacle, String> p)
			        {
				        return new ReadOnlyObjectWrapper<String>(p.getValue().getName());
			        }
		        });
		
		teams = FXCollections.observableArrayList(Controller.getInstance().getTeams());
		teamsTableView.setItems(teams);
		teamImageColumn.setCellFactory(
		        new Callback<TableColumn<Team, String>, TableCell<Team, String>>()
		        {
			        @Override
			        public TableCell<Team, String> call(
		                    TableColumn<Team, String> param)
			        {
				        TableCell<Team, String> cell = new TableCell<Team, String>()
		                {
			                @Override
			                public void updateItem(String item, boolean empty)
			                {
			                	super.updateItem(item, empty);
				                if (item != null && !empty)
				                {          
				                	final Canvas canvas = new Canvas(32, 32);
				                	GraphicsContext gc = canvas.getGraphicsContext2D();
				                	gc.setFill(Color.web(item));
				                	gc.fillOval(0, 0, 32, 32);
					                setGraphic(canvas);
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

		teamImageColumn.setCellValueFactory(
		        new Callback<CellDataFeatures<Team, String>, ObservableValue<String>>()
		        {
			        public ObservableValue<String> call(CellDataFeatures<Team, String> p)
			        {
				        return new ReadOnlyObjectWrapper<String>(p.getValue().getColour());
			        }
		        });

		teamNameColumn.setCellValueFactory(
		        new Callback<CellDataFeatures<Team, String>, ObservableValue<String>>()
		        {
			        public ObservableValue<String> call(CellDataFeatures<Team, String> p)
			        {
				        return new ReadOnlyObjectWrapper<String>(p.getValue().getName());
			        }
		        });
		
		projectiles = FXCollections.observableArrayList(Controller.getInstance().getProjectile());
		projectilesTableView.setItems(projectiles);
		projectileImageColumn.setCellFactory(
		        new Callback<TableColumn<Projectile, String>, TableCell<Projectile, String>>()
		        {
			        @Override
			        public TableCell<Projectile, String> call(
		                    TableColumn<Projectile, String> param)
			        {
				        TableCell<Projectile, String> cell = new TableCell<Projectile, String>()
		                {
			                @Override
			                public void updateItem(String item, boolean empty)
			                {
			                	super.updateItem(item, empty);
				                if (item != null && !empty)
				                {
				                	File file = new File(item);
					                ImageView imageView = new ImageView(new Image(file.toURI().toString()));
					                imageView.setFitWidth(32);
					                imageView.setFitHeight(32);
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

		projectileImageColumn.setCellValueFactory(
		        new Callback<CellDataFeatures<Projectile, String>, ObservableValue<String>>()
		        {
			        public ObservableValue<String> call(CellDataFeatures<Projectile, String> p)
			        {
				        return new ReadOnlyObjectWrapper<String>(p.getValue().getImagePath());
			        }
		        });

		projectileNameColumn.setCellValueFactory(
		        new Callback<CellDataFeatures<Projectile, String>, ObservableValue<String>>()
		        {
			        public ObservableValue<String> call(CellDataFeatures<Projectile, String> p)
			        {
				        return new ReadOnlyObjectWrapper<String>(p.getValue().getName());
			        }
		        });
		
		updateAllTables();
		
		obstaclesTableView.setOnDragDetected(new EventHandler<MouseEvent>() { //drag
	        @Override
	        public void handle(MouseEvent event) {
	            // drag was detected, start drag-and-drop gesture
	            Obstacle selected = obstaclesTableView.getSelectionModel().getSelectedItem();
	            if(selected !=null){

	                Dragboard db = obstaclesTableView.startDragAndDrop(TransferMode.ANY);
	                ClipboardContent content = new ClipboardContent();
	                File imageFile = new File(selected.getImagePath());
	                Image image = new Image(imageFile.toURI().toString(), 32, 32, false, false);
	                db.setDragView(image);
	                content.putString(selected.getName());
	                db.setContent(content);
	                event.consume(); 
	            }
	        }
	    });
		
		projectilesTableView.setOnDragDetected(new EventHandler<MouseEvent>() { //drag
	        @Override
	        public void handle(MouseEvent event) {
	            // drag was detected, start drag-and-drop gesture
	            Projectile selected = projectilesTableView.getSelectionModel().getSelectedItem();
	            if(selected !=null)
	            {
	                Dragboard db = projectilesTableView.startDragAndDrop(TransferMode.ANY);
	                ClipboardContent content = new ClipboardContent();
	                File imageFile = new File(selected.getImagePath());
	                Image image = new Image(imageFile.toURI().toString(), 16, 16, false, false);
	                db.setDragView(image);
	                content.putString(selected.getName());
	                db.setContent(content);
	                event.consume(); 
	            }
	        }
	    });
		
		teamsTableView.setOnDragDetected(new EventHandler<MouseEvent>() { //drag
	        @Override
	        public void handle(MouseEvent event) {
	            // drag was detected, start drag-and-drop gesture
	            Team selected = teamsTableView.getSelectionModel().getSelectedItem();
	            if(selected != null){

	                Dragboard db = teamsTableView.startDragAndDrop(TransferMode.ANY);
	                ClipboardContent content = new ClipboardContent();
	                final Canvas canvas = new Canvas(32, 32);
                	GraphicsContext gc = canvas.getGraphicsContext2D();
                	gc.setFill(Color.web(selected.getColour()));
                	gc.fillOval(0, 0, 32, 32);
                	WritableImage snapshot = canvas.snapshot(new SnapshotParameters(), null);
	                db.setDragView(snapshot);
	                content.putString(selected.getName());
	                db.setContent(content);
	                event.consume(); 
	            }
	        }
	    });
	}
	
	public void updateObstaclesTable()
	{
		obstacles.clear();
		obstacles.addAll(Controller.getInstance().getObstacles());
	}
	
	public void updateTeamsTable()
	{
		teams.clear();
		//TODO teams n'enregistrent pas
		teams.addAll(Controller.getInstance().getTeams());
	}
	
	public void updateProjectilesTable()
	{
		projectiles.clear();
		projectiles.add(Controller.getInstance().getProjectile());
	}
	
	public void updateAllTables()
	{
		updateObstaclesTable();
		updateTeamsTable();
		updateProjectilesTable();
	}

	public Accordion getRootAccordion()
	{
		return rootAccordion;
	}
}
