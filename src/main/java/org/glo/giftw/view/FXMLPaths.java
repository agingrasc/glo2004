package org.glo.giftw.view;

public enum FXMLPaths
{
	BOTTOM_TOOL_BAR_PATH("/fxml/BottomToolBar.fxml"),
	CREATION_STACK_PANE_PATH("/fxml/CreationStackPane.fxml"),
	CREATION_TOOL_BAR_PATH("/fxml/CreationToolBar.fxml"),
	DEFAULT_TOOL_BAR_PATH("/fxml/DefaultToolBar.fxml"),
	FIELD_EDITOR_PATH("/fxml/FieldEditor.fxml"),
	GENERAL_PROPERTIES_PANE_PATH("/fxml/GeneralPropertiesPane.fxml"),
	ITEMS_ACCORDION_PATH("/fxml/ItemsAccordion.fxml"),
	LIST_PLACE_HOLDER("/fxml/ListPlaceHolder.fxml"),
	MEDIA_CONTENT_PATH("/fxml/MediaContent.fxml"),
	MEDIA_TOOL_BAR_PATH("/fxml/MediaToolBar.fxml"),
	MODE_TOOL_BAR_PATH("/fxml/ModeToolBar.fxml"),
	NEW_OBSTACLE_PATH("/fxml/NewObstacle.fxml"),
	NEW_SPORT_PATH("/fxml/NewSport.fxml"),
	NEW_STRATEGY_PATH("/fxml/NewStrategy.fxml"),
	OBSTACLE_PROPERTIES_PANE_PATH("/fxml/ObstaclePropertiesPane.fxml"),
	OPEN_LIST_ITEM("/fxml/OpenListItem.fxml"),
	OPEN_OBSTACLE_PATH("/fxml/OpenObstacle.fxml"),
	OPEN_OBSTACLE_TOOL_BAR_PATH("/fxml/OpenObstacleToolBar.fxml"),
	OPEN_SPORT_PATH("/fxml/OpenSport.fxml"),
	OPEN_SPORT_TOOL_BAR_PATH("/fxml/OpenSportToolBar.fxml"),
	OPEN_STRATEGY_PATH("/fxml/OpenStrategy.fxml"),
	OPEN_STRATEGY_TOOL_BAR_PATH("/fxml/OpenStrategyToolBar.fxml"),
	PLAYER_PROPERTIES_PANE_PATH("/fxml/PlayerPropertiesPane.fxml"),
	PROJECTILE_PROPERTIES_PANE_PATH("/fxml/ProjectilePropertiesPane.fxml"),
	ROOT_LAYOUT_PATH("/fxml/RootLayout.fxml");
	
	private String name = "";
	
	FXMLPaths(String name)
	{
		this.name = name;
	}
	   
	public String toString()
	{
		return name;
	}
}
