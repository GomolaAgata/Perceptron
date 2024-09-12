import java.util.List;

public class Perceptron {

    private double[] weightVector;
    private double threshold;

    private List<Iris> data;

    public Perceptron(List<Iris> data) {
        this.data=data;
        threshold = 0;
        setInitialWeights(data.get(0).getAtributes());
    }

    private void setInitialWeights(double[] inputVector){

        weightVector = new double[inputVector.length];

        for(int i = 0; i< weightVector.length; i++){
            double randomValue = Math.random() * 10 - 5;
            weightVector[i] = randomValue;
        }
    }

    public void deltaRule(){

        for(Iris iris : data){

            double[] inputVector = iris.getAtributes();
            int result = activationFunction(inputVector);

            for(int i = 0; i< weightVector.length-1;i++){
                double ALPHA = 0.1;
                weightVector[i] = weightVector[i] + (iris.getFlag() - result) * inputVector[i] * ALPHA;
            }
            double BETA = 0.1;
            threshold = threshold + (result - iris.getFlag()) * BETA;

        }
    }

    public int activationFunction(double[] inputVector){

        double dotProduct = 0;

        for(int i = 0; i < weightVector.length;i++){
           dotProduct += weightVector[i] * inputVector[i];
        }
        if(dotProduct >= threshold){
            return 1;
        }else{
            return 0;
        }
    }
    public void learn(List<Iris> testingData, Perceptron second, boolean printMessages) {
        int result;
        String answer;
        String correctAnswer;
        int resultSecond;
        double accuracy;
        double accuracySecond;

        do{
            this.deltaRule();
            double correct = 0;
            double correctSecond = 0;
            int secondCounter = 0;

            for (Iris iris : testingData) {
                if(printMessages){
                System.out.println();}
                result = this.activationFunction(iris.getAtributes());
                if (result == 1) {
                    answer = "Iris-setosa";
                } else {
                    answer = "Not Iris-setosa";
                }
                if (iris.getFlag() == 1) {
                    correctAnswer = "Iris-setosa";
                } else {
                    correctAnswer = "Not Iris-setosa";
                }
                if(printMessages){
                System.out.println(iris+ ", classified as: " + answer + ", correct answer: " + correctAnswer);}
                if (result == iris.getFlag()) {
                    correct += 1;
                }
                if (result == 0) {
                    if(printMessages){
                    System.out.println("Classifing by second perceptron: ----------------");}

                    resultSecond = second.activationFunction(iris.getAtributes());

                    if (resultSecond == 1) {
                        answer = "Iris-virginica";
                    } else {
                        answer = "Iris-versicolor";
                    }
                    if(printMessages){
                    System.out.println(answer + ", correct answer: " + iris.getAnswer());}
                    if (answer.equals(iris.getAnswer())) {
                        correctSecond += 1;
                    }
                    secondCounter+=1;
                }
            }
            accuracy = correct / testingData.size();
            accuracySecond = correctSecond / secondCounter;
            if(printMessages){
                System.out.println("Accuracy: " + accuracy*100+"%");
                System.out.println("second perceptron accuracy: " + accuracySecond*100+"%");
            }
        } while (accuracy != 1.0);

    }
}
