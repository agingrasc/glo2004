package org.glo.giftw.view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.util.Vector;

import java.io.File;
import java.io.IOException;

public class MediaContentController extends AnimationTimer
{

    private final static long FPS = 1/30;
    @Override
    /**
     * Main loop pour la visualisation
     * @param long timestamp temps systeme au moment de l'appel en nanoseconde
     */
    public void handle(long timestamp)
    {
        long delta_t = timestamp - lastTimeStamp;
        if (delta_t >= FPS)
        {
            //actual stuff
            System.out.println("FUBAR: " + timestamp);
        }
    }

    @FXML
	private StackPane rootStackPane;

	private Vector ratioPixelToUnit;

	private long lastTimeStamp;

	public void displayNewFrame()
	{
		rootStackPane.getChildren().clear();
		rootStackPane.setAlignment(Pos.CENTER);
		Controller.getInstance().createNewFrame();
		File file = new File(Controller.getInstance().getSportFieldImagePath());
		Image sportFieldImage = new Image(file.toURI().toString());
        ImageView field = new ImageView(sportFieldImage);

        field.setOnMouseMoved(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Vector adjCoord = new Vector(event.getX(), event.getY());
                try
                {
                    RootLayoutController.getInstance().getBottomToolBarController().updateCoordinate(adjCoord, ratioPixelToUnit);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        rootStackPane.getChildren().add(field);

		//FIXME: trouver dynamiquement la taille restreignante
		Vector fieldDimensions = Controller.getInstance().getFieldDimensions();
        double adjustedHeight = rootStackPane.getChildren().get(0).getBoundsInParent().getHeight();
        double ratio = adjustedHeight/fieldDimensions.getY();
        double adjustedWidth = ratio * fieldDimensions.getX();
		this.ratioPixelToUnit = new Vector(adjustedWidth/fieldDimensions.getX(), adjustedHeight/fieldDimensions.getY());
	}

	public StackPane getRootStackPane()
	{
		return rootStackPane;
	}
}
