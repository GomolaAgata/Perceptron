import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Data data = new Data();

        List<Iris> trainingData = data.getData("src/irisData/iris-train.txt", true);
        List<Iris> secondTrain = data.getData("src/irisData/iris-train.txt", false);
        List<Iris> testingData = data.getData("src/irisData/iris-test.txt", true);
        List<Iris> secondTest=data.getData("src/irisData/iris-test.txt", false);
        List<Iris> i = new ArrayList<>();

        Perceptron perceptron = new Perceptron(trainingData);

        System.out.println("Enter 'file' to input data from a file or 'input' to pass data directly: ");
        String input;
        do {
            input = scanner.nextLine();
            if (!input.equals("file") && !input.equals("input")) {
                System.out.println("Not recognized command. Please enter 'file' or 'input'.");
            }
        } while (!input.equals("file") && !input.equals("input"));

        if (input.equals("file")) {
            System.out.println("Pass file path");
            String str = scanner.nextLine();
            testingData = data.getData(str, true);
            secondTest = data.getData(str, false);

        } else {

            boolean continueAdding = true;

            while (continueAdding) {
                System.out.println("Enter sepal length: ");
                double sepalLength = scanner.nextDouble();
                System.out.println("Enter sepal width: ");
                double sepalWidth = scanner.nextDouble();
                System.out.println("Enter petal length: ");
                double petalLength = scanner.nextDouble();
                System.out.println("Enter petal width: ");
                double petalWidth = scanner.nextDouble();

                double[] attributes = {sepalLength, sepalWidth, petalLength, petalWidth};
                Iris iris = new Iris(attributes, 0, null);
                i.add(iris);

                scanner.nextLine();

                System.out.println("Do you want to add another vector?(yes/no)");
                String decision = scanner.nextLine().toLowerCase();
                continueAdding = decision.equals("yes");
            }

        }

        Perceptron second = new Perceptron(secondTrain);

        double accuracySecond;
        double correctSecond;
        int resultSecond;

        do {
            second.deltaRule();
            correctSecond = 0;
            for (Iris iris : secondTest) {
                resultSecond = second.activationFunction(iris.getAtributes());

                if (resultSecond == iris.getFlag()) {
                    correctSecond += 1;
                }
            }
            accuracySecond = correctSecond / secondTest.size();
        } while (accuracySecond != 1.0);

        perceptron.learn(testingData, second, input.equals("file"));

        int res;

        for(Iris iris: i){
            res = perceptron.activationFunction(iris.getAtributes());
            if(res==0){
                res=second.activationFunction(iris.getAtributes());
                if(res==0){
                    System.out.println(iris + "\nClassified as Iris-versicolor");
                }else{
                    System.out.println(iris+ "\nClassified as Iris-virginica");
                }
            }else{
                System.out.println(iris+ "\nClassified as Iris-setosa");
            }
        }
    }
}