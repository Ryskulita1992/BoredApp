package kg.geektech.todo.presenter.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.google.android.material.slider.RangeSlider;

import java.text.MessageFormat;
import java.util.List;
import kg.geektech.todo.App;
import kg.geektech.todo.R;
import kg.geektech.todo.data.abstractActivityAndFrag.BaseActivity;
import kg.geektech.todo.data.database.AppPreferences;
import kg.geektech.todo.model.BoredAction;
import kg.geektech.todo.model.BoredApiClient;
import kg.geektech.todo.presenter.intro.IntroActivity;

public class MainActivity extends BaseActivity  {
    Spinner spinner;
    RangeSlider priceRangeBar, accessibilityRangeBar;
    TextView textViewCategory, textViewExplore, textViewPrice, textViewPaymentFree;
    ProgressBar progressBar;
    String valueOfSpinner;
    Float minPrice;
    Float maxPrice;
    Float minAccessibility;
    Float maxAccessibility;
    ImageView moneyBag, icOnePerson, icTwoPerson, icThreePerson, icFourPerson;
    private List<Float> price, accessibility;


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
        onRangeBar();

    }



    public void initViews() {
        textViewExplore = findViewById(R.id.textViewExplore);
        progressBar = findViewById(R.id.progress_horizontal);
        spinner = findViewById(R.id.types_spinner);
        textViewCategory = findViewById(R.id.textViewCategory);
        priceRangeBar = findViewById(R.id.rangeBar1);
        accessibilityRangeBar = findViewById(R.id.rangeBar2);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewPaymentFree = findViewById(R.id.textViewPayment);
        moneyBag = findViewById(R.id.money_bag);
        icOnePerson = findViewById(R.id.ic_person);
        icTwoPerson = findViewById(R.id.ic_two_person);
        icThreePerson = findViewById(R.id.ic_three_persons);
        icFourPerson = findViewById(R.id.ic_four_persons);


    }


    public void onRangeBar() {
        priceRangeBar.addOnChangeListener((slider, value, fromUser) -> {
            price = slider.getValues();
            minPrice = price.get(0);
            maxPrice = price.get(1);
            Log.e("yoyo", price.toString());
        });


        accessibilityRangeBar.addOnChangeListener((slider, value, fromUser) -> {
            accessibility = slider.getValues();
            minAccessibility = accessibility.get(0);
            maxAccessibility = accessibility.get(1);
            Log.e("yoyo", accessibility.toString());

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
            IntroActivity.start(this);
       // startActivity(new Intent(this, IntroActivity.class));

    }

    public void participantsQuantity(BoredAction b) {
        switch (b.getParticipants()) {
            case 1:
                if (b.getParticipants() == 1) {
                    icOnePerson.setVisibility(View.VISIBLE);
                    icTwoPerson.setVisibility(View.INVISIBLE);
                    icThreePerson.setVisibility(View.INVISIBLE);
                    icFourPerson.setVisibility(View.INVISIBLE);
                }
            case 2:
                if (b.getParticipants() == 2) {
                    icTwoPerson.setVisibility(View.VISIBLE);
                    icOnePerson.setVisibility(View.INVISIBLE);
                    icThreePerson.setVisibility(View.INVISIBLE);
                    icFourPerson.setVisibility(View.INVISIBLE);
                }
            case 3:
                if (b.getParticipants() == 3) {
                    icThreePerson.setVisibility(View.VISIBLE);
                    icTwoPerson.setVisibility(View.INVISIBLE);
                    icOnePerson.setVisibility(View.INVISIBLE);
                    icFourPerson.setVisibility(View.INVISIBLE);
                }
            case 4:
                if (b.getParticipants() == 4) {
                    icFourPerson.setVisibility(View.VISIBLE);

                    icTwoPerson.setVisibility(View.INVISIBLE);
                    icThreePerson.setVisibility(View.INVISIBLE);
                    icOnePerson.setVisibility(View.INVISIBLE);
                }


        }
    }
//

    public void next(View view) {
        App.boredApiClient.getAction(valueOfSpinner, null, null, maxPrice, minPrice, null, minAccessibility, maxAccessibility, new BoredApiClient.BoredActionCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(BoredAction boredAction) {
                Log.e("yoyo", boredAction.toString());
                textViewExplore.setText(boredAction.getActivity());
                textViewPrice.setText(MessageFormat.format("{0} $ ", boredAction.getPrice().toString()));
                participantsQuantity(boredAction);
                progressBar.setProgress((int) (boredAction.getAccessibility()*100),true);

            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
        Log.e("yoyo", valueOfSpinner);

    }


}



