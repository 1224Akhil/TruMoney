package com.example.expensemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanager.Models.Category;
import com.example.expensemanager.R;
import com.example.expensemanager.databinding.SampleCategoryItemBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    ArrayList<Category> categorys;

    public interface CategoryClickLIstner{
      void onCategoryClicked(Category category);
    };

    CategoryClickLIstner categoryClickLIstner;

    public CategoryAdapter(Context context, ArrayList<Category> categorys, CategoryClickLIstner categoryClickLIstner) {
        this.context = context;
        this.categorys = categorys;
        this.categoryClickLIstner = categoryClickLIstner;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CategoryViewHolder(LayoutInflater.from(context).
                inflate(R.layout.sample_category_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categorys.get(position);
        holder.binding.categoryText.setText(category.getCategoryName());
        holder.binding.categoryIcon.setImageResource(category.getCategoryImage());

        holder.binding.categoryIcon.setBackgroundTintList(context.getColorStateList(category.getCategoryColor()));

        holder.itemView.setOnClickListener(v->{
            categoryClickLIstner.onCategoryClicked(category);
        });
    }

    @Override
    public int getItemCount() {
        return categorys.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        SampleCategoryItemBinding binding;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SampleCategoryItemBinding.bind(itemView);
        }
    }
}
