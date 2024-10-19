package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressTextView;
    private TextView messageTextView; // Добавляем переменную для messageTextView
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressTextView);
        messageTextView = findViewById(R.id.messageTextView); // Инициализируем messageTextView

        // Устанавливаем начальный текст
        messageTextView.setText("Удаляем папку /data и obb");

        // Запускаем обновление прогресса
        updateProgress();
    }

    private void updateProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus++; // Увеличиваем прогресс на 1
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            progressTextView.setText("Прогресс: " + progressStatus + "%");
                        }
                    });
                    try {
                        Thread.sleep(200); // Задержка для имитации работы
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Вывод фразы "С ДР!" после завершения прогресса
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        messageTextView.setText("Шутка. С ДР тебя!");
                    }
                });
            }
        }).start();
    }
}