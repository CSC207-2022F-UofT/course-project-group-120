package UI;

import Boundaries.SongRecInputBoundary;
import Boundaries.SongRecOutputBoundary;
import Controllers.SongRecController;
import Entities.Song;
import Entities.SongPool;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SongRecWindow extends JFrame implements ActionListener {
    JPanel mainPanel;

    JPanel bottomPanel;

    JLabel song1, song2, song3, song4, song5;

    JLabel image1, image2, image3, image4, image5;

    ArrayList<Song> recommendedSongs;

    String[] artistImages;
    ArrayList<String> availableArtistImages;
    public static JButton finishedButton;

    SongPool songPool;

    SongRecOutputBoundary presenter;

    SongRecInputBoundary useCaseInteractor;

    SongRecController controller;

    ArrayList<Integer> sliderValues;

    public SongRecWindow(SongPool inputSongPool, SongRecOutputBoundary inputPresenter,
                         SongRecController inputController, SongRecInputBoundary inputInteractor,
                         ArrayList<Integer> inputSliderValues) {
        artistImages = new String[]{"Adele", "Ariana Grande", "Beyonce", "Bruno Mars", "Drake",
                "Ed Sheeran", "Eminem", "Jennifer Lopez", "Justin Bieber", "Justin Timberlake", "Katy Perry",
                "Lady Gaga", "Maroon 5", "One Direction", "Pitbull", "Rihanna", "The Black Eyed Peas",
                "The Chainsmokers", "The Weeknd"};
        availableArtistImages = new ArrayList<String>();

        songPool = inputSongPool;
        presenter = inputPresenter;
        controller = inputController;
        useCaseInteractor = inputInteractor;
        sliderValues = inputSliderValues;

        initializeSongRecWindow();
    }

    private void initializeSongRecWindow() {
        controller.generate(sliderValues);
        ArrayList<Song> songNameSet = presenter.get5RecSongs(); // now have our songs

        // panel with songs on it
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.getHSBColor(164,219,232));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 120, 30, 120));
        mainPanel.setLayout(new GridLayout(5, 1));

        // bottom panel with button
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.getHSBColor(0,0,(float) 0.2)); // dark grey
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 40, 30));
        bottomPanel.setLayout(new GridLayout());

        // initializing frames w/ panels
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setTitle("Song Recommendation!");
        setSize(1200, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(false); // needs to be because we want only visible AFTER user presses submit results button

        finishedButton = new JButton("DONE!");
        finishedButton.setSize(50, 50);
        finishedButton.setHorizontalAlignment(SwingConstants.CENTER);
        finishedButton.addActionListener(this);
        finishedButton.setVerticalAlignment(SwingConstants.CENTER);
        finishedButton.setBackground(Color.getHSBColor(0,0,1));
        finishedButton.setHorizontalAlignment(SwingConstants.LEFT);
        finishedButton.setVerticalAlignment(SwingConstants.CENTER);
        finishedButton.setFont(new Font("Helvetica", Font.BOLD, 20));

        // formatting song recs:
        // song names
        song1 = new JLabel("<HTML>" + songNameSet.get(0).getSong() + " by. " + songNameSet.get(0).getArtist() +
                "</HTML>");
        song1.setFont(new Font("Helvetica", Font.BOLD, 20));
        song2 = new JLabel("<HTML>" + songNameSet.get(1).getSong() + " by. " + songNameSet.get(1).getArtist() +
                "</HTML>");
        song2.setFont(new Font("Helvetica", Font.BOLD, 20));
        song3 = new JLabel("<HTML>" + songNameSet.get(2).getSong() + " by. " + songNameSet.get(2).getArtist() +
                "</HTML>");
        song3.setFont(new Font("Helvetica", Font.BOLD, 20));
        song4 = new JLabel("<HTML>" + songNameSet.get(3).getSong() + " by. " + songNameSet.get(3).getArtist() +
                "</HTML>");
        song4.setFont(new Font("Helvetica", Font.BOLD, 20));
        song5 = new JLabel("<HTML>" + songNameSet.get(4).getSong() + " by. " + songNameSet.get(4).getArtist() +
                "</HTML>");
        song5.setFont(new Font("Helvetica", Font.BOLD, 20));

        availableArtistImages.addAll(List.of(artistImages)); // for searching purposes

        // song artists pictures
        image1 = new JLabel();
        image1.setBounds(5, 5, 200, 200);
        image1.setHorizontalAlignment(SwingConstants.LEFT);

        image2 = new JLabel();
        image2.setBounds(5, 5, 200, 200);
        image2.setHorizontalAlignment(SwingConstants.CENTER);

        image3 = new JLabel();
        image3.setBounds(5, 5, 200, 200);
        image3.setHorizontalAlignment(SwingConstants.LEFT);

        image4 = new JLabel();
        image4.setBounds(5, 5, 200, 200);
        image4.setHorizontalAlignment(SwingConstants.CENTER);

        image5 = new JLabel();
        image5.setBounds(5, 5, 200, 200);
        image5.setHorizontalAlignment(SwingConstants.LEFT);

        if (availableArtistImages.contains(songNameSet.get(0).getArtist())) {
            scaleImage("program-images/artist-images/" + songNameSet.get(0).getArtist() +
                    ".jpeg", image1);
        } else {
            scaleImage("program-images/artist-images/defaultpic.jpg", image1);
        }

        // song image 2
        if (availableArtistImages.contains(songNameSet.get(1).getArtist())) {
            scaleImage("program-images/artist-images/" + songNameSet.get(1).getArtist() +
                    ".jpeg", image2);
        } else {
            scaleImage("program-images/artist-images/defaultpic.jpg", image2);
        }

        // song image 3
        if (availableArtistImages.contains(songNameSet.get(2).getArtist())) {
            scaleImage("program-images/artist-images/" + songNameSet.get(2).getArtist() +
                    ".jpeg", image3);
        } else {
            scaleImage("program-images/artist-images/defaultpic.jpg", image3);
        }

        // song image 4
        if (availableArtistImages.contains(songNameSet.get(3).getArtist())) {
            scaleImage("program-images/artist-images/" + songNameSet.get(3).getArtist() +
                    ".jpeg", image4);
        } else {
            scaleImage("program-images/artist-images/defaultpic.jpg", image4);
        }

        // song image 5
        if (availableArtistImages.contains(songNameSet.get(4).getArtist())) {
            scaleImage("program-images/artist-images/" + songNameSet.get(4).getArtist() +
                    ".jpeg", image5);
        } else {
            scaleImage("program-images/artist-images/defaultpic.jpg", image5);
        }

        // adding songs and images to panel
        mainPanel.add(image1);
        mainPanel.add(song1);
        mainPanel.add(image2);
        mainPanel.add(song2);
        mainPanel.add(image3);
        mainPanel.add(song3);
        mainPanel.add(image4);
        mainPanel.add(song4);
        mainPanel.add(image5);
        mainPanel.add(song5);

        bottomPanel.add(finishedButton);

    }

    private void scaleImage(String location, JLabel label) {
        ImageIcon icon = new ImageIcon(location);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label.setIcon(scaledIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == finishedButton) {
            Dashboard dashboard = new Dashboard();
            dashboard.DashboardWindow();
            this.setVisible(false);
        }

    }
}

