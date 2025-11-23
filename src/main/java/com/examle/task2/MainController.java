package com.examle.task2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MainController {
    @FXML
    private ImageView screen;

    private final ConcreteAggregate conaggr = new ConcreteAggregate("img");
    private final Iterator iter = conaggr.getIterator();

    private Timeline timeline;

    @FXML
    private javafx.scene.control.TextField delayField;

    private double delayMillis = 1000;

    @FXML
    public void initialize() {
        Image first = (Image) iter.next();
        if (first != null) {
            screen.setImage(first);
        }

        buildTimeline();
    }

    private void buildTimeline() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame frame = new KeyFrame(
                Duration.millis(delayMillis),
                this::onTimerTick
        );

        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(frame);
    }

    private void onTimerTick(ActionEvent event) {
        Image img = (Image) iter.next();
        if (img != null) {
            screen.setImage(img);
        }
    }

    @FXML
    private void onNextButtonClick() {
        Image img = (Image) iter.next();
        if (img != null) {
            screen.setImage(img);
        }
    }

    @FXML
    private void onPrevButtonClick() {
        Image img = (Image) iter.preview();
        if (img != null) {
            screen.setImage(img);
        }
    }

    @FXML
    private void onStartAutoClick() {
        if (timeline != null) {
            timeline.play();
        }
    }

    @FXML
    private void onStopAutoClick() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public void setDelayMillis(double delayMillis) {
        this.delayMillis = delayMillis;
        buildTimeline();
    }

    @FXML
    private void onDelayApply() {
        try {
            double value = Double.parseDouble(delayField.getText());
            if (value <= 0) {
                return;
            }
            setDelayMillis(value);
        } catch (NumberFormatException e) {
            System.out.println("Введено неверное значение");
        }
    }

}