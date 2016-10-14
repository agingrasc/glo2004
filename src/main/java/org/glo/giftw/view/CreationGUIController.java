package org.glo.giftw.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CreationGUIController
{
	
	@FXML
    private ListView<ImageView> playersListView;

    @FXML
    private ListView<ImageView> ObstaclesListView;

    @FXML
    private ListView<ImageView> projectilesListView;

	@FXML
	void mouseClickedDelete(MouseEvent event)
	{
		System.out.println("mouseClickedDelete");
	}

	@FXML
	void mouseClickedNext(MouseEvent event)
	{
		System.out.println("mouseClickedNext");
	}

	@FXML
	void mouseClickedPrevious(MouseEvent event)
	{
		System.out.println("mouseClickedPrevious");
	}

	@FXML
	void mouseClickedRedo(MouseEvent event)
	{
		System.out.println("mouseClickedRedo");
	}

	@FXML
	void mouseClickedUndo(MouseEvent event)
	{
		System.out.println("mouseClickedUndo");
	}

	@FXML
	void mouseClickedZoomIn(MouseEvent event)
	{
		System.out.println("mouseClickedZoomIn");
	}

	@FXML
	void mouseClickedZoomOut(MouseEvent event)
	{
		System.out.println("mouseClickedZoomOut");
	}

	@FXML
	private void initialize()
	{
		Image image1 = new Image(getClass().getClassLoader().getResourceAsStream("images/next.png"));
		ImageView joueur1 = new ImageView(image1);
		joueur1.setImage(image1);
		Image image2 = new Image(getClass().getClassLoader().getResourceAsStream("images/next.png"));
		ImageView joueur2 = new ImageView(image2);
		joueur1.setImage(image2);
		ObservableList<ImageView> images = FXCollections.observableArrayList(joueur1,joueur2);
		playersListView.setItems(images);
	}
}
