package wang.raye.viewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
/**
 * 圆角图片的控件
 * 
 * @author Ray
 * @date 2015年6月19日10:37:44
 * @version 1.0
 */
public class RoundImage extends BaseXfermodeImageView {

	/** 圆角的大小 */
	private int mBorderRadius;

	public RoundImage(Context context) {
		super(context);
	}

	public RoundImage(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundImage(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.RoundImage);
		mBorderRadius = array.getDimensionPixelSize(0, 0);
	}

	public Bitmap getBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()),
				mBorderRadius, mBorderRadius, paint);
		return bitmap;
	}

	public float getScale(int width, int height) {
		float scale = Math.max(getWidth() * 1.0f / width, getHeight() * 1.0f
				/ height);
		return scale;
	}

}
