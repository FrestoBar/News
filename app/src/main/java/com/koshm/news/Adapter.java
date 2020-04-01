package com.koshm.news;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.koshm.news.models.Article;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Article> articles;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public Adapter(List<Article> paramList, Context paramContext) {
        this.articles = paramList;
        this.context = paramContext;
    }

    public int getItemCount() {
        return this.articles.size();
    }

    public void onBindViewHolder(@NonNull final MyViewHolder holder, int paramInt) {
        Article article = this.articles.get(paramInt);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder((Drawable) Utils.getRandomDrawbleColor());
        requestOptions.error((Drawable) Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        Glide.with(this.context).load(article.getUrlToImage()).apply(requestOptions).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException param1GlideException, Object param1Object, Target<Drawable> param1Target, boolean param1Boolean) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            public boolean onResourceReady(Drawable param1Drawable, Object param1Object, Target<Drawable> param1Target, DataSource param1DataSource, boolean param1Boolean) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).transition((TransitionOptions) DrawableTransitionOptions.withCrossFade()).into(holder.imageView);
        holder.title.setText(article.getTitle());
        holder.desc.setText(article.getDescription());
        holder.source.setText(article.getSource().getName());
        TextView textView = holder.time;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" • ");
        stringBuilder.append(Utils.DateToTimeFormat(article.getPublishedAt()));
        textView.setText(stringBuilder.toString());
        holder.published_ad.setText(Utils.DateFormat(article.getPublishedAt()));
        holder.author.setText(article.getAuthor());
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item, paramViewGroup, false), this.onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView author;

        TextView desc;

        ImageView imageView;

        OnItemClickListener onItemClickListener;

        ProgressBar progressBar;

        TextView published_ad;

        TextView source;

        TextView time;

        TextView title;

        public MyViewHolder(View itemView, OnItemClickListener param1OnItemClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.title = itemView.findViewById(R.id.title);
            this.desc = itemView.findViewById(R.id.desc);
            this.author = itemView.findViewById(R.id.author);
            this.published_ad = itemView.findViewById(R.id.publishedAt);
            this.source = itemView.findViewById(R.id.source);
            this.time = itemView.findViewById(R.id.time);
            this.imageView = itemView.findViewById(R.id.img);
            progressBar = itemView.findViewById(R.id.progress_load_photo);
            this.onItemClickListener = param1OnItemClickListener;
        }

        public void onClick(View param1View) {
            this.onItemClickListener.onItemClick(param1View, getAdapterPosition());
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View param1View, int param1Int);
    }
}


/* Location:              D:\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\com\koshm\news\Adapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */