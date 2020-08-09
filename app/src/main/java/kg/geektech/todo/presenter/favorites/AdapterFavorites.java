package kg.geektech.todo.presenter.favorites;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.like.LikeButton;
import com.like.OnLikeListener;
import java.util.ArrayList;
import java.util.List;
import kg.geektech.todo.App;
import kg.geektech.todo.R;
import kg.geektech.todo.model.BoredAction;

public class AdapterFavorites extends RecyclerView.Adapter<AdapterFavorites.ViewHolder> {

    List<BoredAction> addedToFavList = new ArrayList<>();
    private ItemViewClick onItemViewClick;
    public AdapterFavorites(List<BoredAction> addedToFavList) {
        this.addedToFavList = addedToFavList;

    }

    public void AdapterFavorites(ItemViewClick itemViewClick) {
        this.onItemViewClick = itemViewClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorites, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        holder.bind(addedToFavList.get(pos));
    }

    @Override
    public int getItemCount() {
        return addedToFavList.size();
    }

    public void setBoredAction(
            List<BoredAction> boredActions) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategory, textViewExplore, textViewPrice, textViewPaymentFree;
        ProgressBar progressBar;
        ImageView moneyBag, icOnePerson, icTwoPerson, icThreePerson, icFourPerson, replace_icons;
        LikeButton like;

        public ViewHolder(@NonNull View view) {
            super(view);
            initViews(view);
            }

        public void initViews(View view) {
            textViewExplore = view.findViewById(R.id.textViewExplore);
            progressBar = view.findViewById(R.id.progress_horizontal);
            textViewCategory = view.findViewById(R.id.textViewCategory);
            textViewPrice = view.findViewById(R.id.textViewPrice);
            textViewPaymentFree = view.findViewById(R.id.textViewPayment);
            moneyBag = view.findViewById(R.id.money_bag);
            icOnePerson = view.findViewById(R.id.ic_person);
            icTwoPerson = view.findViewById(R.id.ic_two_person);
            icThreePerson = view.findViewById(R.id.ic_three_persons);
            icFourPerson = view.findViewById(R.id.ic_four_persons);
            replace_icons = view.findViewById(R.id.replace_icons);
            like = view.findViewById(R.id.likeButton2);
           like.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onItemViewClick.onItemClick(getAdapterPosition());
               }
           });
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bind(BoredAction boredAction) {
            textViewCategory.setText(boredAction.getType());
            textViewExplore.setText(boredAction.getActivity());
            textViewPrice.setText(boredAction.getPrice().toString());
            setVisibilityOfImages();


            switch (boredAction.getParticipants()) {
                case 1:
                    replace_icons.setImageResource(R.drawable.ic_one_user);
                    break;
                case 2:
                    replace_icons.setImageResource(R.drawable.ic_two_users);
                    break;
                case 3:
                    replace_icons.setImageResource(R.drawable.ic_three_persons);
                    break;
                case 4:
                    replace_icons.setImageResource(R.drawable.ic_four_persons);
                    break;
            }
            progressBar.setProgress((int) (boredAction.getAccessibility() * 100), true);
        }
        public void setVisibilityOfImages() {
            replace_icons.setVisibility(View.VISIBLE);
            icOnePerson.setVisibility(View.GONE);
            icTwoPerson.setVisibility(View.GONE);
            icThreePerson.setVisibility(View.GONE);
            icFourPerson.setVisibility(View.GONE);
        }
    }
}
