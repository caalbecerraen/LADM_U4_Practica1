package mx.tecnm.tepic.ladm_u4_practica1

import android.content.ContentProvider
import android.content.Context
import android.content.Context.*
import android.graphics.*
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import android.util.LogPrinter
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService

class Lienzo (p:MainActivity,d:SensorManager,v:SensorManager): View(p),SensorEventListener{

    val mosca1= BitmapFactory.decodeResource(resources,R.drawable.mosca)
    var mosca= Bitmap.createScaledBitmap(mosca1, 200 /*Ancho*/, 200 /*Alto*/, true /* filter*/)
    var xsens=0f
    var ysens=0f
    var zsens=0f
    var p=100f
    val f=d
    val k=v
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val pd = Paint()
        c.drawARGB(p.toInt(),p.toInt(),p.toInt(),p.toInt())
        f.registerListener(this,f.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        f.registerListener(this,f.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL)
        pd.setColor(Color.BLUE)
        pd.textSize = 60f
        //c.drawText("Ancho "+xsens+" \n Alto "+ysens+"\nZ:"+zsens,100f,300f,pd)
        c.drawBitmap(mosca,xsens*75,zsens*150,pd)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        return true
    }

    override fun onSensorChanged(event: SensorEvent) {
       if(Sensor.TYPE_ACCELEROMETER==event.sensor.type){
           xsens=event.values[0]
           ysens=event.values[1]
           zsens=event.values[2]
           invalidate()
       }
        if(Sensor.TYPE_PROXIMITY==event.sensor.type){
            p=event.values[0]*1000
            invalidate()
        }

    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}