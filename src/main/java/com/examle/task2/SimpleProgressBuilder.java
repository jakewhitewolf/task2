package com.examle.task2;

public class SimpleProgressBuilder implements ProgressBuilder {
    private final Progress progress;

    public SimpleProgressBuilder() {
        this.progress = new Progress();
    }

    @Override
    public void setCurrentSlide(int current) {
        progress.setCurrentSlide(current);
    }

    @Override
    public void setTotalSlides(int total) {
        progress.setTotalSlides(total);
    }

    @Override
    public void buildMessage() {
        int current = progress.getCurrentSlide();
        int total = progress.getTotalSlides();
        int remaining = Math.max(total - current, 0);

        String word = getSlidesWord(remaining);
        String msg = "Осталось ещё " + remaining + " " + word;

        progress.setMessage(msg);
    }

    @Override
    public Progress getProgress() {
        return progress;
    }

    private String getSlidesWord(int n) {
        n = Math.abs(n) % 100;
        int n1 = n % 10;
        if (n > 10 && n < 20) return "слайдов";
        if (n1 > 1 && n1 < 5) return "слайда";
        if (n1 == 1) return "слайд";
        return "слайдов";
    }
}
