package kg.geektech.todo.presenter.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import kg.geektech.todo.App;
import kg.geektech.todo.R;
import kg.geektech.todo.data.database.AppPreferences;
import kg.geektech.todo.data.abstractActivityAndFrag.BaseActivity;
import kg.geektech.todo.model.BoredAction;
import kg.geektech.todo.model.BoredApiClient;
import kg.geektech.todo.presenter.intro.IntroActivity;
import me.bendik.simplerangeview.SimpleRangeView;

public class MainActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    SimpleRangeView rangeBar;
    TextView textViewRangeBar, textViewCategory, textViewExplore;
    ProgressBar progressBar;
    String valueOfSpinner;


    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        skipIntroIfShown();
        spinner();
    }


    public void initViews() {
        textViewExplore = findViewById(R.id.textViewExplore);
        progressBar = findViewById(R.id.progress_horizontal);
        spinner = findViewById(R.id.types_spinner);
        rangeBar = findViewById(R.id.rangeBar1);
        textViewRangeBar = findViewById(R.id.textViewRangeBar);
        textViewCategory = findViewById(R.id.textViewCategory);
    }


    private void spinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueOfSpinner = spinner.getSelectedItem().toString();
                textViewCategory.setText(valueOfSpinner);

                //String[] size_values = getResources().getStringArray(R.array.types);
                //int size = Integer.valueOf(size_values[spinner_pos]);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                toast("No item is selected");

            }
        });
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


    public void skipIntroIfShown() {
        boolean isFirstLaunched = new AppPreferences(this).isFirstLaunched();
        if (!isFirstLaunched)
            startActivity(new Intent(this, IntroActivity.class));

    }

    public void next(View view) {
        App.boredApiClient.getAction(valueOfSpinner, null,
                null,
                null,
                null,
                null,
                null,
                null, new BoredApiClient.BoredActionCallback() {
                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        Log.e("yoyo", boredAction.toString());
                        textViewExplore.setText(boredAction.getActivity());

                    }

                    @Override
                    public void onFailure(Exception exception) {

                    }
                });


    }


}

