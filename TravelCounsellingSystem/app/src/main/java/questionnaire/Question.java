package questionnaire;


import java.util.ArrayList;

public class Question {
    //question id
    private String questionId;
    private String type;
    private String content;
    private ArrayList<Answer> answers;
    //if answered
    private int que_state;


    public int getQue_state() {
        return que_state;
    }
    public void setQue_state(int que_state) {
        this.que_state = que_state;
    }

    public String getquestionId() {
        return questionId;
    }
    public void setquestionId(String questionId) {
        this.questionId = questionId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public ArrayList<questionnaire.Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(ArrayList<questionnaire.Answer> answers) {
        this.answers = answers;
    }

}
