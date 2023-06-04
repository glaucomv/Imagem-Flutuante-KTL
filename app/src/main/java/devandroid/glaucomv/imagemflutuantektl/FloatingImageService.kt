package devandroid.glaucomv.imagemflutuantektl

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.widget.ImageView

class FloatingImageService : Service() {
    private var windowManager: WindowManager? = null
    private var floatingImage: ImageView? = null
    private var offsetX = 0f
    private var offsetY = 0f
    private var originalX = 0
    private var originalY = 0
    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        floatingImage = ImageView(this)
        floatingImage!!.setImageResource(R.drawable.retangulo)
        floatingImage!!.setBackgroundColor(Color.WHITE)
        floatingImage!!.visibility = View.VISIBLE
        val layoutParams = WindowManager.LayoutParams(
            270,  // Largura da imagem
            60,  // Altura da imagem
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )
        layoutParams.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        //layoutParams.gravity = Gravity.TOP | Gravity.START;
        //layoutParams.x = 210;  // Posição X da margem esquerda
        layoutParams.y = 115 // Posição Y do topo
        floatingImage!!.setOnClickListener {
            // Lógica de clique aqui
        }
        floatingImage!!.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    offsetX = event.rawX
                    offsetY = event.rawY
                    originalX = layoutParams.x
                    originalY = layoutParams.y
                    return@OnTouchListener true
                }

                MotionEvent.ACTION_MOVE -> {
                    layoutParams.x = (originalX + (event.rawX - offsetX)).toInt()
                    layoutParams.y = (originalY + (event.rawY - offsetY)).toInt()
                    windowManager!!.updateViewLayout(floatingImage, layoutParams)
                    return@OnTouchListener true
                }
            }
            false
        })
        windowManager!!.addView(floatingImage, layoutParams)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (floatingImage != null && windowManager != null) {
            windowManager!!.removeView(floatingImage)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}