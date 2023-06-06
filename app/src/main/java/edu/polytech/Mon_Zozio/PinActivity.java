package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class PinActivity extends AppCompatActivity implements ClickableMenuItem<Integer>, ClickableActivity {

    private MapView map;
    private SearchView searchView;
    private BirdMarkerList birdMarkerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        FragmentMenu fragmentFame = new FragmentMenu();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, (Fragment) fragmentFame).commit();

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.pin_activity);

        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        GeoPoint startPoint = new GeoPoint(46.603354, 1.888334); // Coordonnées du centre de la France
        IMapController mapController = map.getController();
        mapController.setZoom(6.0); // Zoom suffisamment élevé pour afficher toute la France
        mapController.setCenter(startPoint);

        birdMarkerList = new BirdMarkerList();
        ArrayList<BirdMarker> birdMarkers = (ArrayList<BirdMarker>) birdMarkerList.getBirdMarkers();

        ArrayList<OverlayItem> items = new ArrayList<>();
        for (BirdMarker birdMarker : birdMarkers) {
            OverlayItem marker = new OverlayItem(birdMarker.getBirdName(), "", birdMarker.getCoordinates());
            items.add(marker);
        }

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<>(getApplicationContext(), items, new ItemizedOverlayWithFocus.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });
    }

    private void performSearch(String query) {
        map.getOverlays().clear();

        ArrayList<OverlayItem> items = new ArrayList<>();
        ArrayList<BirdMarker> birdMarkers = (ArrayList<BirdMarker>) birdMarkerList.getBirdMarkers();
        for (BirdMarker birdMarker : birdMarkers) {
            if (birdMarker.getBirdName().toLowerCase().contains(query.toLowerCase())) {
                OverlayItem marker = new OverlayItem(birdMarker.getBirdName(), "", birdMarker.getCoordinates());
                items.add(marker);
            }
        }

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<>(getApplicationContext(), items, new ItemizedOverlayWithFocus.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });
        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);

        map.invalidate();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public String getKeyValue(int id) {
        return getString(R.string.NUM_ACTIVITY);
    }

    @Override
    public void onClick(int position) {

    }
}
