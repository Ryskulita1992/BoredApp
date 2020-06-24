package kg.geektech.todo.presenter.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import kg.geektech.todo.R;
import kg.geektech.todo.data.database.AppPreferences;
import kg.geektech.todo.data.abstractActivityAndFrag.BaseActivity;
import kg.geektech.todo.presenter.intro.IntroActivity;
import me.bendik.simplerangeview.SimpleRangeView;

public class MainActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    SimpleRangeView rangeBar;
    TextView textViewRangeBar;
    ProgressBar progressBar;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        skipIntroIfShown();
    }


    public void initViews(){
        progressBar = findViewById(R.id.progress_horizontal);
        spinner = findViewById(R.id.types_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rangeBar = findViewById(R.id.rangeBar1);
        textViewRangeBar = findViewById(R.id.textViewRangeBar);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        toast(text);

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        toast("No item was selected");
    }
    public void skipIntroIfShown(){
        Boolean isFirstLaunched=new AppPreferences(this).isFirstLaunched();
        if (isFirstLaunched==false)
            startActivity(new Intent(this, IntroActivity.class));

    }
}

