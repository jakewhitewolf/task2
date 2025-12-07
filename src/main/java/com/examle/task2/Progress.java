package com.examle.task2;

public class Progress {
    private int currentSlide;
    private int totalSlides;
    private String message;

    public int getCurrentSlide() {
        return currentSlide;
    }

    public void setCurrentSlide(int currentSlide) {
        this.currentSlide = currentSlide;
    }

    public int getTotalSlides() {
        return totalSlides;
    }

    public void setTotalSlides(int totalSlides) {
        this.totalSlides = totalSlides;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
