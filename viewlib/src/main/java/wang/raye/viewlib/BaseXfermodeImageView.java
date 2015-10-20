package wang.raye.viewlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * 实现各种效果的ImageView的父控件
 * @author Ray
 * @date 2015年6月19日10:23:24
 * @version 1.0
 */
public abstract class BaseXfermodeImageView extends ImageView {
	
	private static final Xfermode sXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//  private BitmapShader mBitmapShader;
	/** 显示的形状的bitmap*/
	private Bitmap maskBitmap;
	/** 画笔对象*/
	private Paint mPaint;
	/** 最终效果的Bitmap缓存对象*/
	private WeakReference<Bitmap> weakBitmap;

	public BaseXfermodeImageView(Context context){
		super(context);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
	public BaseXfermodeImageView(Context context, AttributeSet attrs){
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
	public BaseXfermodeImageView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	protected void onDraw(Canvas canvas) {
		Bitmap bitmap = weakBitmap == null ? null : weakBitmap.get();
		if(bitmap == null || bitmap.isRecycled()){
			//为空或者已经被释放了内存,获取设置的图片Drawable
			Drawable drawable = getDrawable();
			if(drawable == null){
				//并没有设置图片
				return ;
			}
			//获取图片的宽高，用来计算缩放比例
			int width = drawable.getIntrinsicWidth();
			int height = drawable.getIntrinsicHeight();
			
			//根据控件的宽高创建一个Bitmap，用于底图
			bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
			float scale = getScale(width, height);
			
			Canvas bitmapCanvas = new Canvas(bitmap);
			
			
			//对图片进行缩放
			int swidth = (int)(width*scale);
			int sheight = (int) (height*scale);
		
			drawable.setBounds(0, 0,swidth ,sheight);
			drawable.draw(bitmapCanvas);
			
			if(maskBitmap == null || maskBitmap.isRecycled()){
				maskBitmap = getBitmap();
			}
			mPaint.reset();
			mPaint.setXfermode(sXfermode);
			//绘制形状
			bitmapCanvas.drawBitmap(maskBitmap, 0,0, mPaint);
			mPaint.setXfermode(null);
			//将准备好的bitmap绘制出来
			canvas.drawBitmap(bitmap, 0, 0, null);
			//缓存起来
			weakBitmap = new WeakReference<Bitmap>(bitmap);
		}else{
			//有缓存，直接绘制
			canvas.drawBitmap(bitmap, 0f,0f, mPaint);
		}
	}
	
	public void invalidate() {
		weakBitmap = null;
		if(maskBitmap != null && !maskBitmap.isRecycled()){
			maskBitmap.recycle();
			maskBitmap = null;
		}
		super.invalidate();
	}
	
	/**
	 * 计算出缩放比例
	 * @param width Drawable的宽度
	 * @param height Drawable的高度
	 * @return
	 */
	public abstract float getScale(int width,int height);
	/**
	 * 创建显示形状的bitmap
	 * @return
	 */
	public abstract Bitmap getBitmap();
}
