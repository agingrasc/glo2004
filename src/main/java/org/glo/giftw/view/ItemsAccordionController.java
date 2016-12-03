package org.glo.giftw.view;

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
import javafx.scene.control.Accordion;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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
				                	ObstacleDisplay obsDisp = new ObstacleDisplay(item, 0, 0);
					                setGraphic(obsDisp);
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
				                	PlayerDisplay playerDisp = new PlayerDisplay(item, 0, 0);
					                setGraphic(playerDisp.getCanvas());
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
				                	ProjectileDisplay projDisp = new ProjectileDisplay(item, 0, 0);
					                setGraphic(projDisp);
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
		
		obstaclesTableView.setOnDragDetected(new EventHandler<MouseEvent>() 
		{
	        @Override
	        public void handle(MouseEvent event) 
	        {
	            Obstacle selected = obstaclesTableView.getSelectionModel().getSelectedItem();
	            if(selected != null)
	            {
	                Dragboard db = obstaclesTableView.startDragAndDrop(TransferMode.ANY);
	                ClipboardContent content = new ClipboardContent();
	                ObstacleDisplay obsDisp = new ObstacleDisplay(selected.getImagePath(), 0, 0);
	                db.setDragView(obsDisp.getImage());
	                content.putString(selected.getName());
	                db.setContent(content);
	                event.consume(); 
	            }
	        }
	    });
		
		projectilesTableView.setOnDragDetected(new EventHandler<MouseEvent>() 
		{
	        @Override
	        public void handle(MouseEvent event) 
	        {
	            Projectile selected = projectilesTableView.getSelectionModel().getSelectedItem();
	            if(selected != null)
	            {
	                Dragboard db = projectilesTableView.startDragAndDrop(TransferMode.ANY);
	                ClipboardContent content = new ClipboardContent();
	                ProjectileDisplay projDisp = new ProjectileDisplay(selected.getImagePath(), 0, 0);
	                db.setDragView(projDisp.getImage());
	                content.putString(selected.getName());
	                db.setContent(content);
	                event.consume(); 
	            }
	        }
	    });
		
		teamsTableView.setOnDragDetected(new EventHandler<MouseEvent>() 
		{ 
	        @Override
	        public void handle(MouseEvent event) 
	        {
	            Team selected = teamsTableView.getSelectionModel().getSelectedItem();
	            if(selected != null)
	            {
	                Dragboard db = teamsTableView.startDragAndDrop(TransferMode.ANY);
	                ClipboardContent content = new ClipboardContent();
	                PlayerDisplay playerDisp = new PlayerDisplay(selected.getColour(), 0, 0);
	                db.setDragView(playerDisp.getSnapshot());
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
