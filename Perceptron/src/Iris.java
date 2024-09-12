import java.util.Arrays;

public class Iris {
    private double[] atributes;

    private int flag;
    private String answer;

    public Iris(double[] atributes, int flag, String answer) {
        this.answer = answer;
        this.atributes = atributes;
        this.flag = flag;
    }

    public String getAnswer() {
        return answer;
    }

    public double[] getAtributes() {
        return atributes;
    }

    public int getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "Iris{ " +
                "atributes=" + Arrays.toString(atributes) +

                '}';
    }
}
