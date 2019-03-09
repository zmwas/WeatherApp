package com.newsapp.weatherapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsapp.weatherapp.R;
import com.newsapp.weatherapp.WeatherViewModelFactory;
import com.newsapp.weatherapp.databinding.BookmarksListLayoutBinding;
import com.newsapp.weatherapp.model.BookMark;
import com.newsapp.weatherapp.viewModel.BookMarkViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class BookMarkFragment extends Fragment implements RecyclerViewCallback {
    @Inject
    WeatherViewModelFactory factory;
    BookMarkViewModel viewModel;
    BookmarksListLayoutBinding binding;
    RecyclerView recyclerView;
    BookMarksAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bookmarks_list_layout, container, false);
        recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        viewModel = ViewModelProviders.of(this, factory).get(BookMarkViewModel.class);
        viewModel.fetchBookMarks().observe(this, bookMarks -> {
            if (bookMarks != null) {
                adapter = new BookMarksAdapter(getContext(), this);
                adapter.setBookMarksList(bookMarks);
                recyclerView.setAdapter(adapter);

            } else {
                recyclerView.setVisibility(View.GONE);
                binding.noBookmarksText.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(int position, BookMark bookMark, View v) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable("bookmark", bookMark);
        fragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDelete(int position, BookMark bookMark, View v) {
        viewModel.deleteBookMark(bookMark);
    }
}
