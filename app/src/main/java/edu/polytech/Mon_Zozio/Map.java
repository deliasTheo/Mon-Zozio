//package edu.polytech.Mon_Zozio;
//
//import android.graphics.Typeface;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//public class Map extends BaseAdapter {
//    private Videos videos;
//    private Controller activity;
//    private Culture choosenVideo;
//    private LayoutInflater mInflater;
//
//<<<<<<< HEAD:app/src/main/java/edu/polytech/Mon_Zozio/VideoAdapter.java
//    public VideoAdapter(Controller activity, Videos videos){
//=======
//    public Map(ClickableActivity activity, Videos videos){
//>>>>>>> main:app/src/main/java/edu/polytech/Mon_Zozio/Map.java
//        this.videos=videos;
//        this.activity = activity;
//        mInflater = LayoutInflater.from(activity.getContext());
//    }
//
//
//    @Override
//    public int getCount() {
//        return videos.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return videos.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        //(1) : Réutilisation des layouts
//        View layoutItem = convertView == null ? mInflater.inflate(R.layout.video_layout, parent, false) : convertView;
//
//        //(2) : Récupération des TextView de notre layout
//        TextView displayName = layoutItem.findViewById(R.id.videoName);
//        ImageView pizzaPicture = layoutItem.findViewById(R.id.videoPicture);
//
//        //(3) : Renseignement des valeurs
//        displayName.setText(videos.get(position).getName());
//        if (videos.get(position)==choosenVideo) {
//            displayName.setTextSize(30); displayName.setTypeface(null, Typeface.BOLD_ITALIC);
//        }
//        else {
//            displayName.setTextSize(22); displayName.setTypeface(null, Typeface.NORMAL);
//        }
//        displayName.setText(videos.get(position).getName());
//        pizzaPicture.setImageResource(videos.get(position).getPicture());
//
//        layoutItem.setOnClickListener( view -> {
//            choosenVideo = (videos.get(position) == choosenVideo ? null : videos.get(position));
//            activity.onClick(position);
//        });
//
//        return layoutItem; //On retourne l'item créé.
//    }
//}
