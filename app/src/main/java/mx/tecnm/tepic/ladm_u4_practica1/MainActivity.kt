package mx.tecnm.tepic.ladm_u4_practica1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View

class MainActivity : AppCompatActivity(),SensorEventListener{
    lateinit var sensorManager: SensorManager
    lateinit var sensorManager2: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
        sensorManager2=getSystemService(Context.SENSOR_SERVICE)as SensorManager
        //sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
        // SensorManager.SENSOR_DELAY_NORMAL)
        //setContentView(R.layout.activity_main)
        setContentView(Lienzo(this,sensorManager,sensorManager2))
    }
    override fun onSensorChanged(event: SensorEvent) {
        //0=x
        //1=y
        //2=z
        var act4 = Intent(this,Lienzo::class.java)
        act4.putExtra("X",event.values[0])
        act4.putExtra("Y",event.values[1])
        act4.putExtra("Z",event.values[2])
        //TextView.setText("Coordenadas:\nX: ${event!!.values[0]}\nY:${event!!.values[1]}\nZ:${event!!.values[2]}\n")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}