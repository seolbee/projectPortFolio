package application;
	
import Util.contentVO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.UpdateController;
import views.ViewController;
import views.WriteController;

public class Main extends Application {
	public static Main app;
	
	private static Stage WritePopupStage;
	
	private WriteController writePopupController;
	
	private static Stage ViewStage;
	private ViewController viewController;
	
	private static Stage updateStage;
	
	private UpdateController updateController;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			app = this;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/main.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			FXMLLoader writePopLoader = new FXMLLoader();
			writePopLoader.setLocation(getClass().getResource("/views/WritePopup.fxml"));
			
			FXMLLoader viewPopuploader = new FXMLLoader();
			viewPopuploader.setLocation(getClass().getResource("/views/View.fxml"));
			
			FXMLLoader updatePopuploader = new FXMLLoader();
			updatePopuploader.setLocation(getClass().getResource("/views/update.fxml"));
			
			WritePopupStage = new Stage();
			WritePopupStage.setTitle("글쓰기");
			WritePopupStage.initModality(Modality.WINDOW_MODAL);
			WritePopupStage.initOwner(primaryStage);
			
			ViewStage = new Stage();
			ViewStage.setTitle("상세보기");
			ViewStage.initModality(Modality.WINDOW_MODAL);
			ViewStage.initOwner(primaryStage);
			
			updateStage = new Stage();
			updateStage.setTitle("수정하기");
			updateStage.initModality(Modality.WINDOW_MODAL);
			updateStage.initOwner(primaryStage);
			
			AnchorPane writePopup = writePopLoader.load();
			Scene WritePopupScene = new Scene(writePopup);
			WritePopupStage.setScene(WritePopupScene);
			
			AnchorPane viewPopup = viewPopuploader.load();
			Scene viewPopupScene = new Scene(viewPopup);
			ViewStage.setScene(viewPopupScene);
			
			AnchorPane updatePopup = updatePopuploader.load();
			Scene updatePopupScene = new Scene(updatePopup);
			updateStage.setScene(updatePopupScene);
			
			writePopupController = writePopLoader.getController();
			writePopupController.setDialogStage(WritePopupStage);
			
			viewController = viewPopuploader.getController();
			viewController.setDialog(ViewStage);
			
			updateController = updatePopuploader.getController();
			updateController.setDialog(updateStage);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void openWritePopup() {
		WritePopupStage.show();
	}
	
	public void openViewPopup(contentVO item) {
		viewController.setText(item);
		ViewStage.show();
	}
	
	public void openUpdatePopup(contentVO item) {
		updateController.setText(item);
		updateStage.show();
	}
}
