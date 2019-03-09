package com.newsapp.weatherapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.newsapp.weatherapp.databinding.BookmarkItemBinding;
import com.newsapp.weatherapp.model.BookMark;

import java.util.List;

public class BookMarksAdapter extends RecyclerView.Adapter<BookMarksAdapter.BookMarkViewHolder> {

    Context context;
    List<BookMark> bookMarks;
    RecyclerViewCallback callback;

    public BookMarksAdapter(Context context, RecyclerViewCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public BookMarkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        BookmarkItemBinding binding = BookmarkItemBinding.inflate(inflater, viewGroup, false);
        return new BookMarkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookMarkViewHolder viewHolder, int i) {
        BookMark bookMark = bookMarks.get(i);
        viewHolder.bind(bookMark);
        viewHolder.binding.getRoot().setOnClickListener(v -> callback.onItemClick(i, bookMark, v));
        ((ImageButton)viewHolder.binding.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onDelete(i, bookMark, v);
            }
        });
    }

    public void setBookMarksList(List<BookMark> bookMarks) {
        this.bookMarks = bookMarks;
    }

    @Override
    public int getItemCount() {
        return bookMarks.size();
    }

    public class BookMarkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        BookmarkItemBinding binding;

        public BookMarkViewHolder(@NonNull BookmarkItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(BookMark bookMark) {
            binding.cityName.setText(bookMark.getName());
            binding.countryName.setText(bookMark.getCountry());
        }

        @Override
        public void onClick(View v) {

        }
    }
}
