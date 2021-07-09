package backend;

public final class BoardInitializer {
    private final int width;
    private final int height;
    private final int numBombs;
    private final int xNotABomb;
    private final int yNotABomb;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public int getxNotABomb() {
        return xNotABomb;
    }

    public int getyNotABomb() {
        return yNotABomb;
    }

    public BoardInitializer(int width, int height, int numBombs, int xNotABomb, int yNotABomb) {
        this.width = width;
        this.height = height;
        this.numBombs = numBombs;
        this.xNotABomb = xNotABomb;
        this.yNotABomb = yNotABomb;
    }

    public boolean isValid() {
        return width > 0 && height > 0 &&
                numBombs < width * height &&
                xNotABomb >= 0 && xNotABomb < width &&
                yNotABomb >= 0 && yNotABomb < height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardInitializer that = (BoardInitializer) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        if (numBombs != that.numBombs) return false;
        if (xNotABomb != that.xNotABomb) return false;
        return yNotABomb == that.yNotABomb;
    }

    public int indexFromIndex2D(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0) throw new IndexOutOfBoundsException();
        if (y < 0) throw new IndexOutOfBoundsException();
        if (x >= width) throw new IndexOutOfBoundsException();
        if (y >= height) throw new IndexOutOfBoundsException();

        return y * width + x;
    }
}
