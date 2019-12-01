package com.example.myapplication;

class StudySchedule {
    private String login = "somebody";
    private String password = "ASDFGH";

    private LessonData[][] week = {
            {null, null, null, null, new LessonData("5", "Лала",
                    "16:30", "18:00", "Мат. анализ", "ПР",
                    "А-12", "Типовик"), null},
            {new LessonData("1", "Лала",
                    "09:00", "10:30", "Информатика", "ЛК",
                    "А-12", "Типовик"), null, null, null, null, null},
            {null, new LessonData("2", "Лала",
                    "10:40", "12:10", "Физика", "ЛАБ",
                    "А-12", "Типовик"), null, null, null, null},
            {null, new LessonData("3", "Лала",
                    "10:40", "12:10", "Java", "ПР",
                    "А-12", "Типовик"),
                    new LessonData("4", "Лала",
                    "13:10", "14:40", "Java", "ПР",
                    "А-12", "Типовик"), null, null, null},
            {null, null, null, new LessonData("4", "Лала",
                    "14:50", "16:20", "Лин. алгебра", "ПР",
                    "А-12", "Типовик"), null, null},
            {null, null, null, null, null, null},
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LessonData[][] getWeek() {
        return week;
    }
}
