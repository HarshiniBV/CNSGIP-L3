import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


class song{
    public String title;
    public double time;

    public song(String title, double time){
        this.title=title;
        this.time=time;
    }

    public String getTitle() {
        return title;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return title + " : " + time + "min";
    }
}



class album{
    public String name;
    public String artist;
    public List<song> songs;

    public album(String name, String artist){
        this.name=name;
        this.artist=artist;
        this.songs= new ArrayList<>();
    }

    public boolean addSong(String title, double time){
        if(findSong(title) == null){
            songs.add(new song(title, time));
            return true;
        }
        return false;
    }

    public song findSong(String title){
        for(song checkedSong : this.songs){
            if(checkedSong.getTitle().equals(title)){
                return checkedSong;
            }
        }
        return null;
    }

    public boolean addInPlaylist(String title, List<song> playlist){
       song checkedSong = findSong(title);
       if(checkedSong != null){
        playlist.add(checkedSong);
        return true;
       }
       return false;
    }

    public boolean addInPlaylist(int trackNo, List<song> playList){
        int index = trackNo -1;
        if((index>=0) && (index < this.songs.size())){
            playList.add(this.songs.get(index));
            return true;
        }
        return false;
    }
}



public class MusicApp {
    public static List<album> albums = new ArrayList<>();

    public static void main(String[] args) {

        album album = new album("Map Of The Soul:7", "BTS");
        album.addSong("Boy with Luv(Feat.Halsey)",3.50);
        album.addSong("Make it Right",3.46);
        album.addSong("Black Swan",3.16);
        album.addSong("ON",3.45);
        album.addSong("Friends",4.02);
        albums.add(album);

        album = new album("Face the Sun", "Seventeen");
        album.addSong("Darl+ing",2.55);
        album.addSong("HOT",3.17);
        album.addSong("Shadow",3.32);
        album.addSong("Super",3.56);
        album.addSong("Nice",3.05);
        albums.add(album);

        List<song> playlist = new LinkedList<>();

        albums.get(0).addInPlaylist("Boy with Luv(Feat.Halsey)", playlist);
        albums.get(0).addInPlaylist("Make it Right", playlist);
        albums.get(0).addInPlaylist("Black Swan", playlist);
        albums.get(0).addInPlaylist("ON", playlist);
        albums.get(0).addInPlaylist("Friends", playlist);

        albums.get(1).addInPlaylist(1, playlist);
        albums.get(1).addInPlaylist(2, playlist);
        albums.get(1).addInPlaylist(3, playlist);
        albums.get(1).addInPlaylist(4, playlist);
        albums.get(1).addInPlaylist(5, playlist);

        showAlbums();
        play(playlist);
    }

    public static void showAlbums() {
        System.out.println("Albums present:");
        for (album album : albums) {
            System.out.println(album.name + " by " + album.artist);
            System.out.println("Songs:");
            for (song song : album.songs) {
                System.out.println(song);
            }
            System.out.println();
        }
    }

    public static void play(List<song> playlist){
        try (Scanner scanner = new Scanner(System.in)){
        boolean quit = false;
        boolean forward = true;

        ListIterator<song> listIterator = playlist.listIterator();
        if(playlist.size() == 0){
            System.out.println("No songs found in Playlist");
            return;
        }else{
            System.out.println("Now playing :" + listIterator.next().toString());
            printMenu();
        }

        while(!quit){
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action){
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if(!forward){
                        if(listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if(listIterator.hasNext()){
                        System.out.println("Now playing: " + listIterator.next().toString());
                    }else{
                        System.out.println("We reached the end of playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if(forward){
                        if(listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        forward =false;
                    }
                    if(listIterator.hasPrevious()){
                        System.out.println("Now playing: " + listIterator.previous().toString());
                    } else{
                        System.out.println("We are at starting of playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Replaying: " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Replaying: " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                    }
                    break;
                case 4:
                    printList(playlist);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                if (playlist.size() > 0) {
                    listIterator.remove();
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next());
                    } else if (listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous());
                    }
                }
                break;
                case 7:
                    showAlbums();
                    break;
                    
            }
        }
    }
        
}

    public static void printMenu(){
       System.out.println("Select what u want to do:");
       System.out.println("0 : Quit");
       System.out.println("1 : Next Song");
       System.out.println("2 : Previous Song");
       System.out.println("3 : Replay ");
       System.out.println("4 : Songs in playlist");
       System.out.println("5 : Menu");
       System.out.println("6 : Delete");
       System.out.println("7 : View Albums");

    }

    public static void printList(List<song> playlist){
        System.out.println("================================");
        for(song song : playlist){
            System.out.println(song);
        }
        System.out.println("================================");
        System.out.println("Enjoy Listening More!!!!\n");
    }
    
}