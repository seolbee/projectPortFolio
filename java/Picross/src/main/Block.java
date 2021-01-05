package main;

import javafx.scene.paint.Color;

public class Block {
	
	private Color color;
	
	private int width;
	
	private int height;
	
	private boolean click = false;
	
	private boolean setBoolean;
	
	private boolean check = false;
	
	public Block(boolean bo, Color color) {
		this.color = color;
		this.setBoolean = bo;
	}

	public boolean isSetBoolean() {
		return setBoolean;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
	public boolean isClick() {
		return click;
	}
	
	public void setClick(boolean click) {
		this.click = click;
	}
	
}
