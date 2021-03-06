package org.glo.giftw.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RootLayoutController
{
    private BottomToolBarController bottomToolBarController;
    private CreationStackPaneController creationStackPaneController;
    private CreationToolBarController creationToolBarController;
    private DefaultToolBarController defaultToolBarController;
    private GeneralPropertiesPaneController generalPropertiesPaneController;
    private ItemsAccordionController itemsAccordionController;
    private MediaContentController mediaContentController;
    private MediaToolBarController mediaToolBarController;
    private ModeToolBarController modeToolBarController;
    private StrategyExporterController strategyExporterController;
    private NewObstacleController newObstacleController;
    private NewSportController newSportController;
    private NewStrategyController newStrategyController;
    private OpenObstacleController openObstacleController;
    private OpenObstacleToolBarController openObstacleToolBarController;
    private OpenStrategyController openStrategyController;
    private OpenStrategyToolBarController openStrategyToolBarController;
    private PlayerPropertiesPaneController playerPropertiesPaneController;

    @FXML
    private ToggleGroup mode;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ToolBar rootToolBar;

    private static RootLayoutController INSTANCE = null;

    private RootLayoutController()
    {
    }

    ;

    public static synchronized RootLayoutController getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new RootLayoutController();
        }
        return INSTANCE;
    }

    public void exportStrategy() throws IOException
    {
        getStrategyExporterController().showDialog();
    }

    public void openObstacle() throws IOException
    {
        clearRootToolBar();
        addToolBar(getDefaultToolBarController().getRootToolBar());
        addToolBar(getOpenObstacleToolBarController().getRootToolBar());
        borderPane.setLeft(null);
        borderPane.setCenter(getOpenObstacleController().getRootVBox());
        borderPane.setBottom(null);
        borderPane.setRight(null);
    }

    public void openStrategy() throws IOException
    {
        clearRootToolBar();
        addToolBar(getDefaultToolBarController().getRootToolBar());
        addToolBar(getModeToolBarController().getRootToolBar());
        addToolBar(getOpenStrategyToolBarController().getRootToolBar());
        borderPane.setLeft(null);
        borderPane.setCenter(getOpenStrategyController().getRootVBox());
        borderPane.setBottom(null);
        borderPane.setRight(null);
    }

    public void newObstacle() throws IOException
    {
        getNewObstacleController().showDialog();
    }

    public void newSport() throws IOException
    {
        getNewSportController().showDialog();
    }

    public void newStrategy() throws IOException
    {
        getNewStrategyController().showDialog();
    }

    public void watch() throws IOException
    {
        clearRootToolBar();
        addToolBar(getDefaultToolBarController().getRootToolBar());
        addToolBar(getModeToolBarController().getRootToolBar());
        addToolBar(getMediaToolBarController().getRootToolBar());
        borderPane.setLeft(null);
        borderPane.setCenter(getCreationStackPaneController().getScrollPane());
        borderPane.setBottom(getBottomToolBarController().getRootToolBar());
        borderPane.setRight(null);
        borderPane.applyCss();
        borderPane.layout();
        getMediaToolBarController().initialize();
        getCreationStackPaneController().init(EditionMode.WATCH);
    }

    public void imageByImage() throws IOException
    {
        clearRootToolBar();
        addToolBar(getDefaultToolBarController().getRootToolBar());
        addToolBar(getModeToolBarController().getRootToolBar());
        addToolBar(getCreationToolBarController().getRootToolBar());
        borderPane.setLeft(getItemsAccordionController().getRootAccordion());
        borderPane.setCenter(getCreationStackPaneController().getScrollPane());
        borderPane.setBottom(getBottomToolBarController().getRootToolBar());
        borderPane.setRight(getGeneralPropertiesPaneController().getRootAccordion());
        borderPane.applyCss();
        borderPane.layout();
        getCreationStackPaneController().init(EditionMode.IMAGE);
    }

    public void realTime() throws IOException
    {
        clearRootToolBar();
        addToolBar(getDefaultToolBarController().getRootToolBar());
        addToolBar(getModeToolBarController().getRootToolBar());
        addToolBar(getCreationToolBarController().getRootToolBar());
        borderPane.setLeft(getItemsAccordionController().getRootAccordion());
        borderPane.setCenter(getCreationStackPaneController().getScrollPane());
        borderPane.setBottom(getBottomToolBarController().getRootToolBar());
        borderPane.setRight(getGeneralPropertiesPaneController().getRootAccordion());
        borderPane.applyCss();
        borderPane.layout();
        this.getCreationStackPaneController().init(EditionMode.REAL_TIME);
    }

    private void addToolBar(ToolBar toolBar)
    {
        rootToolBar.getItems().addAll(toolBar.getItems());
    }

    private void clearRootToolBar()
    {
        rootToolBar.getItems().clear();
    }

    public BottomToolBarController getBottomToolBarController() throws IOException
    {
        if (bottomToolBarController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.BOTTOM_TOOL_BAR_PATH.toString()));
            loader.load();
            this.bottomToolBarController = loader.getController();
        }
        return bottomToolBarController;
    }

    public CreationStackPaneController getCreationStackPaneController() throws IOException
    {
        if (creationStackPaneController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.CREATION_STACK_PANE_PATH.toString()));
            loader.load();
            this.creationStackPaneController = loader.getController();
        }
        return creationStackPaneController;
    }

    public CreationToolBarController getCreationToolBarController() throws IOException
    {
        if (creationToolBarController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.CREATION_TOOL_BAR_PATH.toString()));
            loader.load();
            this.creationToolBarController = loader.getController();
        }
        return creationToolBarController;
    }

    public DefaultToolBarController getDefaultToolBarController() throws IOException
    {
        if (defaultToolBarController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.DEFAULT_TOOL_BAR_PATH.toString()));
            loader.load();
            this.defaultToolBarController = loader.getController();
        }
        return defaultToolBarController;
    }

    public GeneralPropertiesPaneController getGeneralPropertiesPaneController() throws IOException
    {
        if (generalPropertiesPaneController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.GENERAL_PROPERTIES_PANE_PATH.toString()));
            loader.load();
            this.generalPropertiesPaneController = loader.getController();
        }
        return generalPropertiesPaneController;
    }

    public ItemsAccordionController getItemsAccordionController() throws IOException
    {
        if (itemsAccordionController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.ITEMS_ACCORDION_PATH.toString()));
            loader.load();
            this.itemsAccordionController = loader.getController();
        }

        this.itemsAccordionController.initialize();
        return itemsAccordionController;
    }

    public MediaContentController getMediaContentController() throws IOException
    {
        if (mediaContentController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.MEDIA_CONTENT_PATH.toString()));
            loader.load();
            this.mediaContentController = loader.getController();
        }
        return mediaContentController;
    }

    public MediaToolBarController getMediaToolBarController() throws IOException
    {
        if (mediaToolBarController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.MEDIA_TOOL_BAR_PATH.toString()));
            loader.load();
            this.mediaToolBarController = loader.getController();
        }
        return mediaToolBarController;
    }

    public ModeToolBarController getModeToolBarController() throws IOException
    {
        if (modeToolBarController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.MODE_TOOL_BAR_PATH.toString()));
            loader.load();
            this.modeToolBarController = loader.getController();
        }
        return modeToolBarController;
    }

    public StrategyExporterController getStrategyExporterController() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXMLPaths.STRATEGY_EXPORTER.toString()));
        loader.load();
        this.strategyExporterController = loader.getController();
        return strategyExporterController;
    }

    public NewObstacleController getNewObstacleController() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXMLPaths.NEW_OBSTACLE_PATH.toString()));
        loader.load();
        this.newObstacleController = loader.getController();
        return newObstacleController;
    }

    public NewSportController getNewSportController() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXMLPaths.NEW_SPORT_PATH.toString()));
        loader.load();
        this.newSportController = loader.getController();
        return newSportController;
    }

    public NewStrategyController getNewStrategyController() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXMLPaths.NEW_STRATEGY_PATH.toString()));
        loader.load();
        this.newStrategyController = loader.getController();
        return newStrategyController;
    }

    public OpenObstacleController getOpenObstacleController() throws IOException
    {
        if (openObstacleController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.OPEN_OBSTACLE_PATH.toString()));
            loader.load();
            this.openObstacleController = loader.getController();
        }
        return openObstacleController;
    }

    public OpenObstacleToolBarController getOpenObstacleToolBarController() throws IOException
    {
        if (openObstacleToolBarController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.OPEN_OBSTACLE_TOOL_BAR_PATH.toString()));
            loader.load();
            this.openObstacleToolBarController = loader.getController();
        }
        return openObstacleToolBarController;
    }

    public OpenStrategyController getOpenStrategyController() throws IOException
    {
        if (openStrategyController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.OPEN_STRATEGY_PATH.toString()));
            loader.load();
            this.openStrategyController = loader.getController();
        }
        return openStrategyController;
    }

    public OpenStrategyToolBarController getOpenStrategyToolBarController() throws IOException
    {
        if (openStrategyToolBarController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.OPEN_STRATEGY_TOOL_BAR_PATH.toString()));
            loader.load();
            this.openStrategyToolBarController = loader.getController();
        }
        return openStrategyToolBarController;
    }

    public PlayerPropertiesPaneController getPlayerPropertiesPaneController() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXMLPaths.PLAYER_PROPERTIES_PANE_PATH.toString()));
        loader.load();
        this.playerPropertiesPaneController = loader.getController();

        return playerPropertiesPaneController;
    }

    public BorderPane getBorderPane()
    {
        return borderPane;
    }

    public void setRightPane(Accordion rightMenu)
    {
        borderPane.setRight(rightMenu);
    }
}