package Processors;

import Entities.History;
import Entities.Recommendable;
import Entities.Song;

import java.util.ArrayList;

public class HistoryProcessor implements Recommendable {
    @Override
    public ArrayList<Song> recommend(History songs){
        ArrayList<Song> new_playlist = new ArrayList<Song>();
        for (int i = 0; i < 10; i++){
            int index = (int) ((Math.random() * ((songs.previous_songs.size()-1))));
            ArrayList<Song> playlist = songs.previous_songs.get(index);
            int index2 = (int) ((Math.random() * 9));
            Song song = playlist.get(index2);
            if (!(new_playlist.contains(song))){
                new_playlist.add(song);
            }
        }
        return new_playlist;
    }

    public void addToHistory (ArrayList<String> playlist, History history){


    }
}
