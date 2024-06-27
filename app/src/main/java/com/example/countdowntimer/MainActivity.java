package com.example.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button startButton, stopButton, resetButton;
    private Button add1sButton, add10sButton, add1mButton, add10mButton;
    private Button sub1sButton, sub10sButton, sub1mButton, sub10mButton;
    private CountDownTimer countDownTimer;
    private boolean isRunning = false;
    private long timeLeftInMillis = 0; // 1 menit
    private long initialTimeInMillis = 0; // 1 menit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);
        add1sButton = findViewById(R.id.add1sButton);
        add10sButton = findViewById(R.id.add10sButton);
        add1mButton = findViewById(R.id.add1mButton);
        add10mButton = findViewById(R.id.add10mButton);
        sub1sButton = findViewById(R.id.sub1sButton);
        sub10sButton = findViewById(R.id.sub10sButton);
        sub1mButton = findViewById(R.id.sub1mButton);
        sub10mButton = findViewById(R.id.sub10mButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startTimer();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    stopTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        add1sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    addTime(1000); // Tambah 1 detik
                }
            }
        });

        add10sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    addTime(10000); // Tambah 10 detik
                }
            }
        });

        add1mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    addTime(60000); // Tambah 1 menit
                }
            }
        });

        add10mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    addTime(600000); // Tambah 10 menit
                }
            }
        });

        sub1sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    subtractTime(1000); // Kurangi 1 detik
                }
            }
        });

        sub10sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    subtractTime(10000); // Kurangi 10 detik
                }
            }
        });

        sub1mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    subtractTime(60000); // Kurangi 1 menit
                }
            }
        });

        sub10mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    subtractTime(600000); // Kurangi 10 menit
                }
            }
        });

        updateCountDownText();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isRunning = false;
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }
        }.start();

        isRunning = true;
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
    }

    private void stopTimer() {
        countDownTimer.cancel();
        isRunning = false;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    private void resetTimer() {
        timeLeftInMillis = initialTimeInMillis;
        updateCountDownText();
    }

    private void addTime(long millis) {
        timeLeftInMillis += millis;
        updateCountDownText();
    }

    private void subtractTime(long millis) {
        timeLeftInMillis -= millis;
        if (timeLeftInMillis < 0) {
            timeLeftInMillis = 0;
        }
        updateCountDownText();
    }

    private void updateCountDownText() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((timeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted;
        if (hours > 0) {
            timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeFormatted = String.format("%02d:%02d", minutes, seconds);
        }

        textView.setText(timeFormatted);
    }
}
