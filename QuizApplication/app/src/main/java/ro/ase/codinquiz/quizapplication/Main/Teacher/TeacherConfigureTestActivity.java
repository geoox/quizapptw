package ro.ase.codinquiz.quizapplication.Main.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ro.ase.codinquiz.quizapplication.Main.Student.JoinTestActivity;
import ro.ase.codinquiz.quizapplication.R;

public class TeacherConfigureTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_configure_test);
    }

    public void generateCode(View view){
        Intent intent = new Intent(this, GenerateCode.class);
        startActivity(intent);
    }
}
