package ro.ase.codinquiz.quizapplication.Main.Teacher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerCategory_Post;
import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerQuestion_Post;
import ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers.WorkerRetrieveCategories_All;
import ro.ase.codinquiz.quizapplication.Main.Entities.Answer;
import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity2;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity3;
import ro.ase.codinquiz.quizapplication.Main.OtherActivities.ToDoActivity4;
import ro.ase.codinquiz.quizapplication.Main.Teacher.Adapters.SpinnerHintAdapter;
import ro.ase.codinquiz.quizapplication.R;

public class TeacherCreateQuestion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner QuestionCategorySpinner = null;
    List<String> categories=null;
    private static int RESULT_LOAD_IMAGE = 1;
    private List<Category> categoryList=new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        QuestionCategorySpinner = (Spinner) findViewById(R.id.spQuestionCategory2);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        //Display hint text "Question categories" for spinner
        WorkerRetrieveCategories_All workerRetrieveCategoriesAll=new WorkerRetrieveCategories_All(getApplicationContext());
        try {
            categoryList=workerRetrieveCategoriesAll.execute(categoryList).get();
        }catch (ExecutionException e){

        }catch (InterruptedException e){

        }
         categories = new ArrayList<String>();
        for (Category c :
                categoryList) {
            categories.add(c.getName());
        }


        adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        QuestionCategorySpinner.setAdapter(adapter);
      //  SpinnerHintAdapter adapter = new SpinnerHintAdapter(this, objects, android.R.layout.simple_spinner_item);


       // QuestionCategorySpinner.setAdapter(adapter);
     //  QuestionCategorySpinner.setSelection(adapter.getCount()-1); // show hint
    }

    public void addImage(View view) {
        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery, RESULT_LOAD_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            ImageView imageView = findViewById(R.id.ivAddedImage);
            imageView.setImageURI(imageUri);
        }
    }

    public void saveQuestion(View view) {
        List<Answer> answerList=new ArrayList<>();
        TextView answer1TW=findViewById(R.id.textAnswer1);
        TextView answer2TW=findViewById(R.id.textAnswer2);
        TextView answer3TW=findViewById(R.id.textAnswer3);
        TextView answer4TW=findViewById(R.id.textAnswer4);
        CheckBox box1=findViewById(R.id.checkBoxAnswer1);
        CheckBox box2=findViewById(R.id.checkBoxAnswer2);
        CheckBox box3=findViewById(R.id.checkBoxAnswer3);
        CheckBox box4=findViewById(R.id.checkBoxAnswer4);
        boolean answer1isCorrect=false,answer2isCorrect=false,answer3isCorrect=false,answer4isCorrect=false;
        Answer answer1=new Answer(answer1TW.getText().toString());
        if (box1.isChecked()) {
            answer1.setCorrect(true);
        }

        Answer answer2=new Answer(answer2TW.getText().toString());
        if(box2.isChecked()) {
            answer2.setCorrect(true);
        }
        Answer answer3=new Answer(answer3TW.getText().toString());

        if(box3.isChecked()) {
            answer3.setCorrect(true);
        }

        Answer answer4=new Answer(answer4TW.getText().toString());
        if(box4.isChecked()) {
            answer4.setCorrect(true);
        }
        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);
        answerList.add(answer4);

        ImageView imageView = findViewById(R.id.ivAddedImage);
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();
        String img_str = Base64.encodeToString(image, 0);

        TextView questionTW=findViewById(R.id.textQuestion);
        TextView cat=findViewById(R.id.etCategory);
        if("".equals(cat.getText().toString())) {//the question is not posting the image yet,under development
            Question question = new Question(QuestionCategorySpinner.getSelectedItem().toString(),questionTW.getText().toString(), answerList, null);

            WorkerQuestion_Post workerQuestionPost=new WorkerQuestion_Post(getApplicationContext());
            workerQuestionPost.execute(question);
        }
        else
        {
            categories.add(cat.getText().toString());
            Category newCategory=new Category(999,cat.getText().toString());
            adapter.notifyDataSetChanged();
            WorkerCategory_Post workerCategory_post=new WorkerCategory_Post(getApplicationContext());
            workerCategory_post.execute(newCategory);
            Question question = new Question(cat.getText().toString(), questionTW.getText().toString(),answerList, null);
            WorkerQuestion_Post workerQuestionPost=new WorkerQuestion_Post(getApplicationContext());
            workerQuestionPost.execute(question);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teacher_create_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_create_new_test) {
            Intent intent =new Intent(this,TeacherCreateTest.class);
            startActivity(intent);
        } else if (id == R.id.nav_existing_tests) {
            Intent intent =new Intent(this,TeacherExistingTests.class);
            startActivity(intent);
        } else if (id == R.id.nav_create_new_question) {
            Intent intent =new Intent(this,TeacherCreateQuestion.class);
            startActivity(intent);
        } else if (id == R.id.nav_existing_questions) {
            Intent intent =new Intent(this,TeacherExistingQuestions.class);
            startActivity(intent);
        } else if (id == R.id.nav_assignment_history) {
            Intent intent =new Intent(this,TeacherSeeHistory.class);
            startActivity(intent);
        } else if (id == R.id.nav_leave_feedback) {
            Intent intent =new Intent(this,TeacherFeedbackActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_share) {
            Intent intent = new Intent(this, TeacherShareTest.class);
            startActivity(intent);
        }else if (id == R.id.nav_see_statistics) {
            Intent intent = new Intent(this, TeacherSeeStatistics.class);
            startActivity(intent);
        }else if (id == R.id.nav_rate_app) {
            Intent intent = new Intent(this, ToDoActivity2.class);
            startActivity(intent);
        }else if (id == R.id.nav_contact_us) {
            Intent intent = new Intent(this, ToDoActivity4.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
