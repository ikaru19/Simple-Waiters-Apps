package com.syafrizal.cafemenuapps.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.syafrizal.cafemenuapps.Constant;
import com.syafrizal.cafemenuapps.R;
import com.syafrizal.cafemenuapps.activities.MenuActivity;
import com.syafrizal.cafemenuapps.activities.MenuListActivity;
import com.syafrizal.cafemenuapps.models.Pesanan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    EditText etDate , etTime , etTableNumber;
    Button btnCart;
    final Calendar myCalendar = Calendar.getInstance();
    private TimePickerDialog timePickerDialog;


    public MenuFragment() {
        // Required empty public constructor
    }



    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        etDate = view.findViewById(R.id.etDate);
        etTime = view.findViewById(R.id.etTime);
        etTableNumber = view.findViewById(R.id.etTableNumber);
        btnCart = view.findViewById(R.id.btnToCart);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToCart();
            }
        });


        return view;
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDate.setText(sdf.format(myCalendar.getTime()));
    }


    private void showTimeDialog() {
        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                etTime.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(getContext()));

        timePickerDialog.show();
    }


    private void saveToCart(){
        Pesanan pesanan = new Pesanan();
        if (etTime.getText().toString().equals("") || etDate.getText().toString().equals("")|| etTableNumber.getText().toString().equals("")){
            Toast.makeText(getContext(), "Please Check The Form Above !", Toast.LENGTH_SHORT).show();
        }else{
            pesanan.setTanggal(etDate.getText().toString());
            pesanan.setJam(etTime.getText().toString());
            pesanan.setNomorMeja(Integer.parseInt(etTableNumber.getText().toString()));
            Intent i = new Intent(getActivity(), MenuListActivity.class);
            i.putExtra(Constant.KEYPESAN , pesanan);
            startActivity(i);
        }
    }

}
