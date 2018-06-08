package model;

import controller.DrawController;
import controller.RootLayoutController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

public class SwitchButton extends Label {
	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(true);
	private RootLayoutController rootLayoutController;

	public SwitchButton(RootLayoutController rootLayoutController) {
		this.rootLayoutController = rootLayoutController;
		Button switchBtn = new Button();
		switchBtn.setPrefWidth(40);
		switchBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				switchedOn.set(!switchedOn.get());
			}
		});

		setGraphic(switchBtn);
		switchedOn.set(true);
		setCode();
		setGraph();
		switchedOn.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
				if (t1) {
					setGraph();
					rootLayoutController.changeModel();
				} else {
					setCode();
					rootLayoutController.changeModel();
				}
			}
		});

	}

	public void setGraph() {
		setText("Graph");
		setStyle("-fx-background-color: green;-fx-text-fill:white;");
		setContentDisplay(ContentDisplay.RIGHT);
	}

	public void setCode() {
		setText("Code");
		setStyle("-fx-background-color: grey;-fx-text-fill:black;");
		setContentDisplay(ContentDisplay.LEFT);
	}

	public SimpleBooleanProperty switchOnProperty() {
		return switchedOn;
	}
}