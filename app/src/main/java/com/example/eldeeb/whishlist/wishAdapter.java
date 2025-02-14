package com.example.eldeeb.whishlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class wishAdapter extends RecyclerView.Adapter<wishAdapter.wishViewHolder> {

    private static final String BASE_URL = "http://lensaty.net/";
    private Context context;
    private List<userwishes> list;

    public wishAdapter(Context context, List<userwishes> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public wishViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.wish_list, null);
        return new wishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull wishViewHolder wishViewHolder, int i) {
        userwishes user = list.get(i);

//loading image
        Picasso.with(context)
                .load(BASE_URL + user.getWishImage())
                .resize(110, 80)
                .centerCrop()
                .error(R.drawable.logo)
                .into(wishViewHolder.wishImage);

        wishViewHolder.wishName.setText(user.getWishName());
        wishViewHolder.wishPrice.setText(user.getWishPrice() + "  LE.");

        wishViewHolder.deltebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Remove Api is not clear for me, so i tried to test it before typing any code using postman and errors begin to appear.
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class wishViewHolder extends RecyclerView.ViewHolder {

        ImageView wishImage;
        TextView wishName, wishPrice;
        ImageButton deltebtn;

        public wishViewHolder(@NonNull View itemView) {
            super(itemView);
            wishImage = itemView.findViewById(R.id.wishimage);
            wishName = itemView.findViewById(R.id.textViewName);
            wishPrice = itemView.findViewById(R.id.textViewPrice);
            deltebtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
