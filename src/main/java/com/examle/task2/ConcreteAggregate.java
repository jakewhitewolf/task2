package com.examle.task2;

import javafx.scene.image.Image;

import java.nio.file.Paths;

public class ConcreteAggregate implements Aggregate {
    private final String filetopic;

    public ConcreteAggregate(String filetopic) {
        this.filetopic = filetopic;
    }

    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }

    private class ImageIterator implements Iterator {

        private int current = 0;

        private Image getImage(int index) {
            String filename = Paths
                    .get("src/main/resources/img/" + filetopic + index + ".jpg")
                    .toUri()
                    .toString();
            return new Image(filename);
        }

        @Override
        public boolean hasNext(int step) {
            Image img = getImage(current + step);
            return !img.isError();
        }

        @Override
        public Object next() {
            if (hasNext(1)) {
                return getImage(++current);
            }
            current = 1;
            return getImage(1);
        }

        @Override
        public Object preview() {
            if (current > 1 && hasNext(-1)) {
                return getImage(--current);
            }

            int i = 1;
            while (!getImage(i).isError()) {
                i++;
            }
            current = i - 1;
            return getImage(current);
        }
    }

    public int countSlides() {
        int i = 1;
        while (true) {
            String filename = Paths
                    .get("src/main/resources/img/" + filetopic + i + ".jpg")
                    .toUri()
                    .toString();
            Image img = new Image(filename);
            if (img.isError()) {
                break;
            }
            i++;
        }
        return i - 1;
    }
}
