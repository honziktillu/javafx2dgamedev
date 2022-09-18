package com.example.kostickaxddddd;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Canvas canvas;
    public AnchorPane mainLayout;
    private GraphicsContext gc;
    private ArrayList<String> input = new ArrayList<>();
    private Player player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player = new Player(0, 0, 50, 50, Paint.valueOf("RED"));
        gc = canvas.getGraphicsContext2D();
        Platform.runLater(() -> mainLayout.requestFocus());
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoop();
            }
        };
        animationTimer.start();
    }

    private void gameLoop() {
        render();
        handleMovement();
    }

    private void render() {
        gc.setFill(Paint.valueOf("WHITE"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(player.getPaint());
        gc.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    private void handleMovement() {
        if (input.contains("w")) {
            player.setY(player.getY() - 1);
        }
        if (input.contains("s")) {
            player.setY(player.getY() + 1);
        }
        if (input.contains("a")) {
            player.setX(player.getX() - 1);
        }
        if (input.contains("d")) {
            player.setX(player.getX() + 1);
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (input.contains(keyEvent.getText())) return;
        input.add(keyEvent.getText());
    }

    public void keyReleased(KeyEvent keyEvent) {
        input.remove(keyEvent.getText());
    }
}