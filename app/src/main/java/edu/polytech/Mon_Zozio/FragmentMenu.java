package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fred on 17/03/2018.
 */

public class FragmentMenu extends Fragment {
    private final String TAG = "polytech "+getClass().getSimpleName();
    private String attachedActivity;

    private int currentSelectedItem;
    private ImageView user, moto, car, basket;
    private ClickableMenuItem<Integer> activity;

    public FragmentMenu() {
        Log.d(TAG, "FragmentMenu created");
        setCurrentSelectedItem(0);
    }

    public int getCurrentSelectedItem() {
        return currentSelectedItem;
    }

    private void setCurrentSelectedItem(int currentSelectedItem) {
        this.currentSelectedItem = currentSelectedItem;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments!=null)  {
            setCurrentSelectedItem( getArguments().getInt(getString(R.string.VALUE_FOR_MENU_FRAGMENT)) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        //Instancier vos composants graphique ici (faîtes vos findViewById)
        Log.d(TAG," fragment display menu ");

        user = view.findViewById(R.id.home);
        moto = view.findViewById(R.id.loupe);
        car = view.findViewById(R.id.pin);
        basket = view.findViewById(R.id.profil);

        user.setImageResource(R.drawable.maison);
        moto.setImageResource(R.drawable.loupe);
        car.setImageResource(R.drawable.pin);
        basket.setImageResource(R.drawable.profil);
        switch (currentSelectedItem){
            case 0: user.setImageResource(R.drawable.maison); break;
            case 1: moto.setImageResource(R.drawable.loupe); break;
            case 2: car.setImageResource(R.drawable.pin); break;
            case 3: basket.setImageResource(R.drawable.profil); break;
        }

        user.setOnClickListener( click -> {
            try {
                activity.onClick(ActivityFactory.MAIN_ACTIVITY);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
        moto.setOnClickListener( click -> {
            try {
                activity.onClick(ActivityFactory.MUSIC_ACTIVITY);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
        car.setOnClickListener( click -> {
            try {
                activity.onClick(ActivityFactory.PIN_ACTIVITY);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
        basket.setOnClickListener( click -> {
            try {
                activity.onClick(ActivityFactory.PROFIL_ACTIVITY);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachedActivity = getActivity().getClass().getSimpleName();
        List<Class<?>> interfaces = Arrays.asList(getActivity().getClass().getInterfaces());
        if (interfaces==null || !interfaces.contains(ClickableMenuItem.class)) try {
            throw new Throwable(attachedActivity + " must implements ClickableActivity");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        activity = (ClickableMenuItem<Integer>)getActivity();
    }


}
