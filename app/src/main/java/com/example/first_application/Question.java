package com.example.first_application;

public class Question {

    private String ID;
    private String image;
    private String correctrep;
    private String reponse2;
    private String reponse3;
    private String question;
    private int score ;

    public Question(String ID, String image, String correctrep, String reponse2, String reponse3, String question, int score) {
        this.ID = ID;
        this.image = image;
        this.correctrep = correctrep;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.question = question;
        this.score = score;
    }

    public Question() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCorrectrep() {
        return correctrep;
    }

    public void setCorrectrep(String correctrep) {
        this.correctrep = correctrep;
    }

    public String getReponse2() {
        return reponse2;
    }

    public void setReponse2(String reponse2) {
        this.reponse2 = reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public void setReponse3(String reponse3) {
        this.reponse3 = reponse3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
