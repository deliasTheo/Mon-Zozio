package edu.polytech.Mon_Zozio;

import java.util.List;

public interface PostExecuteActivity<T> {
    void onPostExecuteBirds(List<T> itemList);
    void runOnUiThread( Runnable runable);
}
