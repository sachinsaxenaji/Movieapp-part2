package com.sachin.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sachin.movieapp.model.MoviesClass;
import com.sachin.movieapp.utils.NetworkUtils;


import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static final String TAG = MovieAdapter.class.getSimpleName();

    private List<MoviesClass> mMovieItemList;
    private final Context mContext;
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void OnListItemClick(MoviesClass movieItem);
    }

    public MovieAdapter(List<MoviesClass> movieItemList, ListItemClickListener listener, Context context) {

        mMovieItemList = movieItemList;

        mOnClickListener = listener;
        mContext = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mMovieItemList == null ? 0 : mMovieItemList.size();
    }

    public void setMovieData(List<MoviesClass> movieItemList) {
        mMovieItemList = movieItemList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView listMovieItemView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            listMovieItemView = itemView.findViewById(R.id.iv_item_poster);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            MoviesClass movieItem = mMovieItemList.get(listIndex);
            listMovieItemView = itemView.findViewById(R.id.iv_item_poster);
            String posterPathURL = NetworkUtils.buildPosterUrl(movieItem.getImage());
            try {
                Glide.with(mContext)
                        .load(posterPathURL)
                        .into(listMovieItemView);
            } catch (Exception e) {}
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.OnListItemClick(mMovieItemList.get(clickedPosition));
        }
    }

}
