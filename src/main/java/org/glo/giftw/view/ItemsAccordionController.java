package org.glo.giftw.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.MaxNumberException;
import org.glo.giftw.domain.exceptions.TeamNotFound;
import org.glo.giftw.domain.strategy.Obstacle;
import org.glo.giftw.domain.strategy.Projectile;
import org.glo.giftw.domain.strategy.Team;
import org.glo.giftw.view.edit.ViewableGameObject;
import org.glo.giftw.view.edit.ViewablePlayer;

import java.io.IOException;

public class ItemsAccordionController
{
    @FXML
    private Accordion rootAccordion;
    
    @FXML
    private TitledPane teamsTitledPane;

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
    
    public static final int PLAYER_RADIUS = 16;
    
    public static final int OBSTACLE_SIZE = 32;
    
    public static final int PROJECTILE_SIZE = 16;


    @FXML
    public void initialize() throws IOException
    {	
    	rootAccordion.setExpandedPane(teamsTitledPane);
    	
    	initObstacleTable();
        initTeamTable();
        initProjectileTable();

        updateAllTables();
        
        initObstacleDrag();
        initTeamDrag();
        initProjectileDrag();
    }
    
    private void initObstacleDrag()
    {
    	obstaclesTableView.setOnDragDetected(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Obstacle selected = obstaclesTableView.getSelectionModel().getSelectedItem();
                if (selected != null)
                {
                    Dragboard db = obstaclesTableView.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    String uuid = Controller.getInstance().addObstacle(selected.getName());
                    ViewableGameObject viewableGameObject = new ViewableGameObject(uuid);
                    try
					{
						RootLayoutController.getInstance().getCreationStackPaneController().getCurrentPane().addViewableToHashMap(uuid, viewableGameObject);
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    db.setDragView(viewableGameObject.getImage());
                    content.putString(uuid);
                    db.setContent(content);
                    event.consume();
                }
            }
        });
    }
    
    private void initTeamDrag()
    {
    	teamsTableView.setOnDragDetected(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Team selected = teamsTableView.getSelectionModel().getSelectedItem();
                if (selected != null)
                {
                    Dragboard db = teamsTableView.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    String uuid;
                    try
                    {
                        uuid = Controller.getInstance().addPlayer(selected.getName());
                        ViewablePlayer viewablePlayer = new ViewablePlayer(uuid, false, false, false);
                        RootLayoutController.getInstance().getCreationStackPaneController().getCurrentPane().addViewableToHashMap(uuid, viewablePlayer);
                        db.setDragView(viewablePlayer.getImage());
                        content.putString(uuid);
                        db.setContent(content);
                        event.consume();
                    }
                    catch (MaxNumberException err)
                    {
                    	Alert alert = new Alert(AlertType.WARNING);
                    	alert.setTitle("Avertissement");
                    	alert.setHeaderText("Avertissement");
                    	alert.setContentText("Le nombre de joueurs maximum dans l'equipe est atteint!");
                    	DialogPane dialogPane = alert.getDialogPane();
                    	dialogPane.getStylesheets().add(getClass().getResource("/css/visuaLigueCSS.css").toExternalForm());
                    	alert.showAndWait();
                    }
                    catch (TeamNotFound err)
                    {
                        err.printStackTrace();
                    } catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });
    }
    
    private void initProjectileDrag()
    {
    	 projectilesTableView.setOnDragDetected(new EventHandler<MouseEvent>()
         {
             @Override
             public void handle(MouseEvent event)
             {
                 Projectile selected = projectilesTableView.getSelectionModel().getSelectedItem();
                 if (selected != null)
                 {
                     Dragboard db = projectilesTableView.startDragAndDrop(TransferMode.ANY);
                     ClipboardContent content = new ClipboardContent();
                     //FIXME: dimension
                     String uuid = Controller.getInstance().addProjectile();
                     ViewableGameObject viewableGameObject = new ViewableGameObject(uuid);
                     try
					{
						RootLayoutController.getInstance().getCreationStackPaneController().getCurrentPane().addViewableToHashMap(uuid, viewableGameObject);
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                     db.setDragView(viewableGameObject.getImage());
                     content.putString(uuid);
                     db.setContent(content);
                     event.consume();
                 }
             }
         });
    }
    
    private void initObstacleTable()
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
                                    Image img = new Image(String.format("file:%s", item), OBSTACLE_SIZE, OBSTACLE_SIZE, true, true);
                                    ImageView imgView = new ImageView(img);
                                    setGraphic(imgView);
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
    }
    
    private void initTeamTable()
    {
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
                                    Circle circle = new Circle(PLAYER_RADIUS, Color.web(item));
                                    setGraphic(circle);
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
    }
    
    private void initProjectileTable()
    {
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
                                    Image img = new Image(String.format("file:%s", item), PROJECTILE_SIZE, PROJECTILE_SIZE, true, true);
                                    ImageView imgView = new ImageView(img);
                                    setGraphic(imgView);
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
    }

    public void updateObstaclesTable()
    {
        obstacles.clear();
        obstacles.addAll(Controller.getInstance().getObstacles());
    }

    public void updateTeamsTable()
    {
        teams.clear();
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
