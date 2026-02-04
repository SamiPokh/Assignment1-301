package com.example.samippya_emotilog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Data storage
    private ArrayList<EmoticonLog> logs;

    // UI elements
    private TextView displayTextView;
    private boolean showingSummary = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize data
        logs = new ArrayList<>();

        // Get display text view
        displayTextView = findViewById(R.id.displayTextView);

        // Setup all button click listeners
        setupEmoticonButtons();
        setupActionButtons();
    }

    private void setupEmoticonButtons() {
        // Happy button
        Button btnHappy = findViewById(R.id.btnHappy);
        btnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog("😊", "Happy");
            }
        });

        // Sad button
        Button btnSad = findViewById(R.id.btnSad);
        btnSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog("😢", "Sad");
            }
        });

        // Stressed button
        Button btnStressed = findViewById(R.id.btnStressed);
        btnStressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog("😰", "Stressed");
            }
        });

        // Excited button
        Button btnExcited = findViewById(R.id.btnExcited);
        btnExcited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog("🎉", "Excited");
            }
        });

        // Angry button
        Button btnAngry = findViewById(R.id.btnAngry);
        btnAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog("😠", "Angry");
            }
        });

        // Anxious button
        Button btnAnxious = findViewById(R.id.btnAnxious);
        btnAnxious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog("😨", "Anxious");
            }
        });

        // Tired button
        Button btnTired = findViewById(R.id.btnTired);
        btnTired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog("😴", "Tired");
            }
        });

        // Disgust button
        Button btnDisgust = findViewById(R.id.btnDisgust);
        btnDisgust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog("🤢", "Disgust");
            }
        });

        // Surprise button
        Button btnSurprise = findViewById(R.id.btnSurprise);
        btnSurprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog("😲", "Surprise");
            }
        });
    }

    private void setupActionButtons() {
        // View Summary button
        Button btnViewSummary = findViewById(R.id.btnViewSummary);
        btnViewSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleView();
            }
        });

        // Clear button
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearLogs();
            }
        });
    }

    private void addLog(String emoticon, String emotionName) {
        logs.add(new EmoticonLog(emoticon, emotionName));

        if (showingSummary) {
            displaySummary();
        } else {
            displayLogs();
        }
    }

    private void toggleView() {
        showingSummary = !showingSummary;

        Button btnViewSummary = findViewById(R.id.btnViewSummary);

        if (showingSummary) {
            displaySummary();
            btnViewSummary.setText("📝 View Logs");
        } else {
            displayLogs();
            btnViewSummary.setText("📊 View Summary");
        }
    }

    private void displayLogs() {
        if (logs.isEmpty()) {
            displayTextView.setText("No logs yet. Tap an emotion above to start!");
            return;
        }

        StringBuilder text = new StringBuilder();
        text.append("📝 Recent Logs:\n");
        text.append("═══════════════════\n\n");

        // Display newest first
        for (int i = logs.size() - 1; i >= 0; i--) {
            EmoticonLog log = logs.get(i);
            text.append(log.getLogDisplay()).append("\n");
        }

        displayTextView.setText(text.toString());
    }

    private void displaySummary() {
        if (logs.isEmpty()) {
            displayTextView.setText("No logs yet!\n\nStart tracking your emotions by tapping the emoticon buttons above.");
            return;
        }

        // Count each emotion
        Map<String, Integer> counts = new HashMap<>();
        Map<String, String> names = new HashMap<>();

        for (EmoticonLog log : logs) {
            String emoticon = log.getEmoticon();
            counts.put(emoticon, counts.getOrDefault(emoticon, 0) + 1);
            names.put(emoticon, log.getEmotionName());
        }

        // Build summary text
        StringBuilder text = new StringBuilder();
        text.append("📊 EMOTION SUMMARY\n");
        text.append("Date: ").append(getCurrentDate()).append("\n");
        text.append("═══════════════════════════\n\n");
        text.append("Total Logs: ").append(logs.size()).append("\n\n");

        // Sort and display
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String emoticon = entry.getKey();
            int count = entry.getValue();
            int percentage = (count * 100) / logs.size();

            text.append(emoticon).append(" ")
                    .append(names.get(emoticon)).append(": ")
                    .append(count).append(" (")
                    .append(percentage).append("%)\n");
        }

        displayTextView.setText(text.toString());
    }

    private void clearLogs() {
        logs.clear();

        if (showingSummary) {
            displaySummary();
        } else {
            displayLogs();
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }
}