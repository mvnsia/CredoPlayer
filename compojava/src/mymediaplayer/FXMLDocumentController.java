/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymediaplayer;
import javafx.application.Application;
import javafx.scene.media.*;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import java.nio.file.*;
/**

 *
 * @author gopan
 */
public class FXMLDocumentController implements Initializable {
    Stage primaryStage;
    private MediaPlayer mediaPlayer;
    @FXML
    //private Label label;
    private String filePath;
    private Duration totalTime;
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Slider slider;
    
    @FXML
    private Slider seekSlider;  
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file (*.mp4)","*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        
        if(filePath != null){
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            slider.setValue(mediaPlayer.getVolume()*100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue()/100);
                    
                }
            });
          
            mediaPlayer.setOnReady(() -> {

	            // Set the total duration
	            totalTime = mediaPlayer.getMedia().getDuration();

	        });
            
            
            seekSlider.valueProperty().addListener(new InvalidationListener() {
    public void invalidated(Observable ov) {
       if (seekSlider.isValueChanging()) {
       // multiply duration by percentage calculated by slider position
          mediaPlayer.seek(totalTime.multiply(seekSlider.getValue() / 100.0));
       }
    }
});
            
             mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
	            @Override

	            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                            
//	                seekSlider.setValue(newValue.divide(totalTime.toMinutes()).toMinutes()* 100.0);
                        seekSlider.setValue(newValue.toSeconds());
                    }
             });
             
             
             
             
//             ChangeListener<Duration> timeListener =  new ChangeListener<Duration>() {
//    @Override
//    public void changed(
//            ObservableValue<? extends Duration> observableValue,
//            Duration duration,
//            Duration current) {
//        seekSlider
//                .setValue(current
//                        .toSeconds());
//    }
//};
//
//seekSlider.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
//
//                    }
//
//                });
//            
//            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
//                @Override
//                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
//                    seekSlider.setValue(newValue.divide(totalTime.toMillis()).toMillis() * 100.0););
//                    
//                }
//            });
  
//            seekSlider.setOnMouseDragReleased(new EventHandler<MouseEvent>(){
//                public void handle(MouseEvent event)
//                {
//                     if (! slider.isValueChanging())
//                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
//                }
//                
//            });
//


            seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                    
                }
            });
    
            
            mediaPlayer.play();
        }
        
        
    }
    @FXML
    public void Cplaylist(ActionEvent event){
    	
    
    	JFrame f = new JFrame();
    	JTextField t1;
    	t1= new JTextField();
    	t1.setBounds(50,100,200,30);
    
    	
        JButton b1;
    	b1 = new JButton("enter ");
        
    	b1.setBounds(50,150,200,30);
    
    

    	
    	f.add(t1);
    	f.add(b1);
    	f.setSize(400,400);
    	f.setLayout(null);
    	f.setVisible(true);
    
    b1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String pressed = t1.getText();
    		System.out.print(pressed);
    	}

		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			// TODO Auto-generated method stub
			String pressed = t1.getText();
			String path="C:/Users/munna/Desktop/";
			path=path+pressed;
    		File dir = new File(path);
    		boolean iscreated= dir.mkdir();
    		if(iscreated)
    		{
    			t1.setText("playlist created");
    		}
    		else
    		{
    			t1.setText("problem in creating playlist");
    		}
		}
    });
   
    	
    }
    
    public void actionPerformed(ActionEvent e) {

    	
        System.out.print("name");
    }
    
    @FXML
    private void loopVideo(ActionEvent event){
    	
    	mediaPlayer.isAutoPlay();
    	
        
    }
    
    @FXML
    private void Aplaylist(ActionEvent event){
    	
    
    	String vat=filePath;
    	Path sourceFile = Paths.get("file.mp4");
    	
    	System.out.println(sourceFile);
    
    	Path targetFile = Paths.get("C:/Users/munna/Desktop/sonew");
    	try {
    		Files.copy(sourceFile,targetFile,StandardCopyOption.REPLACE_EXISTING);
    	}catch (IOException ex)
    	{
    		System.err.format("I/O error when copying file");
    	}
        
    }
    
    @FXML
    private void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }
    
    @FXML
    private void playVideo(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
        
    }
    
    @FXML
    private void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }
    
    @FXML
    private void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);
      
    }
    
    @FXML
    private void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2.0);
        
    }
    
    @FXML
    private void slowVideo(ActionEvent event){
        mediaPlayer.setRate(0.75);
        
    }
    
    @FXML
    private void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);
        
    }
    
    @FXML
    private void exitVideo(ActionEvent event){
        System.exit(0);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
       
    
}
