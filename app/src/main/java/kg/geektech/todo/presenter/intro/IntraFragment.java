package kg.geektech.todo.presenter.intro;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

import kg.geektech.todo.R;
import kg.geektech.todo.data.abstractActivityAndFrag.BaseFragment;

import static kg.geektech.todo.R.drawable.*;

public class IntraFragment extends Fragment {
    ImageView image;
    private LinearLayout fragmentIntro;
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

//    @Override
//    protected int getViewLayout() {
//        return R.layout.fragment_intra;
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image=view.findViewById(R.id.imageViewGorIntro);

        assert getArguments() != null;
        int position = getArguments().getInt(ARG_POSITION);
        switch (position) {
            case 0:
                fragmentIntro.setBackgroundResource(R.color.colorLightBlue);

                image.setImageResource(bored);
                break;
            case 1:
                fragmentIntro.setBackgroundResource(R.color.colorLilac);
                image.setImageResource(bored_ic);
                break;
            case 2:
                fragmentIntro.setBackgroundResource(R.color.colorPrimaryDark);

                //lottie.setAnimation(R.raw.third);
                break;

        }
    }
}



