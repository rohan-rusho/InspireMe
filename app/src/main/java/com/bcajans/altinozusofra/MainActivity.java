package com.bcajans.altinozusofra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteText;
    private Button refreshBtn, copyBtn, shareBtn;

    // 30 motivational quotes
    private final String[] quotes = {
            "Believe you can and you're halfway there.",
            "The only limit is your mind.",
            "Dream it. Wish it. Do it.",
            "Push yourself, because no one else is going to do it for you.",
            "Great things never come from comfort zones.",
            "Stay positive, work hard, make it happen.",
            "Don’t stop until you’re proud.",
            "Success is not final, failure is not fatal: It is the courage to continue that counts.",
            "Happiness depends upon ourselves.",
            "Turn your wounds into wisdom.",
            "Don’t let yesterday take up too much of today.",
            "Do something today that your future self will thank you for.",
            "Act as if what you do makes a difference. It does.",
            "Keep going. Everything you need will come to you at the perfect time.",
            "A little progress each day adds up to big results.",
            "Your limitation—it’s only your imagination.",
            "Great things take time. Don’t give up.",
            "Don’t wait for opportunity. Create it.",
            "Difficult roads often lead to beautiful destinations.",
            "Hardships often prepare ordinary people for an extraordinary destiny.",
            "If you get tired, learn to rest, not to quit.",
            "Success doesn’t come to you, you go to it.",
            "You don’t have to be perfect to be amazing.",
            "Doubt kills more dreams than failure ever will.",
            "Focus on being productive instead of busy.",
            "Work hard in silence. Let success make the noise.",
            "Big journeys begin with small steps.",
            "Don’t call it a dream, call it a plan.",
            "The best way to get started is to quit talking and begin doing.",
            "Fall seven times and stand up eight."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);
        refreshBtn = findViewById(R.id.refreshBtn);
        copyBtn = findViewById(R.id.copyBtn);
        shareBtn = findViewById(R.id.shareBtn);

        // Refresh button → show random quote
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQuote = getRandomQuote();
                quoteText.setText(newQuote);
            }
        });

        // Copy button → copy current quote to clipboard
        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = quoteText.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Quote", textToCopy);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Quote copied to clipboard!", Toast.LENGTH_SHORT).show();
            }
        });

        // Share button → share current quote
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = quoteText.getText().toString() +
                        "\n\n— InspireMe | Explore, Discover, Learn";

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }

    // Pick a random quote
    private String getRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        return quotes[index];
    }
}
