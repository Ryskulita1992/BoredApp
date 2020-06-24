package kg.geektech.todo.presenter.intro;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import kg.geektech.todo.R;
import kg.geektech.todo.data.abstractActivityAndFrag.BaseFragment;

public class IntraFragment extends BaseFragment {

    public static final String ARG_POSITION = "position";   //

    public static Fragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        IntraFragment fragment = new IntraFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    public IntraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setIntroSharedPref(introSharedPref);
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
                break;
            case 1:
                //break;
            case 2:
                break;

        }
    }
}



