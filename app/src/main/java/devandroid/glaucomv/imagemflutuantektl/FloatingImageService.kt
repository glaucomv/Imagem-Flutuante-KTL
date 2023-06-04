package devandroid.glaucomv.imagemflutuantektl

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView

class FloatingImageService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatingImage: ImageView

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        // Cria a janela flutuante
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.START
        params.x = 0
        params.y = 0

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        floatingImage = inflater.inflate(R.layout.layout_floating_image, null) as ImageView

        windowManager.addView(floatingImage, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (floatingImage != null) {
            windowManager.removeView(floatingImage)
        }
    }
}
