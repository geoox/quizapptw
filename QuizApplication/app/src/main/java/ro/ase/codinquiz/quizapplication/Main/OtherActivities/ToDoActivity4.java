package ro.ase.codinquiz.quizapplication.Main.OtherActivities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ro.ase.codinquiz.quizapplication.R;

public class ToDoActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do4);
    }

    public void goToGmail(View v){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent mailClient = new Intent(Intent.ACTION_VIEW);
                mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
                startActivity(mailClient);
            }});
        thread.start();

    }


}