package com.example.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void showToast(Context context, String mensaje, int duracion) {
        Toast.makeText(context, mensaje, duracion).show();
    }
}
