package edu.polytech.Mon_Zozio;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class Culture implements Parcelable {
    private String name;
    private int duration;
    private int picture;

    public Culture(String name, int duration,  int picture) {
        this.name = name;
        this.duration = duration;
        this.picture = picture;
    }

    protected Culture(Parcel in) {
        name = in.readString();
        duration = in.readInt();
        picture = in.readInt();
    }
    public int getDuration() {
        return duration;
    }

    public int getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name+" ("+(duration/60)+"min)";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(duration);
        parcel.writeInt(picture);
    }

    public static final Creator<Culture> CREATOR = new Creator<Culture>() {
        @Override
        public Culture createFromParcel(Parcel in) {
            return new Culture(in);
        }

        @Override
        public Culture[] newArray(int size) {
            return new Culture[size];
        }
    };
}

// next version ? https://github.com/erik-sytnyk/movies-list/blob/master/db.json
class Videos extends ArrayList<Culture> {
    public Videos(){
        add( new Culture("ishtar",69*60,R.drawable.ishtar));
        add( new Culture("jaws",103*60,R.drawable.jaws));
        add( new Culture("rocky",97*60,R.drawable.rocky));
        add( new Culture("scary",113*60,R.drawable.scary));
        add( new Culture("smile",107*60,R.drawable.smile));
        add( new Culture("soleil",91*60, R.drawable.soleil));
        add( new Culture("lopez vs lopez",132*60,R.drawable.lopez));
        add( new Culture("this is it",145*60,R.drawable.thisisit));
        add( new Culture("X men",147*60,R.drawable.xmen));
    }
}
