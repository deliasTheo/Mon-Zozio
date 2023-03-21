package edu.polytech.Mon_Zozio;

import android.content.Context;

public interface ClickableActivity {
    Context getContext();
    void onClick(int position);
}
