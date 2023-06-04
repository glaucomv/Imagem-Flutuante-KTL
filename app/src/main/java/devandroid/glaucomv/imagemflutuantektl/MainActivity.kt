package devandroid.glaucomv.imagemflutuantektl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val floatingImageServiceIntent = Intent(this, FloatingImageService::class.java)
        startService(floatingImageServiceIntent)
    }
}
