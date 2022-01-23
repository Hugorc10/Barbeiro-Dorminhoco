/**
 * Created by Hugo Teixeira Mafra <hugorc10@hotmail.com> on 01/10/2018. Last modification on 07/10/2018.
 * <p>
 * Enrollment number: 201611540
 * <p>
 * The sleeping barber problem is a classic inter-process communication and synchronization problem
 * between multiple operating system processes. The problem is analogous to that of keeping a barber
 * working when there are customers, resting when there are none, and doing so in an orderly manner.
 * <p>
 * Sleeping barber problem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Sleeping barber problem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * To see the GNU General Public License
 * please visit this site: <http://www.gnu.org/licenses/>.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Barber;
import model.Customer;
import view.ScreenView;

import java.net.URL;

public class Principal extends Application {
    
    // caminho do audio
    private static final String MARIOPATH = "/audios/super-mario-theme.mp3";
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreenView sv = new ScreenView();
        
        Scene scene = new Scene(sv.createContent());
        
        // classes responsaveis por manipular o audio
        final URL marioThemeResource = getClass().getResource(MARIOPATH);
        final Media media = new Media(marioThemeResource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        
        ScreenView.startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScreenView.startBtn.setDisable(true);
                // comeca a musica
                mediaPlayer.play();
                
                // Inicia o barbeiro
                Barber barber = new Barber();
                barber.setDaemon(true);
                barber.start();
                
                // inicia o cliente
                Customer customer = new Customer();
                customer.setDaemon(true);
                customer.start();
            }
        });
        
        ScreenView.pausePlayBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ScreenView.pausePlayBtn.getText() == "Pause song") {
                    ScreenView.pausePlayBtn.setText("Play song");
                    mediaPlayer.pause();
                } else {
                    ScreenView.pausePlayBtn.setText("Pause song");
                    mediaPlayer.play();
                }
            }
        });
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
