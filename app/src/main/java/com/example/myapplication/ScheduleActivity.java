package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Calendar;

import static android.os.Build.VERSION_CODES.O;

public class ScheduleActivity extends Activity {
    private int dayOfWeek;

    private LinearLayout schedule_day;

    private StudySchedule studySchedule = AuthenticationActivity.studySchedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        Calendar c = Calendar.getInstance();
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;

        schedule_day = findViewById(R.id.schedule_day);

        if(dayOfWeek == -1) {
            schedule_day.addView(createWeekend(schedule_day.getContext()));
            return;
        }

        paintButton(true);

        printLessons(schedule_day);
    }

    public void chooseMonday(View view){
        if (dayOfWeek == 0) return;

        paintButton(false);
        dayOfWeek = 0;
        paintButton(true);

        printLessons(schedule_day);
    }

    public void chooseTuesday(View view){
        if (dayOfWeek == 1) return;

        paintButton(false);
        dayOfWeek = 1;
        paintButton(true);

        printLessons(schedule_day);
    }

    public void chooseWednesday(View view){
        if (dayOfWeek == 2) return;

        paintButton(false);
        dayOfWeek = 2;
        paintButton(true);

        printLessons(schedule_day);
    }

    public void chooseThursday(View view){
        if (dayOfWeek == 3) return;

        paintButton(false);
        dayOfWeek = 3;
        paintButton(true);

        printLessons(schedule_day);
    }

    public void chooseFriday(View view){
        if (dayOfWeek == 4) return;

        paintButton(false);
        dayOfWeek = 4;
        paintButton(true);

        printLessons(schedule_day);
    }

    public void chooseSaturday(View view){
        if (dayOfWeek == 5) return;

        paintButton(false);
        dayOfWeek = 5;
        paintButton(true);

        printLessons(schedule_day);
    }

    private void paintButton(boolean isCurrent){
        switch (dayOfWeek){
            case 0:
                (findViewById(R.id.monday)).setBackgroundResource(isCurrent
                        ?R.drawable.mybutton_onclick
                        :R.drawable.mybutton);
                break;
            case 1:
                (findViewById(R.id.tuesday)).setBackgroundResource(isCurrent
                        ?R.drawable.mybutton_onclick
                        :R.drawable.mybutton);
                break;
            case 2:
                (findViewById(R.id.wednesday)).setBackgroundResource(isCurrent
                        ?R.drawable.mybutton_onclick
                        :R.drawable.mybutton);
                break;
            case 3:
                (findViewById(R.id.thursday)).setBackgroundResource(isCurrent
                        ?R.drawable.mybutton_onclick
                        :R.drawable.mybutton);
                break;
            case 4:
                (findViewById(R.id.friday)).setBackgroundResource(isCurrent
                        ?R.drawable.mybutton_onclick
                        :R.drawable.mybutton);
                break;
            case 5:
                (findViewById(R.id.saturday)).setBackgroundResource(isCurrent
                        ?R.drawable.mybutton_onclick
                        :R.drawable.mybutton);
                break;
        }
    }

    private void printLessons(LinearLayout schedule_day){
        boolean isWeekend = true;

        schedule_day.removeAllViews();

        for(int i = 0; i < studySchedule.getWeek()[dayOfWeek].length; i++){
            if (studySchedule.getWeek()[dayOfWeek][i] == null) continue;

            isWeekend = false;
            schedule_day.addView(createLessonLayout(studySchedule.getWeek()[dayOfWeek][i], schedule_day.getContext()));
        }

        if(isWeekend) schedule_day.addView(createWeekend(schedule_day.getContext()));
    }

    private LinearLayout createWeekend(Context parentContext){
        Context context = new ContextThemeWrapper(parentContext, R.style.weekend);
        LinearLayout wrap = new LinearLayout(context);
        wrap.setOrientation(LinearLayout.HORIZONTAL);
        wrap.setGravity(Gravity.CENTER);

        TextView textView = new TextView(parentContext);
        textView.setText("ВЫХОДНОЙ");
        wrap.addView(textView);

        return wrap;
    }

    @SuppressLint("ClickableViewAccessibility")
    private LinearLayout createLessonLayout(final LessonData data, Context parentContext){
        //обертка над вссем
        Context lessonLayoutContext = new ContextThemeWrapper(parentContext, R.style.lessonLayout);
        LinearLayout lessonLayout = new LinearLayout(lessonLayoutContext);

        //обертка информации на уроке
        Context informationAboutLessonContext = new ContextThemeWrapper(lessonLayoutContext, R.style.informationAboutLesson);
        LinearLayout informationAboutLesson = new LinearLayout(informationAboutLessonContext);
        lessonLayout.addView(informationAboutLesson);

        //номер урока
        Context numberOfLessonViewContext = new ContextThemeWrapper(informationAboutLessonContext, R.style.numberOfLesson);
        TextView numberOfLessonView = new TextView(numberOfLessonViewContext);
        numberOfLessonView.setText(data.getNumberOfLesson());
        informationAboutLesson.addView(numberOfLessonView);

        //обертка времени
        Context wrapTimeLessonContext = new ContextThemeWrapper(informationAboutLessonContext, R.style.wrapTimeLesson);
        LinearLayout wrapTimeLesson = new LinearLayout(wrapTimeLessonContext);
        informationAboutLesson.addView(wrapTimeLesson);

        //время начала урока
        Context startTimeLessonViewContext = new ContextThemeWrapper(wrapTimeLessonContext, R.style.timeLesson);
        TextView startTimeLessonView = new TextView(startTimeLessonViewContext);
        startTimeLessonView.setText(data.getStartTimeLesson());
        wrapTimeLesson.addView(startTimeLessonView);

        //время конца урока
        Context endTimeLessonViewContext = new ContextThemeWrapper(wrapTimeLessonContext, R.style.timeLesson);
        TextView endTimeLessonView = new TextView(endTimeLessonViewContext);
        endTimeLessonView.setText(data.getEndTimeLesson());
        wrapTimeLesson.addView(endTimeLessonView);

        //дополнительная информации
        Context additionalInformationContext = new ContextThemeWrapper(informationAboutLessonContext,
                R.style.additionalInformation);
        LinearLayout additionalInformation = new LinearLayout(additionalInformationContext);
        informationAboutLesson.addView(additionalInformation);

        //тип урока
        Context typeOfLessonViewContext = new ContextThemeWrapper(additionalInformationContext,
                R.style.typeOfLesson);
        TextView typeOfLessonView = new TextView(typeOfLessonViewContext);
        typeOfLessonView.setText(data.getTypeOfLesson());
        additionalInformation.addView(typeOfLessonView);

        //номер кабинета
        Context cabinetViewContext = new ContextThemeWrapper(additionalInformationContext,
                R.style.cabinet);
        TextView cabinetView = new TextView(cabinetViewContext);
        cabinetView.setText(data.getCabinet());
        additionalInformation.addView(cabinetView);

        //обертка названия урока
        Context wrapTitleOfLessonViewContext = new ContextThemeWrapper(informationAboutLessonContext, R.style.wrapTitleOfLesson);
        LinearLayout wrapTitleOfLessonView = new LinearLayout(wrapTitleOfLessonViewContext);
        informationAboutLesson.addView(wrapTitleOfLessonView);

        //название урока
        Context titleOfLessonViewContext = new ContextThemeWrapper(wrapTitleOfLessonViewContext, R.style.titleOfLesson);
        TextView titleOfLessonView = new TextView(titleOfLessonViewContext);
        titleOfLessonView.setText(data.getTitleOfLesson());
        wrapTitleOfLessonView.addView(titleOfLessonView);

        //обертка для поля домашняя работа
        final Context wrapHomeworkContext = new ContextThemeWrapper(lessonLayoutContext, R.style.wrapHomeworkOrNote);
        final LinearLayout wrapHomework = new LinearLayout(wrapHomeworkContext);
        wrapHomework.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    wrapHomework.removeAllViews();

                    Context editTextHomeWorkViewContext = new ContextThemeWrapper(wrapHomeworkContext, R.style.textHomeworkOrNote);
                    final EditText editTextHomeWorkView = new EditText(editTextHomeWorkViewContext);
                    editTextHomeWorkView.setText(data.getTextHomework());
                    wrapHomework.addView(editTextHomeWorkView);

                    editTextHomeWorkView.requestFocus();
                    InputMethodManager imm =
                            (InputMethodManager) editTextHomeWorkView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTextHomeWorkView, InputMethodManager.SHOW_IMPLICIT);

                    //кнопка для изменения домашней работы
                    Context buttonHomeworkViewContext = new ContextThemeWrapper(wrapHomeworkContext, R.style.buttonHomeworkOrNote);
                    Button buttonHomeworkView = new Button(buttonHomeworkViewContext);
                    buttonHomeworkView.setBackgroundColor(Color.rgb(89, 150, 255));

                    buttonHomeworkView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            data.setTextHomework(editTextHomeWorkView.getText().toString());

                            wrapHomework.removeAllViews();

                            //текст заметок
                            Context textHomeworkViewContext = new ContextThemeWrapper(wrapHomeworkContext, R.style.textHomeworkOrNote);
                            TextView textHomeworkView = new TextView(textHomeworkViewContext);
                            String s = "Д/З: " + data.getTextHomework();
                            textHomeworkView.setText(s);
                            wrapHomework.addView(textHomeworkView);
                        }
                    });

                    buttonHomeworkView.setText("добавить д/з");
                    wrapHomework.addView(buttonHomeworkView);
                }

                return false;
            }
        });
        lessonLayout.addView(wrapHomework);

        //текст домашней работы
        Context textHomeworkViewContext = new ContextThemeWrapper(wrapHomeworkContext, R.style.textHomeworkOrNote);
        TextView textHomeworkView = new TextView(textHomeworkViewContext);
        String sHomework = "Д/З: " + data.getTextHomework();
        textHomeworkView.setText(sHomework);
        wrapHomework.addView(textHomeworkView);

        //обертка для поля заметок
        final Context wrapNoteContext = new ContextThemeWrapper(lessonLayoutContext, R.style.wrapHomeworkOrNote);
        final LinearLayout wrapNote = new LinearLayout(wrapNoteContext);
        wrapNote.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    wrapNote.removeAllViews();

                    Context textNoteViewContext = new ContextThemeWrapper(wrapNoteContext, R.style.textHomeworkOrNote);
                    final EditText editTextNoteView = new EditText(textNoteViewContext);
                    editTextNoteView.setText(data.getTextNote());
                    wrapNote.addView(editTextNoteView);

                    editTextNoteView.requestFocus();
                    InputMethodManager imm =
                            (InputMethodManager) editTextNoteView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTextNoteView, InputMethodManager.SHOW_IMPLICIT);


                    //кнопка для изменения заметок
                    Context buttonNoteViewContext = new ContextThemeWrapper(wrapNoteContext, R.style.buttonHomeworkOrNote);
                    Button buttonNoteView = new Button(buttonNoteViewContext);
                    buttonNoteView.setBackgroundColor(Color.rgb(89, 150, 255));

                    buttonNoteView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            data.setTextNote(editTextNoteView.getText().toString());

                            wrapNote.removeAllViews();

                            //текст заметок
                            Context textNoteViewContext = new ContextThemeWrapper(wrapNoteContext, R.style.textHomeworkOrNote);
                            TextView textNoteView = new TextView(textNoteViewContext);
                            String s = "Заметка: " + data.getTextNote();
                            textNoteView.setText(s);
                            wrapNote.addView(textNoteView);
                        }
                    });

                    buttonNoteView.setText("добавить заметку");
                    wrapNote.addView(buttonNoteView);
                }

                return false;
            }
        });
        lessonLayout.addView(wrapNote);

        //текст заметок
        Context textNoteViewContext = new ContextThemeWrapper(wrapNoteContext, R.style.textHomeworkOrNote);
        TextView textNoteView = new TextView(textNoteViewContext);
        String sNote = "Заметка: " + data.getTextNote();
        textNoteView.setText(sNote);
        wrapNote.addView(textNoteView);

        LinearLayout.LayoutParams lessonLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        lessonLayoutParams.setMargins(0, 0, 0, 30);

        lessonLayout.setLayoutParams(lessonLayoutParams);

        return lessonLayout;
    }
}
