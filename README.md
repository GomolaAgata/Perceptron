# Perceptron
Project realised during Artificial Intelligence Tools course in Polish-Japanese Academy of Information Technology.

About project

Perceptron classifies irises into one of three species: Iris setosa, Iris virginica, or Iris versicolor. Program reads iris training data from a text file.
One class name is renamed to 1, while the others are renamed to 0. The program implements a perceptron with an appropriately sized weight vector, 
an activation threshold, and methods to modify the weight vector according to the delta rule. 
Program uses training data to train the perceptron, then checks its accuracy on test data, returning the percentage of correctly classified vectors.

Program provides a keyboard interface allowing user to input test vectors and receive classification results as output. 
Additionally, there is a file-based interface where the program reads data from a file and returns the percentage of correctly classified vectors.

This program includes two perceptron models: the first checks whether a vector belongs to the selected class(in this case- Iris setosa), and if not, 
the vector is passed to the second perceptron, which checks to which of the remaining classes the vector belongs.
