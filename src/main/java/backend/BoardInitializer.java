package backend;

public final class BoardInitializer {
    private int width = 0;
    private int height = 0;
    private int numBombs = 0;

    public BoardInitializer(int width, int height, int numBombs) {
        this.width = width;
        this.height = height;
        this.numBombs = numBombs;
    }

    public BoardInitializer() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public void setNumBombs(int numBombs) {
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
