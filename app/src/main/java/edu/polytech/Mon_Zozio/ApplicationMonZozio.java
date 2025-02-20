package edu.polytech.Mon_Zozio;

import android.app.Application;
import android.util.Log;
import android.widget.ListView;


public class ApplicationMonZozio extends Application {
    private final String TAG = "MonZozio " + getClass().getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "APPLICATION START " );
    }

    public void onViewCreated(ListView listView) {
        //create VIEW with XML layout
        PostView view = new PostView(listView, this );
        //create CONTROLLER
        PostController controller = new PostController(PostModel.getInstance(),view);
        //link MVC
        view.setController(controller);
        PostModel.getInstance().addObserver(view);
    }

}
