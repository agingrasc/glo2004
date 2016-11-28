package org.glo.giftw.view;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.StackPane;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.Frame;

import java.io.File;

public class MediaContentController extends AnimationTimer
{

    private final static long FPS = 1000 * 1000 * 1000 / 30; //en nanoseconde

	@FXML
	private StackPane rootStackPane;

	private long lastTimeStamp;

    @Override
    /**
     * Main loop pour la visualisation
     * @param long timestamp temps systeme au moment de l'appel en nanoseconde
     */
    public void handle(long timestamp)
    {
        long delta_t = timestamp - this.lastTimeStamp;
        this.lastTimeStamp = timestamp;
        boolean isLastFrame = Controller.getInstance().isLastFrame();

        if (delta_t >= FPS && !isLastFrame)
        {
            //actual stuff
            System.out.println("FUBAR: " + timestamp);
            StackPane field = (StackPane) this.rootStackPane.getChildren().get(0);
            field.getChildren().clear();

			Frame frame = Controller.getInstance().nextFrame();
        }
    }

	public void displayNewFrame()
	{
		rootStackPane.getChildren().clear();
		rootStackPane.setAlignment(Pos.CENTER);
		this.initWithCurrentFrame();
	}

	public void initWithCurrentFrame()
	{
		File file = new File(Controller.getInstance().getSportFieldImagePath());
		Image sportFieldImage = new Image(file.toURI().toString());

		StackPane field = new StackPane();
		BackgroundPosition bgPos = BackgroundPosition.CENTER;
		BackgroundImage bgImg = new BackgroundImage(sportFieldImage, null, null, bgPos, null);
		Background bg = new Background(bgImg);
		field.setBackground(bg);
		rootStackPane.getChildren().add(field);
	}

	public StackPane getRootStackPane()
	{
		return rootStackPane;
	}
}
