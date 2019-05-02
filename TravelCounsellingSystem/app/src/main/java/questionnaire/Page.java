package questionnaire;

import java.util.ArrayList;

public class Page {
    //page id
    private String pageId;
    //page situation
    private String status;
    //page title
    private String title;
    //questions
    private ArrayList<Question> questions;


    public ArrayList<Question> getquestions() {
        return questions;
    }
    public void setquestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getPageId() {
        return pageId;
    }
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}

