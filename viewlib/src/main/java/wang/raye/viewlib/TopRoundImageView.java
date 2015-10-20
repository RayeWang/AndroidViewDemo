package wang.raye.viewlib;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
/**
 * 只有顶部有圆角的自定义图片控件
 * @author Ray
 * @date 2015年6月19日14:23:58
 * @version 1.0
 */
public class TopRoundImageView extends BaseXfermodeImageView {
	/** 圆角大小*/
	private int mBorderRadius;

	public TopRoundImageView(Context context){
		super(context);
	}
	public TopRoundImageView(Context context, AttributeSet attrs){
		this(context, attrs,0);
	}
	public TopRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TopRoundImageView);
		mBorderRadius = array.getDimensionPixelSize(0, 0);
	}

	public float getScale(int width, int height) {
		return 1.0f;
	}

	public Bitmap getBitmap() {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadii(new float[]{mBorderRadius,mBorderRadius,
				mBorderRadius,mBorderRadius,0,0,0,0});
		Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, getWidth(), getHeight());
		drawable.draw(canvas);
		return bitmap;
	}

}
