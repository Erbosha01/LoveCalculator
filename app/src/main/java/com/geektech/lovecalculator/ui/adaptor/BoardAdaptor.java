package com.geektech.lovecalculator.ui.adaptor;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geektech.lovecalculator.R;
import com.geektech.lovecalculator.databinding.OnBoardingBinding;

import javax.inject.Inject;


public class BoardAdaptor extends RecyclerView.Adapter<BoardAdaptor.ViewHolder> {
    @Inject
    SharedPreferences preferences;

    private String[] images = {"https://cdn2.vectorstock.com/i/1000x1000/64/71/love-word-on-white-background-hand-drawn-vector-15946471.jpg",
            "https://cdn2.vectorstock.com/i/1000x1000/77/66/hearts-and-word-love-on-white-background-vector-1127766.jpg",
            "https://dm0qx8t0i9gc9.cloudfront.net/watermarks/image/rDtN98Qoishumwih/vector-illustration-of-a-love-tree-on-isolated-white-background_f1acrooO_SB_PM.jpg",
            "https://i.pinimg.com/564x/87/74/7b/87747b479e065c22b5abc36a4e6d18da.jpg"};
    private String[] text = {"Welcome", "Check", "your", "compatibility"};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(OnBoardingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return text.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private OnBoardingBinding binding;

        public ViewHolder(OnBoardingBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(int position) {
            Glide.with(binding.imgBoard).load(images[position]).into(binding.imgBoard);
            binding.txtBoard.setText(text[position]);
        }
    }
}
