package org.glo.giftw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.glo.giftw.domain.AutoSave;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.view.FXMLPaths;
import org.glo.giftw.view.RootLayoutController;

import java.io.IOException;
import java.util.Timer;

public class MainApp extends Application
{
    private Stage primaryStage;
    public static final String TITLE = "VisuaLigue";
    private Timer time;

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(TITLE);
        this.primaryStage.setMaximized(true);

        initRootLayout();
        RootLayoutController.getInstance().openStrategy();

        time = new Timer();
        AutoSave save = new AutoSave();
        //Controller.getInstance().enableAutoSave(false);
        time.schedule(save, 0, 5000);
    }

    @Override
    public void stop() throws Exception
    {
        time.cancel();
        Controller.getInstance().saveStrategies();
        super.stop();
    }

    public void initRootLayout()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXMLPaths.ROOT_LAYOUT_PATH.toString()));
            loader.setController(RootLayoutController.getInstance());
            BorderPane rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
