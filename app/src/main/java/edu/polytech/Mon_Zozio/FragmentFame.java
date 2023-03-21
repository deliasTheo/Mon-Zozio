package edu.polytech.Mon_Zozio;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;



public class FragmentFame extends Fragment {
    private final String TAG = "polytech "+getClass().getSimpleName();
    private String attachedActivity;

    public FragmentFame() {
        Log.d(TAG, "Fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fame, container, false);

        return view; }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachedActivity = getActivity().getClass().getSimpleName();
    }
}
