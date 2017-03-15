package com.wii.study.lession_2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by wu on 2017/3/6.
 */

public class ViewHolder {
    /**
     * View容器，用于存放Holer中的View
     * 照顾下小白 SparseArray 是Android推荐使用的一个优化容器，相当于一个Map<integer,View>
     */
    private SparseArray<View> mViews;

    /**
     * Item布局View convertView
     */
    private View mConvertView;

    private int mPostion;
    public ViewHolder(Context context, ViewGroup parent, int layoutId) {
        mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, null);
        mConvertView.setTag(this);
    }

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int postion) {
        this.mPostion = postion;
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, null);
        mConvertView.setTag(this);
    }

    /**
     * 获取ViewHolder
     *
     * @param context
     *            上下文
     * @param convertView
     * @param parent
     * @param layoutId
     *            布局layout Id
     * @param //position
     * @return
     */
    public static ViewHolder getViewHolder(Context context, View convertView,
                                           ViewGroup parent, int layoutId) {

        if (convertView == null)
            return new ViewHolder(context, parent, layoutId);
        return (ViewHolder) convertView.getTag();
    }

    public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int postion) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, postion);
        } else {
            ViewHolder holder = (ViewHolder)convertView.getTag();
            holder.mPostion = postion;
            return holder;
        }
    }

    /**
     * 获取Holder中的ItemView
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {

        View item = mViews.get(viewId);
        if (item == null) {
            item = mConvertView.findViewById(viewId);
            mViews.put(viewId, item);
        }
        return (T) item;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }


    public  ViewHolder setHtml(int viewId,String text){
        TextView tv=getView(viewId);
        tv.setText(Html.fromHtml(text));
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bm);
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return

    public ViewHolder setImageByUrl(int viewId, String url)
    {
        ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
                (ImageView) getView(viewId));
        return this;

    }

    **/
    public ViewHolder setImageByUrl(Context context,int viewId, String url) {
        ImageView imageView;
        if(viewId==-1){
            imageView=new ImageView(context);
        }else{
            imageView= getView(viewId);
        }
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader loader=ImageLoader.getInstance();
        loader.init(config);
        loader.displayImage(url,imageView);
        return this;
    }



    /**
     * 获取convertView
     *
     * @return
     */
    public View getMConvertView() {
        return mConvertView;
    }

}
