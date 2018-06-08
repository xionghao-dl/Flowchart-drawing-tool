package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.BrokenLine;
import model.CurvedRectangle;
import model.Decision;
import model.DoubleBrokenLine;
import model.InputRectangle;
import model.MyCircle;
import model.MyLine;
import model.MyRectangle;
import model.MyShape;
import model.RoundRectangle;

public class ShapeFactory {
	private int countShapeID = 0;
	private AnchorPane drawingArea;
	private DrawController drawController;

	public ShapeFactory(AnchorPane drawingArea, DrawController drawController) {
		this.drawingArea = drawingArea;
		this.drawController = drawController;
	}

	public MyRectangle newMyRectangle(double x, double y) {
		countShapeID++;
		return new MyRectangle(x, y, countShapeID);
	}

	public RoundRectangle newRoundRectangle(double x, double y) {
		countShapeID++;
		return new RoundRectangle(x, y, countShapeID);
	}

	public Decision newDecision(double x, double y) {
		countShapeID++;
		return new Decision(x, y, countShapeID);
	}

	public InputRectangle newInputRectangle(double x, double y) {
		countShapeID++;
		return new InputRectangle(x, y, countShapeID);
	}

	public MyCircle newMyCircle(double x, double y) {
		countShapeID++;
		return new MyCircle(x, y, countShapeID);
	}

	public CurvedRectangle newCurvedRectangle(double x, double y) {
		countShapeID++;
		return new CurvedRectangle(x, y, countShapeID);
	}

	public MyLine newMyLine(double sx, double sy, double ex, double ey) {
		countShapeID++;
		return new MyLine(sx, sy, ex, ey, countShapeID);
	}

	public MyLine newBrokenLine(double sx, double sy, double ex, double ey) {
		countShapeID++;
		return new BrokenLine(sx, sy, ex, ey, countShapeID);
	}

	public MyLine newDoubleBrokenLine(double sx, double sy, double ex, double ey, double ax) {
		countShapeID++;
		return new DoubleBrokenLine(sx, sy, ex, ey, ax, countShapeID);
	}

	public DrawController getDrawController() {
		return drawController;
	}

	public void setDrawController(DrawController drawController) {
		this.drawController = drawController;
	}

	public void produce(String kind, double x, double y, double width, double height, String text, int id) {
		if (kind == null)
			return;
		kind = kind.replaceAll("Image", "");
		if (kind.indexOf("Line") != -1) {
			MyLine line = produceMyLine(kind, x, y, width, height);
			line.getText().setText(text);
			line.setFactoryID(id);
			line.setShape();
		} else {
			MyShape shape = productMyShape(kind, x, y, width, height, text);
			shape.getText().setText(text);
			shape.setFactoryID(id);
			shape.update();
		}
	}
	public void produce(String kind, double x, double y,double ex,double ey, double aX,String text, int id) {
		 if(kind == null)return;
		 MyLine shape= produceMyLine(kind,x, y, ex, ey, aX);
		 drawController.regristeLine(shape);
	}
	public void produce(String kind, double x, double y, double width, double height, String text, int id, String css) {
		if (kind == null)
			return;
		kind = kind.replaceAll("Image", "");
		if (kind.indexOf("Line") != -1) {
			MyLine line = produceMyLine(kind, x, y, width, height);
		} else {
			MyShape shape = productMyShape(kind, x, y, width, height, text);
			shape.getText().setText(text);
			shape.setCSS(css);
			shape.update();
		}
	}

	public void produce(String kind, double x, double y, double width, double height, String text) {
		if (kind == null)
			return;
		kind = kind.replaceAll("Image", "");
		if (kind.indexOf("Line") != -1) {
			MyLine line = produceMyLine(kind, x, y, width, height);
		} else {
			MyShape shape = productMyShape(kind, x, y, width, height, text);
			shape.getText().setText(text);
			shape.update();
		}
	}

	public int getCountShapeID() {
		return countShapeID;
	}

	public void setCountShapeID(int countShapeID) {
		this.countShapeID = countShapeID;
	}

	public void produce(String kind, double x, double y) {
		kind = kind.replaceAll("Image", "");
		if (kind.indexOf("Line") != -1) {
			produceMyLine(kind, x, y);
		} else {
			productMyShape(kind, x, y);
		}
	}

	public MyShape productMyShape(String kind) {
		MyShape shape = productMyShape(kind, 300, 300);
		return shape;
	}

	public MyShape productMyShape(String kind, double x, double y, double width, double height, String text) {
		MyShape shape = null;
		switch (kind) {
		case "CurvedRectangle":
			shape = newCurvedRectangle(x, y);
			break;
		case "Decision":
			shape = newDecision(x, y);
			break;
		case "InputRectangle":
			shape = newInputRectangle(x, y);
			break;
		case "MyCircle":
			shape = newMyCircle(x, y);
			break;
		case "RoundRectangle":
			shape = newRoundRectangle(x, y);
			break;
		case "MyRectangle":
			shape = newMyRectangle(x, y);
			break;
		default:
			break;
		}
		shape.setWidth(width);
		shape.setHeight(height);
		shape.getText().setText(text);
		drawController.regriste(shape);
		return shape;
	}

	public MyShape productMyShape(String kind, double x, double y) {
		MyShape shape = null;
		switch (kind) {
		case "CurvedRectangle":
			shape = newCurvedRectangle(x, y);
			break;
		case "Decision":
			shape = newDecision(x, y);
			break;
		case "InputRectangle":
			shape = newInputRectangle(x, y);
			break;
		case "Circular":
			shape = newMyCircle(x, y);
			break;
		case "RoundRectangle":
			shape = newRoundRectangle(x, y);
			break;
		case "Rectangle":
			shape = newMyRectangle(x, y);
			break;
		default:
			break;
		}
		// �������˵Ĳ�ƷҪ���������ߵǼǽ����б����Ժ����
		System.out.println(shape);
		drawController.regriste(shape);
		return shape;
	}

	public MyLine produceMyLine(String kind, double x, double y) {
		MyLine shape = null;
		switch (kind) {
		case "MyLine":
			shape = newMyLine(x, y, x, y + 100);
			break;
		case "BrokenLine":
			shape = newBrokenLine(x, y, x + 100, y + 100);
			break;
		case "DoubleBrokenLine":
			shape = newDoubleBrokenLine(x, y, x, y + 100, x - 100);
			break;
		default:
			break;
		}
		drawController.regristeLine(shape);
		return shape;
	}
	public MyLine produceMyLine(String kind, double x, double y, double ex, double ey,double aX) {
		MyLine  shape = null;
		shape = newDoubleBrokenLine(x, y, ex, ey,aX);
		return shape;
	}
	public MyLine produceMyLine(String kind, double x, double y, double ex, double ey) {
		MyLine shape = null;
		System.out.println(kind);
		switch (kind) {
		case "MyLine":
			shape = newMyLine(x, y, ex, ey);
			break;
		case "BrokenLine":
			shape = newBrokenLine(x, y, ex, ey);
			break;
		default:
			break;
		}
		drawController.regristeLine(shape);
		return shape;
	}
}
