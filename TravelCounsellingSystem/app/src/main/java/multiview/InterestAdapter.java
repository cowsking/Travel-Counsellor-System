package multiview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TravelCounsellingSystem.R;

import java.util.List;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.ViewHolder>{

    private List<Interest> mInterestList;
    SparseBooleanArray clicked;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View InterestView;
        ImageView InterestImage;
        TextView InterestName;
        CheckBox check;

        public ViewHolder(View view) {
            super(view);
            InterestView = view;
            InterestImage = view.findViewById(R.id.interest_image);
            InterestName = view.findViewById(R.id.interest_name);
            check = view.findViewById(R.id.check_name);
        }
    }

    public InterestAdapter(List<Interest> interestList, SparseBooleanArray click) {
        mInterestList = interestList;
        clicked = click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interests_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.InterestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Interest interest = mInterestList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + interest.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.InterestImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Interest interest = mInterestList.get(position);
                if(!interest.getName().equals("")){
                    Toast.makeText(v.getContext(), "City "+ mInterestList.size(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent("android.intent.action.SHOW");
                    intent.putExtra("AttractionName",interest.getName());
                    intent.putExtra("CityName",interest.getCityName());
                    System.out.println("city name is"+interest.getCityName());
                    view.getContext().startActivity(intent);
                }
                else{
                    Toast.makeText(v.getContext(), "SUBMIT! "+ mInterestList.size(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent("android.intent.action.SHOW");
                    intent.putExtra("AttractionName",interest.getName());
                    intent.putExtra("CityName",interest.getCityName());
                    view.getContext().startActivity(intent);



                }
            }
        });
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Interest interest = mInterestList.get(position);
                if(holder.check.isChecked()){
                    clicked.put(holder.getAdapterPosition(),true);
                    Toast.makeText(v.getContext(), "clicked " + clicked.toString(), Toast.LENGTH_SHORT).show();

                }
                else{
                    clicked.put(holder.getAdapterPosition(),false);
                    Toast.makeText(v.getContext(), "clicked " + clicked.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Interest interest = mInterestList.get(position);
        holder.InterestImage.setImageBitmap(interest.getImageId());
        holder.InterestName.setText(interest.getName());
    }

    @Override
    public int getItemCount() {
        return mInterestList.size();
    }

}
