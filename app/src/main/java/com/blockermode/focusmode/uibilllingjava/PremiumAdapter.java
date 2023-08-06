package com.blockermode.focusmode.uibilllingjava;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blockermode.focusmode.uibilllingjava.model.PremiumSelectionDetail;

import java.util.ArrayList;

public class PremiumAdapter extends RecyclerView.Adapter<PremiumAdapter.PremiumViewHolder> {
    private Activity activity;
    private ArrayList<PremiumSelectionDetail> list;
    private int checkedPosition = 0; // -1: no selection  0: 1st item selection

    public PremiumAdapter(Activity activity, ArrayList<PremiumSelectionDetail> list) {
        this.activity = activity;
        this.list = list;
    }

    public void setPremiumSelection(ArrayList<PremiumSelectionDetail> premiumSelection) {
        this.list = premiumSelection;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PremiumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_premium_list, parent, false);
        return new PremiumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PremiumViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PremiumViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rl_main;
        TextView product_title;
        ImageView image_status;

        public PremiumViewHolder(@NonNull View itemView) {
            super(itemView);

            rl_main = itemView.findViewById(R.id.rl_main);
            product_title = itemView.findViewById(R.id.product_title);
            image_status = itemView.findViewById(R.id.image_status);
        }

        void bind(final PremiumSelectionDetail premiumSelectionDetail) {
            if (checkedPosition == -1) {
                image_status.setImageResource(R.drawable.ic_circle_checked2);
            } else {

                if (checkedPosition == getAdapterPosition()) {
                    image_status.setImageResource(R.drawable.ic_circle_checked);

                } else {
                    image_status.setImageResource(R.drawable.ic_circle_checked2);
                }

            }

            product_title.setText(premiumSelectionDetail.getPrice());
            itemView.setOnClickListener(view -> {
                image_status.setImageResource(R.drawable.ic_circle_checked);
                if (checkedPosition != getAdapterPosition()) {
                    notifyItemChanged(checkedPosition);
                    checkedPosition = getAdapterPosition();
                }
            });
        }
    }

    public PremiumSelectionDetail getSelected() {
        if (checkedPosition != -1) {
            return list.get(checkedPosition);
        }

        return null;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
