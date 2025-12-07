package com.examle.task2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TextField delayField;

    @FXML
    private Label progressLabel;

    private double delayMillis = 1000;

    private final ProgressDirector progressDirector = new ProgressDirector();
    private final ProgressBuilder progressBuilder = new SimpleProgressBuilder();

    private int totalSlides;
    private int currentSlideIndex = 1;

    @FXML
    public void initialize() {
        totalSlides = conaggr.countSlides();

        Image first = (Image) iter.next();
        currentSlideIndex = 1;

        if (first != null) {
            screen.setImage(first);
        }

        updateProgressLabel();

        buildTimeline();
    }

    private void updateProgressLabel() {
        if (totalSlides <= 0) {
            progressLabel.setText("Слайдов нет");
            return;
        }

        Progress progress = progressDirector.build(
                progressBuilder,
                currentSlideIndex,
                totalSlides
        );

        progressLabel.setText(progress.toString());
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
            currentSlideIndex++;
            if (currentSlideIndex > totalSlides) {
                currentSlideIndex = 1;
            }
            screen.setImage(img);
            updateProgressLabel();
        }
    }

    @FXML
    private void onNextButtonClick() {
        Image img = (Image) iter.next();
        if (img != null) {
            currentSlideIndex++;
            if (currentSlideIndex > totalSlides) {
                currentSlideIndex = 1; // по кругу
            }
            screen.setImage(img);
            updateProgressLabel();
        }
    }

    @FXML
    private void onPrevButtonClick() {
        Image img = (Image) iter.preview();
        if (img != null) {
            currentSlideIndex--;
            if (currentSlideIndex < 1) {
                currentSlideIndex = totalSlides; // по кругу назад
            }
            screen.setImage(img);
            updateProgressLabel();
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