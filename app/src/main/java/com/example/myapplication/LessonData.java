package com.example.myapplication;

class LessonData {
    private String numberOfLesson;
    private String textNote;
    private String startTimeLesson;
    private String endTimeLesson;
    private String titleOfLesson;
    private String typeOfLesson;
    private String cabinet;

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public void setTextHomework(String textHomework) {
        this.textHomework = textHomework;
    }

    public String getNumberOfLesson() {
        return numberOfLesson;
    }

    public String getTextNote() {
        return textNote;
    }

    public String getStartTimeLesson() {
        return startTimeLesson;
    }

    public String getEndTimeLesson() {
        return endTimeLesson;
    }

    public String getTitleOfLesson() {
        return titleOfLesson;
    }

    public String getTypeOfLesson() {
        return typeOfLesson;
    }

    private String textHomework;

    public LessonData(String numberOfLesson, String textNote,
                      String startTimeLesson, String endTimeLesson,
                      String titleOfLesson, String typeOfLesson,
                      String cabinet, String textHomework) {
        this.numberOfLesson = numberOfLesson;
        this.textNote = textNote;
        this.startTimeLesson = startTimeLesson;
        this.endTimeLesson = endTimeLesson;
        this.titleOfLesson = titleOfLesson;
        this.typeOfLesson = typeOfLesson;
        this.cabinet = cabinet;
        this.textHomework = textHomework;
    }

    public String getCabinet() {
        return cabinet;
    }

    public String getTextHomework() {
        return textHomework;
    }
}
