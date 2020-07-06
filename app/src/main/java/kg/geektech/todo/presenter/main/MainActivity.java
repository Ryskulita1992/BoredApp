package kg.geektech.todo.presenter.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import kg.geektech.todo.App;
import kg.geektech.todo.R;
import kg.geektech.todo.data.database.AppPreferences;
import kg.geektech.todo.data.abstractActivityAndFrag.BaseActivity;
import kg.geektech.todo.model.BoredAction;
import kg.geektech.todo.model.BoredApiClient;
import kg.geektech.todo.presenter.intro.IntroActivity;
import me.bendik.simplerangeview.SimpleRangeView;

public class MainActivity extends BaseActivity {
    Spinner spinner;
    SimpleRangeView rangeBar;
    TextView textViewRangeBar, textViewCategory, textViewExplore, textViewPrice, textViewPaymentFree;
    ProgressBar progressBar;
    String valueOfSpinner;
    Double minPrice, maxPrice, minAccessibility, maxAccessibility;
    ImageView moneyBag, icOnePerson, icTwoPerson, icThreePerson, icFourPerson;


    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        skipIntroIfShown();
        onSpinnerClick();
        onPriceRangeBar();
        onAccessibilityRangeBar();
       // onProgressBar();


    }

//    private void onProgressBar() {
//progressBar.setProgress(()maxAccessibility);
//
//    }


    public void initViews() {
        textViewExplore = findViewById(R.id.textViewExplore);
        progressBar = findViewById(R.id.progress_horizontal);
        spinner = findViewById(R.id.types_spinner);
        textViewCategory = findViewById(R.id.textViewCategory);
        rangeBar = findViewById(R.id.rangeBar1);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewPaymentFree = findViewById(R.id.textViewPayment);
        moneyBag = findViewById(R.id.money_bag);
        icOnePerson = findViewById(R.id.ic_person);
        icTwoPerson = findViewById(R.id.ic_two_person);
        icThreePerson = findViewById(R.id.ic_three_persons);
        icFourPerson = findViewById(R.id.ic_four_persons);


    }

    public void onPriceRangeBar() {
        rangeBar.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
            @Override
            public void onRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i, int i1) {

                minPrice = Double.valueOf(i);

                Log.e("yoyo", String.valueOf(minPrice));

                maxPrice = Double.valueOf(i1);
                Log.e("yoyo", String.valueOf(maxPrice));
            }
        });

        rangeBar.setLabelFontSize(2);


    }

    public void onAccessibilityRangeBar() {
        rangeBar.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
            @Override
            public void onRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i, int i1) {

                minAccessibility = Double.valueOf(i);
                Log.e("yoyo", String.valueOf(minPrice));

                maxAccessibility = Double.valueOf(i1);
                Log.e("yoyo", String.valueOf(maxPrice));
            }
        });

    }


    private void onSpinnerClick() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueOfSpinner = spinner.getSelectedItem().toString();
                textViewCategory.setText(valueOfSpinner);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                toast("No item is selected");

            }
        });
    }

    public void skipIntroIfShown() {
        boolean isFirstLaunched = new AppPreferences(this).isFirstLaunched();
        if (!isFirstLaunched)
            startActivity(new Intent(this, IntroActivity.class));

    }
    public void participantsQuantity(BoredAction b){
        switch (b.getParticipants()){
            case 1:
                if (b.getParticipants()==1){
                    icOnePerson.setVisibility(View.VISIBLE);
                    icTwoPerson.setVisibility(View.INVISIBLE);
                    icThreePerson.setVisibility(View.INVISIBLE);
                    icFourPerson.setVisibility(View.INVISIBLE);
                }
            case 2:
                if (b.getParticipants()==2){
                    icTwoPerson.setVisibility(View.VISIBLE);
                    icOnePerson.setVisibility(View.INVISIBLE);
                    icThreePerson.setVisibility(View.INVISIBLE);
                    icFourPerson.setVisibility(View.INVISIBLE);
                }
            case 3:
                if (b.getParticipants()==3){
                    icThreePerson.setVisibility(View.VISIBLE);
                    icTwoPerson.setVisibility(View.INVISIBLE);
                    icOnePerson.setVisibility(View.INVISIBLE);
                    icFourPerson.setVisibility(View.INVISIBLE);
                }
            case 4:
                if (b.getParticipants()==4){
                    icFourPerson.setVisibility(View.VISIBLE);

                    icTwoPerson.setVisibility(View.INVISIBLE);
                    icThreePerson.setVisibility(View.INVISIBLE);
                    icOnePerson.setVisibility(View.INVISIBLE);
                }


        }
    }
//    public void onProgressBar(){
//        progressBar.setProgress(1);
//    }

    public void next(View view) {
        App.boredApiClient.getAction(valueOfSpinner, null, null, null, null, null, null, null, new BoredApiClient.BoredActionCallback() {
            @Override
            public void onSuccess(BoredAction boredAction) {
                Log.e("yoyo", boredAction.toString());
                textViewExplore.setText(boredAction.getActivity());
                textViewPrice.setText(boredAction.getPrice().toString() + " $ ");
                participantsQuantity(boredAction);
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
        Log.e("yoyo", valueOfSpinner);

    }



}



