package minh.assignment1.quizzgame;

public class Questions {

    int image;
    String questionContent;
    String answer0;
    String answer1;
    String answer2;
    String answer3;
    int answerCorrect;
    int answerPlayerChoose;

    public Questions(int imageNumber,
                    String questionNumber,
                    String answerZero,
                    String answerOne,
                    String answerTwo,
                    String answerThree,
                    int correctAnswerIndex) {
        image = imageNumber;
        questionContent = questionNumber;
        answer0 = answerZero;
        answer1 = answerOne;
        answer2 = answerTwo;
        answer3 = answerThree;
        answerCorrect = correctAnswerIndex;
        answerPlayerChoose = -1;
    }

    public boolean checkCorrect() {
        return answerPlayerChoose == answerCorrect;
    }


}
