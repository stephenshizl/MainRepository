package com.example.a61555.picassoframeworkdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

/**
 * Created by 61555 on 2017/8/1.
 * 问题：因为图片在加载的时候由于尺寸未知（特别是高度），所以Item需要重新计算自己的尺寸，这样又会导致重绘，
 *      由于瀑布流一次会加载多张图片，所以就产生了，Item切换、闪烁、跳页等问题。
 *      说到底就是在加载图片前，先确定Item的尺寸，避免Item重新计算自己的尺寸。
 * 解决方案：将加载完成的图片高度信息存储起来，再次加载该图片时直接从数组里调用高度信息
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ImageViewHolder> {

    private String[] picUrls;//存储图片下载链接
    private int[] picHeight;//以加载图片的高度
    private Context context;
    private int windowWidth;//屏幕宽度
    private int spanCount;//列数
    private int itemCount = 100;//图片总数

    public RecyclerViewAdapter(String[] picUrls, Context context, int windowWidth, int spanCount) {
        this.picUrls = picUrls;
        this.picHeight = new int[picUrls.length];
        this.context = context;
        this.windowWidth = windowWidth;
        this.spanCount = spanCount;
    }

    /**
     *  返回所要显示的View
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ImageViewHolder(view);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
            holder.loadImage(picUrls[position%picUrls.length], position%picUrls.length);
    }

    /**
     * 返回Item总数（也就是设置图片显示总数）
     * @return
     */
    @Override
    public int getItemCount() {
        return itemCount;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        /**
         * 参数<View itemView>为onCreateViewHolder中
         * LayoutInflater.from(context).inflate(R.layout.item, parent, false)的View
         * @param itemView
         */
        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }

        /**
         *  下载图片，加载到ImageView，并把高度记录到数组中
         * @param url       图片下载链接
         * @param position  于图片下载链接数组中的位置
         */
        public void loadImage(String url, final int position) {

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new EventMessage(picUrls[position], 1));//点击图片，创建新Fragment作为图片容器
                }
            });
            /*
                如果图片已经加载过，直接从存储高度的数组里取值
                如果图片未加载过，通过计算得出图片的高度
             */
            //图片已加载
            if (picHeight[position] > 0) {
                Picasso.with(context).load(url)
                        .resize(getTargetWidth(), picHeight[position])
                        .centerCrop()
                        .placeholder(R.drawable.load)
                        .into(imageView);
            }
            //图片未加载
            else {
                final int height = getTargetHeight();
                Picasso.with(context).load(url)//下载图片
                        .resize(getTargetWidth(), height)//重新设置图片高度和宽度
                        .centerCrop()//剪切图片
                        .placeholder(R.drawable.load)//设置加载时显示的图片
                        .error(R.drawable.load)//设置加载错误时显示的图片
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                picHeight[position] = height;//将高度记录到数组中
                            }

                            @Override
                            public void onError() {

                            }
                        });
            }
        }

        /**
         * 计算出图片显示宽度
         * @return
         */
        public int getTargetWidth() {
            return (windowWidth - (DisplayUtil.dip2px(context, 2)*(spanCount+1)))/spanCount;
        }

        /**
         * 计算初图片显示高度
         * @return
         */
        public int getTargetHeight() {
            Random random = new Random();
            return getTargetWidth() + 100 + 50*random.nextInt(5);
        }
    }
}
