package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.sun.javafx.image.impl.ByteIndexed.Getter;
import com.sun.javafx.stage.StageHelper;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileMenuController {

	public void newDrawingArea(Compiler compiler) {
		compiler.compireProduce("");
	}

	public void saveDrawingArea(DrawController drawController) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Save Directory");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DAT", "*.dat"));
		File saveFile = fileChooser.showSaveDialog(stage);
		if (saveFile != null) {
			try (PrintWriter saver = new PrintWriter(saveFile)) {
				 saver.println(drawController.getCode());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void getDrawingArea(Compiler compiler) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DAT", "*.dat"));
		File file = fileChooser.showOpenDialog(stage);
		try (Scanner geter = new Scanner(file)) {
			String code="";
			while(geter.hasNextLine())code+=geter.nextLine();
			compiler.compireProduce(code);
			System.out.println(code);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportDrawingArea(AnchorPane drawingArea) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Save Directory");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
		File saveFile = fileChooser.showSaveDialog(stage);
		System.out.println(saveFile);
		WritableImage image = drawingArea.snapshot(new SnapshotParameters(), null);
		System.out.println(image);
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
