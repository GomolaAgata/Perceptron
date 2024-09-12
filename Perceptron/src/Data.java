import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public List<Iris> getData(String filePath, boolean isFirst) throws IOException {

        List<Iris> data = new ArrayList<>();
        String line;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        while ((line = bufferedReader.readLine()) != null) {
            String[] strVal = line.split(",");
            double[] values = new double[strVal.length-1];
            int flag;
            if(isFirst) {
                flag = convertClassFlag(strVal[strVal.length - 1]);
            }else{
                flag = convertClassFlagForOther(strVal[strVal.length - 1]);
            }
            String answer = strVal[strVal.length-1];

            for (int i = 0; i < strVal.length - 1; i++) {
                values[i] = Double.parseDouble(strVal[i]);
            }
            if(flag!=3){
            data.add(new Iris(values, flag, answer));}
        }

        bufferedReader.close();
        return data;
    }

    public int convertClassFlag(String flag) {
        return switch (flag) {
            case "Iris-virginica", "Iris-versicolor" -> 0;
            case "Iris-setosa"->1;
            default -> 21;
        };
    }
    public int convertClassFlagForOther(String flag) {
        return switch (flag) {
            case "Iris-virginica" -> 1;
            case "Iris-versicolor"->0;
            case "Iris-setosa"->3;
            default -> 21;
        };

    }
}

