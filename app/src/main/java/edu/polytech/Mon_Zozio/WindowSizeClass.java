package edu.polytech.Mon_Zozio;

import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;

public enum WindowSizeClass {

    COMPACT, MEDIUM, EXPANDED;

    public static WindowSizeClass[] computeWindowSizeClasses(Resources resources, AppCompatActivity activity) {
        WindowMetrics metrics = WindowMetricsCalculator.getOrCreate()
                .computeCurrentWindowMetrics(activity);

        float widthDp = metrics.getBounds().width() /
                resources.getDisplayMetrics().density;
        WindowSizeClass widthWindowSizeClass;

        if (widthDp < 600f) {
            widthWindowSizeClass = WindowSizeClass.COMPACT;
        } else if (widthDp < 840f) {
            widthWindowSizeClass = WindowSizeClass.MEDIUM;
        } else {
            widthWindowSizeClass = WindowSizeClass.EXPANDED;
        }

        float heightDp = metrics.getBounds().height() /
                resources.getDisplayMetrics().density;
        WindowSizeClass heightWindowSizeClass;

        if (heightDp < 480f) {
            heightWindowSizeClass = WindowSizeClass.COMPACT;
        } else if (heightDp < 900f) {
            heightWindowSizeClass = WindowSizeClass.MEDIUM;
        } else {
            heightWindowSizeClass = WindowSizeClass.EXPANDED;
        }
        WindowSizeClass[] sizes = {heightWindowSizeClass, widthWindowSizeClass};
        return sizes;
        // Use widthWindowSizeClass and heightWindowSizeClass.
    }
}
