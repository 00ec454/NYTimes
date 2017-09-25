package com.dharmesh.nytimes.adapters;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    public void clear() {
        articles.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void addArticles(List<Article> newArticles) {
        Log.i(TAG, "addArticles: newArticles "+newArticles.size());
        int currentSize = articles.size();
        this.articles.addAll(newArticles);
        notifyItemRangeChanged(currentSize, newArticles.size());
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
            itemView.setOnClickListener(view -> openArticle(article.getWebUrl()));
        }
    }

    private void openArticle(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        builder.addDefaultShareMenuItem();


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        int requestCode = 100;

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.share);

        builder.setActionButton(bitmap, context.getString(R.string.share), pendingIntent, true);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
