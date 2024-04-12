package com.multimedia.reproductor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author EnriqueVazquez
 */
public class ReproductorVideoController implements Initializable {

    @FXML
    private Label mediaDurationLabel;

    @FXML
    private Button mediaPlayBtn;

    @FXML
    private Button mediaStopBtn;

    @FXML
    private Button mediaOpenFileBtn;

    @FXML
    private Slider mediaDurationSlider;

    @FXML
    private Slider mediaVolumeSlider;

    @FXML
    private MediaView mediaMediaView;

    @FXML
    private VBox vBoxMediaControles;



    public ReproductorVideoController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
            File videoFile = new File("C:\\Users\\enrique_vazquez\\Desktop\\video_small.mp4"); // Reemplaza con la ruta de tu video
            defineMediaContent(videoFile);
        } catch (Exception ex) {}

    }

    @FXML
    public void onMediaPlayButtonClick(ActionEvent event) {
        playVideo();
    }

    @FXML
    public void onMediaStopButtonClick(ActionEvent event) { stopVideo();
    }

    @FXML
    public void onMediaSlider(MouseEvent event) {
        if(mediaMediaView != null && mediaMediaView.getMediaPlayer() != null) {
            mediaMediaView.getMediaPlayer().seek(Duration.seconds(mediaDurationSlider.getValue()));
        }
    }

    @FXML
    public void onSelectMedia(ActionEvent event) {
        showFileChooser();
    }

    @FXML
    private void onMediaViewClick(MouseEvent event) {
        System.out.println("onMediaViewClick: " + event.getClickCount());
        if (event.getClickCount() > 1) {
            try {
                /*
                if(!Stage.getWindows().isEmpty() ) {
                    for(Window window : Stage.getWindows()) {
                        if(window instanceof Stage) {
                            ((Stage)window).close();
                        }
                    }
                }
                */

                FXMLLoader fxmlLoader = new FXMLLoader(ReproductorVideoApplication.class.getResource("ReproductorVideoView.fxml"));
                //Scene scene = new Scene(fxmlLoader.load(), 520, 440);
                Scene scene = new Scene(fxmlLoader.load());

                Stage primaryStage = new Stage();
                //primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.setTitle("Reproductor de Video");
                primaryStage.setScene(scene);
                //primaryStage.setMaximized(true);
                primaryStage.setFullScreen(true);
                primaryStage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void playVideo() {
        if(mediaMediaView != null && mediaMediaView.getMediaPlayer() != null) {
            if(mediaPlayBtn.getText().equals("Play")) {
                mediaMediaView.getMediaPlayer().play();
                //mediaPlay.setText("Pause");
            } else {
                mediaMediaView.getMediaPlayer().pause();
                //mediaPlay.setText("Play");
            }
        }
    }

    // Método para pausar el video
    private void stopVideo() {
        if(mediaMediaView != null && mediaMediaView.getMediaPlayer() != null) {
            mediaMediaView.getMediaPlayer().stop();
            //mediaPlay.setText("Play");
            //rewindVideo();
        }
    }

    // Método para retroceder el video
    private void rewindVideo() {
        if(mediaMediaView != null && mediaMediaView.getMediaPlayer() != null) {
            mediaMediaView.getMediaPlayer().seek(Duration.ZERO);
            updateSliderValue();
        }
    }

    private void updateSliderValue() {
        if(mediaMediaView != null && mediaMediaView.getMediaPlayer() != null) {
            Duration totalDuration = mediaMediaView.getMediaPlayer().getTotalDuration();
            Duration currentTime = mediaMediaView.getMediaPlayer().getCurrentTime();
            mediaDurationSlider.setValue(currentTime.toSeconds());
        }

    }


    public void showFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Media");
        File selectedFile = fileChooser.showOpenDialog(null);

        mediaPlayBtn.setText("Play");
        defineMediaContent(selectedFile);

    }

    public void defineMediaContent(File selectedFile) {
        if(selectedFile != null  && mediaMediaView != null){
            String url = selectedFile.toURI().toString();

            Media media = new Media(url);
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            mediaMediaView.setMediaPlayer(mediaPlayer);

            //creating bindings
            DoubleProperty widthProp = mediaMediaView.fitWidthProperty();
            DoubleProperty heightProp = mediaMediaView.fitHeightProperty();

            widthProp.bind(Bindings.selectDouble(mediaMediaView.sceneProperty(), "width"));
            heightProp.bind(Bindings.selectDouble(mediaMediaView.sceneProperty(), "height"));

            mediaVolumeSlider.setValue(mediaPlayer.getVolume()*100);
            mediaVolumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    System.out.println("el volumen es: " + mediaPlayer.getVolume());
                    mediaPlayer.setVolume(mediaVolumeSlider.getValue()/100);
                }
            });

            mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                mediaDurationSlider.setValue(newValue.toSeconds());
                mediaDurationLabel.setText("Duración: " + (int)mediaDurationSlider.getValue() + " / " + (int)media.getDuration().toSeconds());
            }));

            mediaPlayer.setOnEndOfMedia(this::stopVideo);

            mediaPlayer.setOnStopped(() -> {
                //mediaPlayBtn.setText("Play");
                //mediaDurationLabel.setText("Duración: 00 / " + (int)media.getDuration().toSeconds());
                //mediaDurationSlider.setValue(0.0);
                //rewindVideo();
            });

            mediaPlayer.setOnPlaying(() -> {
                mediaPlayBtn.setText("Pause");
            });

            mediaPlayer.setOnPaused(() -> {
                mediaPlayBtn.setText("Play");
            });

            mediaPlayer.setOnReady(() -> {
                Duration totalDuration = media.getDuration();
                mediaDurationSlider.setMax(totalDuration.toSeconds());
                mediaDurationLabel.setText("Duración: 00 / " + (int)media.getDuration().toSeconds());
            });

            //Scene scene = mediaMediaView.getScene();
            //mediaMediaView.fitWidthProperty().bind(scene.widthProperty());
            //mediaMediaView.fitHeightProperty().bind(scene.heightProperty());

            //mediaPlayer.setAutoPlay(true);

        }
    }


}
