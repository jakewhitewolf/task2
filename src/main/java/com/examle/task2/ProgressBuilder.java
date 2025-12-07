package com.examle.task2;

public interface ProgressBuilder {
    void setCurrentSlide(int current);
    void setTotalSlides(int total);
    void buildMessage();
    Progress getProgress();
}
