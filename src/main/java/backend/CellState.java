package backend;

public class CellState {
    private boolean isBomb;
    private boolean isOpen;
    private boolean isFlagged;
    private int number;

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        assert !(open && isFlagged);
        isOpen = open;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        assert !(flagged && isOpen);
        isFlagged = flagged;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        assert number > 0;
        this.number = number;
    }

    public CellState(boolean isBomb, boolean isOpen, boolean isFlagged, int number) {
        assert !(isOpen && isFlagged);
        assert number > 0;

        this.isBomb = isBomb;
        this.isOpen = isOpen;
        this.isFlagged = isFlagged;
        this.number = number;
    }
}
