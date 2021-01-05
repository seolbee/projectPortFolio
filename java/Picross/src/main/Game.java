package main;

import java.sql.Time;
import java.util.Arrays;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Game {
	
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	private Block[][] board;
	
	private int length;
	
	private double width;
	
	private int gap = 1;
	
	private String xy;
	private String[] pointList;
	
	private boolean gameOver = false;
	
	private boolean gameClear = false;
	
	private VBox vbox;
	
	private HBox hbox;
	
	private AnchorPane pane;
	
	public Timer timer;
	
	private int id;
	
	public boolean isTimer = false;
	
	public Game(Canvas canvas, VBox vbox, HBox hbox, AnchorPane pane) {
		this.canvas = canvas;
		this.vbox = vbox;
		this.hbox = hbox;
		this.pane = pane;
		this.gc = this.canvas.getGraphicsContext2D();
	}

	public boolean MkBoard(int pointY, int pointX) {
		for(int i = 0; i< pointList.length; i++) {
			String[] point = pointList[i].split(":"); 
			int y =(int) Integer.parseInt(point[0]);
			int x =(int) Integer.parseInt(point[1]);
			if(pointY == y && pointX ==x) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}
	
	public void click(MouseEvent e) {
		if(gameOver || gameClear) return;
		double mouseX = e.getX();
		double mouseY = e.getY();
		
		int bs = (int) this.width + 1;
		
		if(mouseX % bs < gap || mouseY % bs < gap) return;
		int x = (int) mouseX / bs;
		int y = (int) mouseY / bs;
		
		if(x >=this.length || y >=this.length) return;
		
		MouseButton btn = e.getButton();
		if(btn == MouseButton.SECONDARY) {
			if(board[y][x].isClick()) {
				board[y][x].setColor(Color.BLACK);
				board[y][x].setClick(false);
			} else {
				board[y][x].setColor(Color.DIMGRAY);
				board[y][x].setClick(true);
			}
		}else if(btn == MouseButton.PRIMARY){
			if(board[y][x].isClick()) return;
			if(!board[y][x].isSetBoolean()) {
				this.gameOver = true;
				this.timer.setStart(false);
				board[y][x].setColor(Color.RED);
			}else {
				board[y][x].setCheck(true);
				this.checkClear();
				board[y][x].setColor(Color.WHITE);
			}
		}
		this.render(gc);
	}
	
	public void checkClear() {
		boolean clear = true;
		for(int i = 0; i< pointList.length; i++) {
			String[] point = pointList[i].split(":"); 
			int y =(int) Double.parseDouble(point[0]);
			int x =(int) Double.parseDouble(point[1]);
			if(!board[y][x].isCheck()) {
				clear = false;
				break;
			}
		}
		if(clear) {
			this.gameClear = true;
			timer.setStart(false);
			this.sendRank();
		}
	}
	
	public void render(GraphicsContext gc) {
			for(int i = 0; i < this.length; i++) {
				for(int j = 0; j < this.length; j++) {
					double x = 1 + (width + 1) * j;
					double y = 1 + (width + 1) * i;
					gc.setFill(board[i][j].getColor());
					gc.fillRect(x, y, width, width);
				}
			}
			if(gameOver) {
				gc.setStroke(Color.WHITE);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(new Font("Arial", 40));
				gc.setFill(Color.GRAY);
				gc.fillText("game Over", this.canvas.getWidth() / 2, this.canvas.getHeight()/2);
			}
			if(gameClear) {
				gc.setStroke(Color.WHITE);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setFont(new Font("Arial", 40));
				gc.setFill(Color.GRAY);
				gc.fillText("game Clear", this.canvas.getWidth() / 2, this.canvas.getHeight()/2);
			}
	}
	
	public void setLabel() {
		for(int i = 0; i<this.length; i++) {
			int count = 0;
			HBox hbox = new HBox();
			hbox.setMinHeight(width+1);
			hbox.setAlignment(Pos.TOP_RIGHT);
			for(int j = 0; j<this.length; j++) {
				if(!board[i][j].isSetBoolean()) {
					if(count == 0) continue;
					Label label = new Label();
					label.setPadding(new Insets(0, 0.1, 0, 0.1));
					label.setText(count+"");
					label.setFont(new Font("Arial", width / 2));
					label.setTextFill(Color.WHITE);
					hbox.getChildren().add(label);
					count = 0;
				} else {
					count++;
				}
			}
			if(count != 0) {
				Label label = new Label();
				label.setPadding(new Insets(0, 0.1, 0, 0.1));
				label.setText(count+"");
				label.setFont(new Font("Arial", width / 2));
				label.setTextFill(Color.WHITE);
				hbox.getChildren().add(label);
			}
			vbox.getChildren().add(hbox);
		}
		
		for(int i = 0; i<this.length; i++) {
			int count = 0;
			VBox vbox = new VBox();
			vbox.setMinWidth(width+1);
			vbox.setAlignment(Pos.CENTER);
			for(int j = 0; j<this.length; j++) {
				if(!board[j][i].isSetBoolean()) {
					if(count == 0) continue;
					Label label = new Label();
					label.setPadding(new Insets(0, 0.1, 0, 0.1));
					label.setText(count+"");
					label.setTextFill(Color.WHITE);
					label.setFont(new Font("Arial", width / 2));
					vbox.getChildren().add(label);
					count = 0;
				}else {
					count++;
				}
			}
			if(count != 0) {
				Label label = new Label();
				label.setPadding(new Insets(0, 0.1, 0, 0.1));
				label.setText(count+"");
				label.setTextFill(Color.WHITE);
				label.setFont(new Font("Arial", width / 2));
				vbox.getChildren().add(label);
			}
			
			hbox.getChildren().add(vbox);
		}
	}
	
	public void gameStart(String location, int xlength, int id, Timer timer){
		vbox.getChildren().clear();
		hbox.getChildren().clear();
		this.gameOver = false;
		this.gameClear = false;
		this.xy = location;
		this.length = xlength;
		this.id = id;
		this.timer = timer;
		pointList = xy.split(",");
		this.width = Math.ceil(this.canvas.getWidth() / (xlength + gap));
		gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getWidth());
		board = new Block[length][length];
		gc.setFill(Color.BLACK);
		for(int i = 0; i < this.length; i++) {
			for(int j = 0; j < this.length; j++) {
				double x = 1 + (width + 1) * j;
				double y = 1 + (width + 1) * i;
				gc.fillRect(x, y, width, width);
				gc.setStroke(Color.WHITE);
				gc.setLineWidth(0.1);
				gc.strokeRect(x, y, width, width);
				board[i][j] = new Block(MkBoard(i, j), Color.BLACK);
				board[i][j].setCheck(false);
				board[i][j].setClick(false);
			}
		}
		setLabel();
		this.isTimer= true;
		timer.start();
	}
	
	public Time send() {
		return new Time(timer.hour, timer.minute, timer.second);
	}
	
	public void sendRank() {
		Main.app.setRank(send(), id);
		Main.app.loadPane("rank");
	}
}
