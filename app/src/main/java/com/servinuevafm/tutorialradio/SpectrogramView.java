package com.servinuevafm.tutorialradio;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SpectrogramView extends View {
    private Paint paint;
    private float[] spectrumData;

    public SpectrogramView(Context context) {
        super(context);

    }

    public SpectrogramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // Inicialización personalizada aquí
        paint = new Paint();
        spectrumData = new float[256]; // Tamaño del espectro
    }

    public void updateSpectrumData(float[] data) {
        spectrumData = data;
        invalidate(); // Redibuja la vista
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int spectrumSize = spectrumData.length;

        // Dibuja el espectrograma utilizando spectrumData y paint
        for (int i = 0; i < spectrumSize; i++) {
            float x = (float) i / spectrumSize * width;
            float y = height - (spectrumData[i] * height);

            canvas.drawLine(x, height, x, y, paint);
        }
    }
}