package com.karim.vatask

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import com.karim.vatask.Adpter.Adapter
import com.karim.vatask.Operation.Calculation
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.lang.Thread.sleep
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), LocationListener {
    var op=""
    var lastOperation=""
    lateinit var items:ArrayList<String>
    lateinit var gridAdapter:Adapter
    var nightMode=false
    lateinit var fnumberQueue:Queue<String>
    lateinit var snumberQueue:Queue<String>
    lateinit var opQueue:Queue<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fnumberQueue = LinkedList<String>()
        snumberQueue = LinkedList<String>()
        opQueue= LinkedList<String>()
        items= ArrayList()
        changeColorModeSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            nightMode = isChecked
            chnageMode()
        })
    }
    /**
     *
     * This function is used to change the colors of the application
     * depend on the switch value
     */
    private fun chnageMode() {
        var currentModeColorPrimary=resources.getColor(R.color.white)
        if(nightMode)
            currentModeColorPrimary=resources.getColor(R.color.dark_color)
        result.setBackgroundColor(currentModeColorPrimary)
        allToolsContainer.setBackgroundColor(currentModeColorPrimary)
        gridView.setBackgroundColor(currentModeColorPrimary)
    }

    /**
     * This function is the all onClick actions in the all application with specific time given to handle it in the background
     * ... which depend on the text inside the view
     * ..... if the text -> "="
     * ........ we check either the op variable which contain the current operation is empty or
     *          no if not we check the entered number of the user if exist then we run evaluate  function
     * ....... else we save the view text to the op variable
     * @param view which is current clickable tool in the UI.
     */
    fun buttonClick(view:View){
        val buttonTextView=view as TextView
        val buttonTextVal=buttonTextView.text.toString()
        when(buttonTextView.text){
            "="->{
                val sNumberText=sNumber.text.toString()
                val fNumberText=fNumber.text.toString()
                val timeStr=time.text.toString()
                when {
                    sNumberText == "" -> Toast.makeText(this,"please enter the second number", Toast.LENGTH_SHORT).show()
                    fNumberText == "" -> Toast.makeText(this,"please enter the first number", Toast.LENGTH_SHORT).show()
                    op=="" -> Toast.makeText(this,"please chose an operation", Toast.LENGTH_SHORT).show()
                    timeStr==""->Toast.makeText(this,"please enter the time", Toast.LENGTH_SHORT).show()
                    else -> {
                        fnumberQueue.add(fNumberText)
                        snumberQueue.add(sNumberText)
                        opQueue.add(op)
                        lastOperation=fNumberText+op+sNumberText
                        items.add(lastOperation)
                        gridAdapter= Adapter(this, R.layout.custome_gride_item,items)
                        gridView.adapter=gridAdapter

                        sNumber.setText("")
                        fNumber.setText("")
                        time.setText("")

                        val thread=Thread(){
                            sleep(timeStr.toLong()*1000)
                            evaluate(fnumberQueue.poll(), snumberQueue.poll(),opQueue.poll())
                            clearFromGrid()
                        }
                        val pool: ExecutorService = Executors.newFixedThreadPool(3)
                        pool.execute(thread)
                        pool.shutdown()
                        changeAllTextViewsColor(buttonTextView)
                    }
                }
            }
            else ->{
                view.background=(ContextCompat.getDrawable(this, R.drawable.selcted_text_view) );
                view.setTextColor(resources.getColor(R.color.white))
                changeAllTextViewsColor(view)
                op=buttonTextVal
            }
        }
    }

    private fun clearFromGrid() {
        runOnUiThread{
            items.removeAt(0)
            gridAdapter=Adapter(this,R.layout.custome_gride_item,items)
            gridView.adapter=gridAdapter
        }
    }

    /**
     * This function is used to  change the other tools
     * color when one clicked and return it the default theme
     */
    private fun changeAllTextViewsColor(view: TextView){
        when(view.id){
            R.id.plusTextView ->{
                changeOneViewBackground(subtractionTextView)
                changeOneViewBackground(divisonTextView)
                changeOneViewBackground(multplicationTextView)
            }
            R.id.subtractionTextView ->{
                changeOneViewBackground(plusTextView)
                changeOneViewBackground(divisonTextView)
                changeOneViewBackground(multplicationTextView)
            }
            R.id.multplicationTextView ->{
                changeOneViewBackground(subtractionTextView)
                changeOneViewBackground(divisonTextView)
                changeOneViewBackground(plusTextView)
            }
            R.id.divisonTextView ->{
                changeOneViewBackground(subtractionTextView)
                changeOneViewBackground(plusTextView)
                changeOneViewBackground(multplicationTextView)
            }
            else ->{
                changeOneViewBackground(subtractionTextView)
                changeOneViewBackground(plusTextView)
                changeOneViewBackground(multplicationTextView)
                changeOneViewBackground(divisonTextView)
            }
        }
    }

    /**
     * This function is used to change the background to be inactive
     */
    private fun changeOneViewBackground(view: TextView){
        view.background=(ContextCompat.getDrawable(this, R.drawable.tools_text_view_design) );
        view.setTextColor(resources.getColor(R.color.orange))
    }

    /**\
     * this function is used to check the operation and
     * call the specific method from the calculation class
     * which set the result to the application UI
     *
     * and put every formula and result in pair
     * and push it the operation stack
     *
     * save every input number with the operation to the grid view
     */
    private fun evaluate(fNumber:String,SNumber:String,currentOp:String){
        when(currentOp){
            "+"->{
                result.text= Calculation.sum(fNumber.toDouble(), SNumber.toDouble())
            }
            "-"->{
                result.text= Calculation.subtraction(fNumber.toDouble(), SNumber.toDouble())
            }
            "*"->{
                result.text= Calculation.multiplication(fNumber.toDouble(), SNumber.toDouble())
            }
            "/"->{
                result.text= Calculation.division(fNumber.toDouble(), SNumber.toDouble())
            }
        }
        op=""

    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val MY_PERMISSIONS_REQUEST_LOCATION = 99

    fun getGPS(view: View) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder(this)
                    .setTitle(R.string.title_location_permission)
                    .setMessage(R.string.text_location_permission)
                    .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialogInterface, i -> //Prompt the user once explanation has been shown
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                MY_PERMISSIONS_REQUEST_LOCATION)
                        fusedLocationClient.lastLocation
                            .addOnSuccessListener(this) { location -> // Got last known location. In some rare situations this can be null.
                                location?.let { onLocationChanged(it) }
                            }
                    })
                    .create()
                    .show()
            return
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION)
        }
        fusedLocationClient.lastLocation
                .addOnSuccessListener(this) { location -> // Got last known location. In some rare situations this can be null.
                    location?.let { onLocationChanged(it) }
                }
    }

    override fun onLocationChanged(location: Location?) {
        //You had this as int. It is advised to have Lat/Loing as double.

        //You had this as int. It is advised to have Lat/Loing as double.
        val lat: Double = location!!.getLatitude()
        val lng: Double = location!!.getLongitude()
        val locale = Locale("ar")
        val geoCoder = Geocoder(this, locale)
        val builder = StringBuilder()
        try {
            val address = geoCoder.getFromLocation(lat, lng, 1)
            val maxLines = address[0].maxAddressLineIndex
            for (i in 0 until maxLines) {
                val addressStr = address[0].getAddressLine(i)
                builder.append(addressStr)
                builder.append(" ")
            }
            val addressFinal = address[0].adminArea
            gps.text=addressFinal
        } catch (e: IOException) {
            // Handle IOException
        } catch (e: NullPointerException) {
            // Handle NullPointerException
        }
    }
}