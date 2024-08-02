package com.example.taller2;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ClothingAdapter extends RecyclerView.Adapter<ClothingAdapter.ClothingViewHolder> {

    private List<ClothingItem> clothingItems;
    private Context context;

    public ClothingAdapter(List<ClothingItem> clothingItems, Context context) {
        this.clothingItems = clothingItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ClothingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clothing, parent, false);
        return new ClothingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothingViewHolder holder, int position) {
        ClothingItem clothingItem = clothingItems.get(position);
        holder.nameTextView.setText(clothingItem.getName());
        holder.priceTextView.setText(clothingItem.getPrice());
        holder.descriptionTextView.setText(clothingItem.getDescription());
        holder.imageView.setImageResource(clothingItem.getImageResourceId());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog(clothingItem.getImageResourceId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return clothingItems.size();
    }

    class ClothingViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        TextView descriptionTextView;
        ImageView imageView;

        ClothingViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

    private void showImageDialog(int imageResourceId) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_image);

        ImageView enlargedImageView = dialog.findViewById(R.id.enlarged_image_view);
        enlargedImageView.setImageResource(imageResourceId);

        dialog.show();
    }
}