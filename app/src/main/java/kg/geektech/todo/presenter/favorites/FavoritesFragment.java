package kg.geektech.todo.presenter.favorites;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.Collections;

import kg.geektech.todo.App;
import kg.geektech.todo.R;
import kg.geektech.todo.model.BoredAction;
import kg.geektech.todo.presenter.ViewModel;

public class FavoritesFragment extends Fragment {
    private LikeButton like;
    private ArrayList<BoredAction> list = new ArrayList<>();

    AdapterFavorites adapterFavorites;
    private FavoritesViewModel mFavoritesViewModel;


    public FavoritesFragment() {
    }

    public static Fragment newInstance() {
        return new FavoritesFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        list.addAll(App.boredStorage.getAllActions());
        adapterFavorites = new AdapterFavorites(list);
        adapterFavorites.AdapterFavorites(new ItemViewClick() {
            @Override
            public void onItemClick(int adapterPosition) {
                App.boredStorage.deleteBoredAction(list.get(adapterPosition));
                list.remove(adapterPosition);
                adapterFavorites.notifyDataSetChanged();


            }
        });
        recyclerView.setAdapter(adapterFavorites);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        like = view.findViewById(R.id.likeButton2);
        add();

              //  ViewModel viewModel = ViewModelProvider.of(new FragmentActivity().get(ViewModel.class));
//
//        viewModel.getLiveDataAllActions().observe(getActivity(), new Observer<List<BoredAction>>() {
//            @Override
//            public void onChanged(List<BoredAction> boredActions) {
//                //update recycler view
//                adapterFavorites.setBoredAction(boredActions);
//
//            }
//        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if (list.isEmpty())
//            Toast.makeText(getContext(), "Your list with favorites activities is empty ", Toast.LENGTH_SHORT).show();
        Log.e("yoyo", "OnActivityCreated fav fragment ");
        //  mFavoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            switch (direction) {
//                case ItemTouchHelper.LEFT:
//                    deleteBySwipe(viewHolder);
//                    Toast.makeText(getActivity(), "item will be removed from the list", Toast.LENGTH_SHORT).show();
//                    break;
//                case ItemTouchHelper.RIGHT:
//                    deleteBySwipe(viewHolder);
//                    break;
//            }
            int pos = viewHolder.getAdapterPosition();
            App.boredStorage.deleteBoredAction(list.get(pos));;
            list.remove(pos);
            adapterFavorites.notifyDataSetChanged();

        }
    };

//    public void deleteBySwipe(RecyclerView.ViewHolder viewHolder) {
//        int pos = viewHolder.getAdapterPosition();
//        App.boredStorage.deleteBoredAction(list.get(pos));;
//        list.remove(pos);
//        adapterFavorites.notifyDataSetChanged();
//    }

//    public void deleteByTwitLike() {
//        if (like != null) {
//            like.setOnLikeListener(new OnLikeListener() {
//                @Override
//                public void liked(LikeButton likeButton) {
//                    Log.e("yoyo", likeButton.toString());
//                }
//
//                @Override



//                        Log.e("yoyo", "deleting boredAction in method of twit dislike");
//
//                    }
//
//            });
//        }
//    }


        @Override
        public void onResume () {
            super.onResume();
            Log.e("yoyo", "onResume Of fav fragment with deleteByTwitLike");
            add();
        }


        public void add () {
            //App.boredStorage.getAllAlive().obser)
            list.clear();
            list.addAll(App.boredStorage.getAllActions());
            Log.e("yoyo", App.boredStorage.getAllActions().toString());
            Collections.reverse(list);
            Log.e("yoyo", "reversing the list");
            adapterFavorites.notifyDataSetChanged();

        }
    }