package wang.raye.viewlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
/**
 * 圆形的图片控件
 * @author Ray
 * @date 2015年6月19日11:28:36
 * @version 1.0
 */
public class CircleImageView extends BaseXfermodeImageView {
	
	/** 控件的宽和高之间的最小值*/
	private int mMin;
	
	public CircleImageView(Context context){
		super(context);
	}
	public CircleImageView(Context context, AttributeSet attrs){
		this(context, attrs, 0);
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mMin = Math.min(getMeasuredWidth(), getMeasuredHeight());
		setMeasuredDimension(mMin, mMin);
	}
	
	
	public float getScale(int width, int height) {
		int min = Math.min(width, height);
		
		return mMin * 1.0f / min;
	}

	public Bitmap getBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(mMin, mMin, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		canvas.drawCircle(mMin / 2, mMin / 2, mMin / 2, paint);
		return bitmap;
	}

}
