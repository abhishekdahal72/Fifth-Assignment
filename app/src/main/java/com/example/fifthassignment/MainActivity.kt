package com.example.fifthassignment

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var etJoinedDate: EditText;
    private lateinit var etDateOfBirth: EditText;
    private lateinit var tvJoinedDate: TextView;
    private lateinit var tvDateOfBirth: TextView;
    private lateinit var btnClear: Button;
    private lateinit var btnCalculate: Button;

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etJoinedDate = findViewById(R.id.etJoinDate);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        tvJoinedDate = findViewById(R.id.tvJoinedDate);
        tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        btnClear = findViewById(R.id.btnClear);
        btnCalculate = findViewById(R.id.btnCalculate);

        //Edit Text Date of Birth

        etDateOfBirth.setOnClickListener {
            loadDatePicker(etDateOfBirth);

        }

        //Edit text Joined Date
        etJoinedDate.setOnClickListener {
            loadDatePicker(etJoinedDate);
        }

        btnCalculate.setOnClickListener {

            val calender = Calendar.getInstance();
            val y = calender.get(Calendar.YEAR);
            val m = calender.get(Calendar.MONTH) + 1;
            val d = calender.get(Calendar.DAY_OF_MONTH);
            val final = "$m/$d/$y";
            val dateFormat = SimpleDateFormat("MM/dd/yyyy"); // assign date format

            // Calculate Date of Birth
            calculateDateOfBirth(dateFormat = dateFormat, final = final);

            //Calculate Date of Joined
            calculateDateJoined(dateFormat = dateFormat, finalDate = final);

        }

        //set views to default value
        btnClear.setOnClickListener {
            resetField();
        }
    }

    fun resetField() {
        etDateOfBirth.setText("");
        etJoinedDate.setText("")
        tvJoinedDate.setText("");
        tvDateOfBirth.setText("");

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadDatePicker(view: View) {

        var datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val date = "${month + 1}/${dayOfMonth}/${year}";
                    when (view.id) {
                        R.id.etJoinDate -> {
                            etJoinedDate.setText(date);

                        }
                        R.id.etDateOfBirth -> {
                            etDateOfBirth.setText(date);
                        }
                    }

                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    //Calculate DateofBirth
    fun calculateDateOfBirth(dateFormat: SimpleDateFormat, final: String) {

        val etDOB = etDateOfBirth.text.toString();

        var d1: Date = Date()
        var d2: Date = Date()
        try {
            d1 = dateFormat.parse(final);
            d2 = dateFormat.parse(etDOB);
        } catch (e: Exception) {
            Toast.makeText(this, "You can calculate Date of Birth as well", Toast.LENGTH_LONG).show()
            if (final.equals("")) {

            } else if (etDOB.equals("")) {

            }
            return;
        }

        val diff: Long = abs(d1.time - d2.time);
        val diffDate = diff / (24 * 60 * 60 * 1000);
        val year = diffDate / 365;
        val r = diffDate % 365;
        val month = r / 30;
        val days = r % (30 * 24);

        val res = "You are $year years - $month months - $days days old";
        tvDateOfBirth.setText(res);

    }

    //Calculate Company Joined years
    fun calculateDateJoined(dateFormat: SimpleDateFormat, finalDate: String) {

        val etJoinedDate = etJoinedDate.text.toString();
        var d1: Date = Date()
        var d2: Date = Date()
        try {
            d1 = dateFormat.parse(finalDate);
            d2 = dateFormat.parse(etJoinedDate);
        } catch (e: Exception) {
            Toast.makeText(this, "You can calculate Date of Joined as well", Toast.LENGTH_LONG).show()
            if (etJoinedDate.equals("")) {

            } else if (finalDate.equals("")) {

            }
            return;
        }

        val diff: Long = abs(d1.time - d2.time);
        val diffDate = diff / (24 * 60 * 60 * 1000);
        val year = diffDate / 365;
        val r = diffDate % 365;
        val month = r / 30;
        val days = r % (30 * 24);

        val res =
                "You spent ${year} years ${month} months and ${days} days";
        tvJoinedDate.setText(res);

    }

}