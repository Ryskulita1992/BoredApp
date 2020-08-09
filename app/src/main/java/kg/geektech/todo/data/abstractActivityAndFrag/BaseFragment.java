package kg.geektech.todo.data.abstractActivityAndFrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


abstract public class BaseFragment extends Fragment {

    public abstract void initView(View view);


}
