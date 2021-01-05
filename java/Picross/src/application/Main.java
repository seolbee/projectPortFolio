package application;
	
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import main.Game;
import views.MainController;
import views.MasterController;
import views.RankController;
import views.StageController;


public class Main extends Application {
	public static Main app;
	
	public Game game;
	
	public static StackPane stPane;
	
	public Map<String, MasterController> controllerMap = new HashMap();
	
	@Override
	public void start(Stage primaryStage) {
		app = this;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/Main.fxml"));
			stPane = loader.load();
			
			MasterController mc = loader.getController();
			mc.setRoot(stPane);
			controllerMap.put("main", mc);
			
			FXMLLoader fontLoader = new FXMLLoader();
			fontLoader.setLocation(getClass().getResource("/views/font.fxml"));
			AnchorPane fontPage = fontLoader.load();
			
			MasterController fc = fontLoader.getController();
			fc.setRoot(fontPage);
			controllerMap.put("font", fc);
			
			FXMLLoader QueueLoader = new FXMLLoader();
			QueueLoader.setLocation(getClass().getResource("/views/Queue.fxml"));
			AnchorPane QueuePage = QueueLoader.load();
			
			MasterController qc = QueueLoader.getController();
			qc.setRoot(QueuePage);
			controllerMap.put("queue", qc);
			
			FXMLLoader StageLoader = new FXMLLoader();
			StageLoader.setLocation(getClass().getResource("/views/Stage.fxml"));
			AnchorPane StagePage = StageLoader.load();
			
			MasterController sc = StageLoader.getController();
			sc.setRoot(StagePage);
			controllerMap.put("stage", sc);
			
			FXMLLoader RankLoader = new FXMLLoader();
			RankLoader.setLocation(getClass().getResource("/views/Rank.fxml"));
			AnchorPane RankPage = RankLoader.load();
			
			MasterController rc = RankLoader.getController();
			rc.setRoot(RankPage);
			controllerMap.put("rank", rc);
			
			Scene scene = new Scene(stPane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stPane.getChildren().add(StagePage);
			stPane.getChildren().add(QueuePage);
			stPane.getChildren().add(fontPage);
			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		        @Override
		        public void handle(final WindowEvent event) {
		        	if(Main.app.game.isTimer) {
		        		Main.app.game.timer.setStart(false);
		        	}
		        }
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void slideOut(Pane pane) {
		Timeline timeline = new Timeline();
		KeyValue toRight = new KeyValue(pane.translateXProperty(), 800);
		KeyValue fadeOut = new KeyValue(pane.opacityProperty(), 800);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500), (e)->{
			stPane.getChildren().remove(pane);
		}, toRight, fadeOut);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void loadPane(String name) {
		MasterController c = controllerMap.get(name);
		Pane pane = c.getRoot();
		stPane.getChildren().add(pane);
		
		pane.setTranslateX(-800);
		pane.setOpacity(0);
		Timeline timeline = new Timeline();
		KeyValue toRight = new KeyValue(pane.translateXProperty(), 0);
		KeyValue fadeOut = new KeyValue(pane.opacityProperty(), 1);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500), toRight, fadeOut);
		
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}
	
	public void setLevel(String level) {
		StageController sc = (StageController) controllerMap.get("stage");
		sc.setLevel(level);
		sc.setList();
	}
	
	public void setMainInfo(String location, int length, int id) {
		MainController mc = (MainController) controllerMap.get("main");
		mc.setLength(length);
		mc.setLocation(location);
		mc.setId(id);
		mc.setGame();
	}

	public void setRank(Time time, int id) {
		RankController rc = (RankController) controllerMap.get("rank");
		rc.setTime(time);
		rc.setId(id);
	}
}
