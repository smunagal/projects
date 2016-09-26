CSE 574 - Machine Learning

Project 1: Handwritten Digits Classification Using Neural Networks:

The main aim of this project is build and train a Neutral Network, i.e., learn the weight parameters and evaluate its performance in classifying handwritten digits from 0 to 9.The steps followed are
1. Preprocess the data.
2. Train the Neural Network
3. Predict and calculate the accuracy.
The Neural Network we have built has one hidden layer and has regularization to control the biasvariance tradeoff, i.e., we have add a regularization term (λ) into our error function to control the magnitude of parameters in Neural Network.


Project 2: Classification and Regression:

The goal of the project is to compare Linear Discriminative Analysis(LDA) and Quadratic Discriminative Analysis(QDA) on the provided test data set (sample test) and to calculate Root mean square error(RMSE) for cases with intercept and without intercept for the given test data.

Project 3: Logistic Regression,Support Vector Machines and Multi-Class Logistic Regression:

Linear Regression: Images of Hand written digits which has to be classified into 10 classes. Since we cannot use a single Binary Logistic Regression Classifier for the given data, we have implemented the One-vs-All Strategy for the Logistic Regression. The classifier build learns 10 Binary classifiers (one for each of the output classes) so that each class can be distinguished from all other classes.
Support Vector Machine:

SVM: A Support Vector Machine (SVM) is a discriminative classifier designed to learn a separating hyperplane i.e., given labeled training data, the algorithm outputs an optimal hyperplane which categorizes new examples and also maximizes the margin of the training data. Maximizing the margin is nothing but the hyperplane is learnt in such a way that it is equally far from all the sets of data points belonging to different classes.

Multi-Class Logistic Regression: Instead of using the One-vs-All model and learning 10 Binary Logistic Classifies, we have implemented the Multiclass Logistic Regression an extension of the Binary Logistic Classifies which learns the weight matrix “w” with 10 different weight vectors for the 10 different classes in just one classifier and we then use it for predicting the training data.
