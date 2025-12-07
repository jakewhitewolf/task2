package com.examle.task2;

public class ProgressDirector {
    public Progress build(ProgressBuilder builder, int current, int total) {
        builder.setCurrentSlide(current);
        builder.setTotalSlides(total);
        builder.buildMessage();
        return builder.getProgress();
    }
}
