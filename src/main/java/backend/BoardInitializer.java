package backend;

public final class BoardInitializer {
    private final int width;
    private final int height;
    private final int numBombs;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public BoardInitializer(int width, int height, int numBombs, int xNotABomb, int yNotABomb) {
        this.width = width;
        this.height = height;
        this.numBombs = numBombs;
    }

    public boolean isValid() {
        return width > 0 && height > 0 &&
                numBombs < width * height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardInitializer that = (BoardInitializer) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        return numBombs == that.numBombs;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + numBombs;
        return result;
    }

    public int indexFromIndex2D(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0) throw new IndexOutOfBoundsException();
        if (y < 0) throw new IndexOutOfBoundsException();
        if (x >= width) throw new IndexOutOfBoundsException();
        if (y >= height) throw new IndexOutOfBoundsException();

        return y * width + x;
    }
}
