package kg.geektech.todo.presenter.intro;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

import kg.geektech.todo.R;
import kg.geektech.todo.data.abstractActivityAndFrag.BaseFragment;

public class IntraFragment extends BaseFragment {
    LottieAnimationView lottie;
    public static final String ARG_POSITION = "position";

    public static Fragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        IntraFragment fragment = new IntraFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    public IntraFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_intra;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int position = getArguments().getInt(ARG_POSITION);
        switch (position) {
            case 0:
               lottie.setAnimation(R.raw.first);
                break;
            case 1:
                lottie.setAnimation(R.raw.second);
                break;
            case 2:
                lottie.setAnimation(R.raw.third);
                break;

        }
    }
}



