package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class MyCircle extends MyShape {
	private Ellipse circle;
	private double radius;
	public MyCircle(double x,double y,int id){
		this(x,y,50.0);
		this.factoryID=id;
	}
	public MyCircle(double x,double y,double radius){
			super(x,y,radius,radius);
			this.x=x;
			this.y=y;
			leftX=x-radius;
			leftY=y-radius;
			this.radius=radius;
			this.circle=new Ellipse(this.x, this.y, radius, radius);
			this.circle.setFill(Color.WHITE);
			this.circle.setStroke(Color.BLACK);
			super.setMyShape(this.circle);
	}
	public void setX(double x) {
		this.x = x;
		setShape();
	}

	public void setY(double y) {
		this.y = y;
		setShape();
	}

	public void setWidth(double width) {
		this.width = width;
		setShape();
	}

	public void setHeight(double height) {
		this.height = height;
		setShape();
	}
	public void Move(double x,double y){
		super.Move(x,y);
		setShape();
	}
	public void setShape(){
			circle.setRadiusX(width);
			circle.setRadiusY(width);
			circle.setCenterX(this.x);
			circle.setCenterY(this.y);
	}

}
