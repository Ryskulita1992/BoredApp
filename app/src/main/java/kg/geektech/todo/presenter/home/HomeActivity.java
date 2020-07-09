package kg.geektech.todo.presenter.home;

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
import androidx.appcompat.widget.SwitchCompat;
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

public class HomeActivity extends BaseActivity {
    Spinner spinner;
    RangeSlider priceRangeBar, accessibilityRangeBar;
    TextView textViewCategory, textViewExplore, textViewPrice, textViewPaymentFree;
    ProgressBar progressBar;
    String valueOfSpinner;
    Float minPrice, maxPrice, minAccessibility, maxAccessibility;
    ImageView moneyBag, icOnePerson, icTwoPerson, icThreePerson, icFourPerson, replace_icons;
    private List<Float> price, accessibility;
    SwitchCompat nightMode;

    public static void start(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        skipIntroIfShown();
        onSpinnerClick();
        onRangeBar();
        //selectDarkMode();
//        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(navView, navController);


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
        replace_icons = findViewById(R.id.replace_icons);
        nightMode=findViewById(R.id.darkModeSwitch);

    }
//    private void selectDarkMode() {
//        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    checkNightModeActivated(true);
//                    recreate();
//
//                }else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    checkNightModeActivated(false);
//                    recreate();
//                }
//            }
//        });
//
//    }
//
//    private void checkNightModeActivated(boolean b) {
//        boolean isNightModeSelected= new AppPreferences(this).isNightModeSelected();
//        if (isNightModeSelected){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            nightMode.setChecked(true);
//            Log.e("yoyo", "night mode selected" );}
//             selectDarkMode();
//            //textViewCategory.setBackground(R.drawable.);
//
//
//    }


    public void onRangeBar() {
        priceRangeBar.addOnChangeListener((slider, value, fromUser) -> {
            try {
                price = slider.getValues();
                minPrice = price.get(0);
                maxPrice = price.get(1);
                Log.e("yoyo", price.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                if (price == null) {
                    toast("there is no such a price ");
                    e.getMessage();
                }
            }
        });
        accessibilityRangeBar.addOnChangeListener((slider, value, fromUser) -> {
            try {
                accessibility = slider.getValues();
                minAccessibility = accessibility.get(0);
                maxAccessibility = accessibility.get(1);
                Log.e("yoyo", accessibility.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                if (accessibility == null) {
                    toast("there is no such accessibility");
                }
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
            IntroActivity.start(this);
        // startActivity(new Intent(this, IntroActivity.class));

    }

    public void setVisibilityOfImages() {
        replace_icons.setVisibility(View.VISIBLE);
        icOnePerson.setVisibility(View.GONE);
        icTwoPerson.setVisibility(View.GONE);
        icThreePerson.setVisibility(View.GONE);
        icFourPerson.setVisibility(View.GONE);

    }

    public void participantsQuantity(BoredAction b) {
        switch (b.getParticipants()) {
            case 1:
                setVisibilityOfImages();
                if (b.getParticipants() == 1)
                    replace_icons.setImageResource(R.drawable.ic_one_user);
                break;
            case 2:
                if (b.getParticipants() == 2)
                    replace_icons.setImageResource(R.drawable.ic_two_users);
                break;
            case 3:
                if (b.getParticipants() == 3)
                    replace_icons.setImageResource(R.drawable.ic_three_persons);
                break;
            case 4:
                if (b.getParticipants() == 4)
                    replace_icons.setImageResource(R.drawable.ic_four_persons);
                break;
        }
    }
    public void next(View view) {
        App.boredApiClient.getAction(valueOfSpinner, null, null, maxPrice, minPrice,
                null, minAccessibility, maxAccessibility,
                new BoredApiClient.BoredActionCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(BoredAction boredAction) {
                Log.e("yoyo", boredAction.toString());
                textViewExplore.setText(boredAction.getActivity());
                textViewPrice.setText(MessageFormat.format("{0} $ ", boredAction.getPrice().toString()));
                participantsQuantity(boredAction);
                progressBar.setProgress((int) (boredAction.getAccessibility() * 100), true);

            }
            @Override
            public void onFailure(Exception exception) {

            }
        });
        Log.e("yoyo", valueOfSpinner);

    }



}