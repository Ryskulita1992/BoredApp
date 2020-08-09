package kg.geektech.todo.presenter.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.RangeSlider;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;

import kg.geektech.todo.App;
import kg.geektech.todo.R;
import kg.geektech.todo.model.BoredAction;
import kg.geektech.todo.model.BoredApiClient;


public class HomeFragment extends Fragment {
    Spinner spinner;
    RangeSlider priceRangeBar, accessibilityRangeBar;
    TextView textViewCategory, textViewExplore, textViewPrice, textViewPaymentFree;
    ProgressBar progressBar;
    String valueOfSpinner;
    Float minPrice, maxPrice, minAccessibility, maxAccessibility;
    ImageView moneyBag, icOnePerson, icTwoPerson, icThreePerson, icFourPerson, replace_icons;


    private List<Float> price, accessibility;
    BoredAction br;
    private String key;

    LikeButton likeButton;
    Button next;


    public HomeFragment() {
    }

    public static Fragment newInstance() {

        return new HomeFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        next();
        onSpinnerClick();
        onRangeBar();
        setLikeDislike();

    }

    public void initViews(View view) {
        textViewExplore = view.findViewById(R.id.textViewExplore);
        progressBar = view.findViewById(R.id.progress_horizontal);
        spinner = view.findViewById(R.id.types_spinner);
        textViewCategory = view.findViewById(R.id.textViewCategory);
        priceRangeBar = view.findViewById(R.id.rangeBar1);
        accessibilityRangeBar = view.findViewById(R.id.rangeBar2);
        textViewPrice = view.findViewById(R.id.textViewPrice);
        textViewPaymentFree = view.findViewById(R.id.textViewPayment);
        moneyBag = view.findViewById(R.id.money_bag);
        icOnePerson = view.findViewById(R.id.ic_person);
        icTwoPerson = view.findViewById(R.id.ic_two_person);
        icThreePerson = view.findViewById(R.id.ic_three_persons);
        icFourPerson = view.findViewById(R.id.ic_four_persons);
        replace_icons = view.findViewById(R.id.replace_icons);
        next = view.findViewById(R.id.next_main);
        likeButton = view.findViewById(R.id.likeButton);
        progressBar = view.findViewById(R.id.progress_bar);
    }


    public void setLikeDislike() {
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                if (br!=null){
                    App.boredStorage.saveBoredAction(br);}
                else {
                    likeButton.setLiked(false);
                }

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                App.boredStorage.deleteBoredAction(br);
                Log.e("yoyo", br.toString());

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        setLikeDislike();
    }

    public void onRangeBar() {
        priceRangeBar.addOnChangeListener((slider, value, fromUser) -> {
            try {
                price = slider.getValues();
                minPrice = price.get(0);
                maxPrice = price.get(1);
                Log.e("yoyo", price.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                if (price == null) {
                    // toast("there is no such a price ");
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
                    Toast.makeText(getActivity(), "there is no such accessibility", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void onSpinnerClick() {
        if (getActivity() != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.types, android.R.layout.simple_spinner_item);
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
                    // toast("No item is selected");

                }
            });
        }
    }

//    public void skipIntroIfShown() {
//        boolean isFirstLaunched = new AppPreferences(true).isFirstLaunched();
//        if (!isFirstLaunched);
//            IntroActivity.start(this);
////         startActivity(new Intent(this, IntroActivity.class));
//
//    }

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

    private void next() {

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                likeButton.setLiked(false);
                App.boredApiClient.getAction(valueOfSpinner,
                        null,
                        textViewPrice,
                        maxPrice,
                        minPrice,
                        null,
                        minAccessibility,
                        maxAccessibility,
                        new BoredApiClient.BoredActionCallback() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onSuccess(BoredAction boredAction) {
                                try {
                                    progressBar.setVisibility(View.GONE);
                                    key = boredAction.getKey();
                                    br = boredAction;
                                    textViewExplore.setText(boredAction.getActivity());
                                    textViewPrice.setText(boredAction.getPrice().toString() + "$");
                                    participantsQuantity(boredAction);
                                    progressBar.setProgress((int) (boredAction.getAccessibility() * 100), true);
                                } catch (Exception ex) {
                                    Log.e("yoyo", ex.getMessage());

                                }

                            }

                            @Override
                            public void onFailure(Exception exception) {
                                Toast.makeText(getContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                            }

                        });

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.boredStorage.getBoredAction(key) == null) {
            likeButton.setLiked(false);
        }
    }
}