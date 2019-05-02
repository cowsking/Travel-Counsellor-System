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

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{

    private List<City> mCityList;
    SparseBooleanArray clicked;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        CheckBox check;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
            check = view.findViewById(R.id.check_name);
        }
    }

    public CityAdapter(List<City> cityList, SparseBooleanArray click) {
        mCityList = cityList;
        clicked = click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                City city = mCityList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + city.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                City city = mCityList.get(position);
                if(!city.getName().equals("")){
                    Toast.makeText(v.getContext(), "you clicked image " + city.getName(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(v.getContext(), "SUBMIT! "+ mCityList.size(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent("android.intent.action.MAP");
                    view.getContext().startActivity(intent);


                }
            }
        });
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                City city = mCityList.get(position);
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
        City city = mCityList.get(position);
        holder.fruitImage.setImageResource(city.getImageId());
        holder.fruitName.setText(city.getName());
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

}
