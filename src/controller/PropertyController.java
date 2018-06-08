package controller;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.MyLine;
import model.MyShape;

public class PropertyController {

	private TextField textFieldH;
	private TextField textFieldW;
	private TextField textFieldX;
	private TextField textFieldY;
	private TextArea textArea;
	private Object shape;
	private Button button;
	private DrawController drawController;
	private ImageView workingImaging;

	public ImageView getWorkingImaging() {
		return workingImaging;
	}

	public void setWorkingImaging(ImageView workingImaging) {
		this.workingImaging = workingImaging;
	}

	public PropertyController(TextField x, TextField y, TextField W, TextField H, TextArea textArea) {
		this.textFieldH = H;
		this.textFieldW = W;
		this.textFieldY = y;
		this.textFieldX = x;
		this.textArea = textArea;
	}

	public void setWorkShape(Object object) {
		this.shape = object;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public void update() {
		if (shape != null) {
			setImage(shape.getClass().getSimpleName());
			System.out.println(shape.getClass());
			if (shape instanceof MyShape) {
				textFieldH.setText(String.valueOf((((MyShape) shape).getHeight())));
				textFieldW.setText(String.valueOf((((MyShape) shape).getWidth())));
				textFieldX.setText(String.valueOf((((MyShape) shape).getX())));
				textFieldY.setText(String.valueOf((((MyShape) shape).getY())));
				textArea.setText(String.valueOf((((MyShape) shape).getText().getText())));
			} else {
				textFieldH.setText(String.valueOf((((MyLine) shape).getEY())));
				textFieldW.setText(String.valueOf((((MyLine) shape).getEX())));
				textFieldX.setText(String.valueOf((((MyLine) shape).getSX())));
				textFieldY.setText(String.valueOf((((MyLine) shape).getSY())));
				System.out.println("???");
			}
		} else {
			textArea.setText("");
			textFieldH.setText("");
			textFieldW.setText("");
			textFieldX.setText("");
			textFieldY.setText("");
		}
	}

	public void setImage(String kind){
		switch (kind) {
		case "CurvedRectangle":
			workingImaging.setImage(new Image(new File("image/document.png").getAbsoluteFile().toURI().toString()));
			break;
		case "Decision":
			workingImaging.setImage(new Image(new File("image/decision.png").getAbsoluteFile().toURI().toString()));
			break;
		case "InputRectangle":
			workingImaging.setImage(new Image(new File("image/data.png").getAbsoluteFile().toURI().toString()));
			break;
		case "MyCircle":
			workingImaging.setImage(new Image(new File("image/prepare.png").getAbsoluteFile().toURI().toString()));
			break;
		case "RoundRectangle":
			workingImaging.setImage(new Image(new File("image/start.png").getAbsoluteFile().toURI().toString()));
			break;
		case "MyRectangle":
			workingImaging.setImage(new Image(new File("image/process.png").getAbsoluteFile().toURI().toString()));
			break;
		case "MyLine":
			workingImaging.setImage(new Image(new File("image/arrow.png").getAbsoluteFile().toURI().toString()));
			//workingImaging.setImage(new Image("file:////"+System.getProperty("user.dir")+"/image/arrow.png"));
			break;
		case "BrokenLine":
			workingImaging.setImage(new Image(new File("image/BrokenLine.png").getAbsoluteFile().toURI().toString()));
			//workingImaging.setImage(new Image("file:////"+System.getProperty("user.dir")+"/image/BrokenLine.png"));
		break;
		default:
			break;
		}
	}

	public void edit() {
		button.setOnMouseClicked(e -> {
			if (shape != null) {
				if (shape instanceof MyShape) {
					MyShape shape = (MyShape) this.shape;
					shape.setX(Double.parseDouble(textFieldX.getText()));
					shape.setY(Double.parseDouble(textFieldY.getText()));
					shape.setWidth(Double.parseDouble(textFieldW.getText()));
					shape.setHeight(Double.parseDouble(textFieldH.getText()));
					shape.getText().setText(textArea.getText());
					shape.updateLocation(shape.getX(), shape.getY());
					shape.update();
					drawController.saveChange();
				} else {
					MyLine shape = (MyLine) this.shape;
					shape.setSX(Double.parseDouble(textFieldX.getText()));
					shape.setSY(Double.parseDouble(textFieldY.getText()));
					shape.setEX(Double.parseDouble(textFieldW.getText()));
					shape.setEY(Double.parseDouble(textFieldH.getText()));
					shape.getText().setText(textArea.getText());
					shape.setShape();
					drawController.saveChange();
				}
			}
		});

	}

	public DrawController getDrawController() {
		return drawController;
	}

	public void setDrawController(DrawController drawController) {
		this.drawController = drawController;
	}

}
