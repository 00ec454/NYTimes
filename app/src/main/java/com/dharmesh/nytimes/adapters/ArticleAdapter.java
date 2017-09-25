package com.dharmesh.nytimes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dharmesh.nytimes.R;
import com.dharmesh.nytimes.models.Article;

import java.util.ArrayList;
import java.util.List;


public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = ArticleAdapter.class.getSimpleName();
    private final Context context;
    private List<Article> articles;
    private static final String NYTIMES_URL = "http://www.nytimes.com/";
    private static final int VIEW_TYPE_WITH_IMAGE = 1;
    private static final int VIEW_TYPE_WITHOUT_IMAGE = 0;
    private final LayoutInflater layoutInflater;

    public ArticleAdapter(Context context) {
        this.context = context;
        this.articles = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_WITH_IMAGE) {
            View view = layoutInflater.inflate(R.layout.article_item_with_image, parent, false);
            return new ArticleWithImageViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.article_item, parent, false);
            return new ArticleNoImageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_WITH_IMAGE) {
            ((ArticleWithImageViewHolder) holder).bind(articles.get(position));
        } else {
            ((ArticleNoImageViewHolder) holder).bind(articles.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return articles.get(position).getMultiMedia().isEmpty() ? VIEW_TYPE_WITHOUT_IMAGE : VIEW_TYPE_WITH_IMAGE;
    }

    class ArticleWithImageViewHolder extends ArticleNoImageViewHolder {

        private ImageView ivMedia;

        ArticleWithImageViewHolder(View view) {
            super(view);
            ivMedia = view.findViewById(R.id.ivMedia);
        }

        void bind(Article article) {
            super.bind(article);
            Glide.with(context)
                    .load(NYTIMES_URL + article.getMultiMedia().get(0).getUrl())
                    .into(ivMedia);
        }
    }

    class ArticleNoImageViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHeadline;
        private TextView tvSnippet;

        ArticleNoImageViewHolder(View view) {
            super(view);
            tvHeadline = view.findViewById(R.id.tvHeadline);
            tvSnippet = view.findViewById(R.id.tvSnippet);
        }

        void bind(Article article) {
            tvHeadline.setText(article.getHeadline().getMain());
            tvSnippet.setText(article.getSnippet());
        }
    }
}
