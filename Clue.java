
public enum Clue {
    Black(0, Color.Black),
    White1(1, Color.White),
    White2(2, Color.White),
    White3(3, Color.White),
    White4(4, Color.White),
    White5(5, Color.White),
    White6(6, Color.White),
    White7(7, Color.White),
    White8(8, Color.White),
    White9(9, Color.White);

    private final int value;
    private final Color color;

    Clue(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public Color toColor() {
        return color;
    }
}
