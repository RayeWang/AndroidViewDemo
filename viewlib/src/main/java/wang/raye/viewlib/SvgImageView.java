package wang.raye.viewlib;

import com.svgandroid.SVG;
import com.svgandroid.SVGParser;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
/**
 * 根据SVG绘制形状的图片控件
 * @author Ray
 * @date 2015年6月19日12:46:12
 * @version 1.0
 */
public class SvgImageView extends BaseXfermodeImageView {
	private int mSvgRawResourceId;
	public SvgImageView(Context context){
		super(context);
	}
	public SvgImageView(Context context, AttributeSet attrs){
		this(context, attrs,0);
	}
	public SvgImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SvgImageView);
		mSvgRawResourceId = array.getResourceId(0, 0);
	}

	public float getScale(int width, int height) {
		return 1.0f;
	}

	public Bitmap getBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if (mSvgRawResourceId > 0) {
            SVG svg = SVGParser.getSVGFromInputStream(
                    getResources().openRawResource(mSvgRawResourceId),
                    getWidth(), getHeight());
            canvas.drawPicture(svg.getPicture());
        } else {
            canvas.drawRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), paint);
        }

        return bitmap;
	}

}
