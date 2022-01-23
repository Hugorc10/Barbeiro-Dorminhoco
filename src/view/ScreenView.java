package view;

import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ScreenView {
    
    public static ImageView castleView;
    public static ImageView destroyedCastleView;
    public static ImageView marioView1;
    public static ImageView marioView2;
    public static Button startBtn;
    public static ImageView luigiView1;
    public static ImageView luigiView2;
    public static ImageView luigiView3;
    public static ImageView toadView1;
    public static ImageView toadView2;
    public static ImageView toadView3;
    public static ImageView yoshiView1;
    public static ImageView yoshiView2;
    public static ImageView yoshiView3;
    public static ImageView goombaView1;
    public static ImageView goombaView2;
    public static ImageView goombaView3;
    public static ImageView marioView3;
    public static Slider speedCustomerSld;
    public static Slider timeCustomerSld;
    public static Slider timeBarberSld;
    public static Button pausePlayBtn;
    
    public Parent createContent() {
        Pane canvas = new Pane();
        canvas.setPrefSize(1300, 645);
        
        Image marioLandImg = new Image(getClass().getResourceAsStream("/img/mario-land-background2.png"));
        
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                false, false, true, false);
        
        BackgroundImage backgroundImage = new BackgroundImage(marioLandImg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        
        Background marioLandBackground = new Background(backgroundImage);
        
        canvas.setBackground(marioLandBackground);
        
        Image startImg = new Image(getClass().getResourceAsStream("/img/mario-start.jpg"));
        
        ImageView startView = new ImageView(startImg);
        startView.setCache(true);
        startView.setSmooth(true);
        startView.setPreserveRatio(true);
        startView.setFitWidth(200);
        
        pausePlayBtn = new Button("Pause song");
        pausePlayBtn.setCancelButton(true);
        pausePlayBtn.setFont(Font.font("Caimbra", FontWeight.BOLD, 16));
        pausePlayBtn.setPrefSize(110, 50);
        pausePlayBtn.setLayoutX(325);
        pausePlayBtn.setLayoutY(55);
        
        startBtn = new Button();
        startBtn.setGraphic(startView);
        startBtn.setDefaultButton(true);
        startBtn.setLayoutX(50);
        startBtn.setLayoutY(50);
        
        Text speedCustomerTxt = new Text(500, 60, "Customers' movement speed");
        speedCustomerTxt.setFill(Color.BLACK);
        speedCustomerTxt.setFont(Font.font("Caimbra", FontWeight.SEMI_BOLD, 17));
        
        speedCustomerSld = new Slider(0, 50, 47.5);
        speedCustomerSld.setOrientation(Orientation.HORIZONTAL);
        speedCustomerSld.setPrefWidth(225);
        speedCustomerSld.setShowTickLabels(true);
        speedCustomerSld.setMajorTickUnit(5);
        speedCustomerSld.setBlockIncrement(5);
        speedCustomerSld.setLayoutX(500);
        speedCustomerSld.setLayoutY(70);
        
        Text timeCustomerTxt = new Text(800, 60, "Time to enter a customer");
        timeCustomerTxt.setFill(Color.BLACK);
        timeCustomerTxt.setFont(Font.font("Caimbra", FontWeight.SEMI_BOLD, 17));
        
        timeCustomerSld = new Slider(1, 10, 2);
        timeCustomerSld.setOrientation(Orientation.HORIZONTAL);
        timeCustomerSld.setPrefWidth(225);
        timeCustomerSld.setShowTickLabels(true);
        timeCustomerSld.setMajorTickUnit(1);
        timeCustomerSld.setBlockIncrement(1);
        timeCustomerSld.setLayoutX(800);
        timeCustomerSld.setLayoutY(70);
        
        Text timeBarberTxt = new Text(1070, 60, "Time to change the look");
        timeBarberTxt.setFill(Color.BLACK);
        timeBarberTxt.setFont(Font.font("Caimbra", FontWeight.SEMI_BOLD, 17));
        
        timeBarberSld = new Slider(1, 10, 6);
        timeBarberSld.setOrientation(Orientation.HORIZONTAL);
        timeBarberSld.setPrefWidth(225);
        timeBarberSld.setShowTickLabels(true);
        timeBarberSld.setMajorTickUnit(1);
        timeBarberSld.setBlockIncrement(1);
        timeBarberSld.setLayoutX(1070);
        timeBarberSld.setLayoutY(70);
        
        Image marioImg1 = new Image(getClass().getResourceAsStream("/img/mario.png"));
        
        marioView1 = new ImageView(marioImg1);
        marioView1.setSmooth(true);
        marioView1.setCache(true);
        marioView1.setPreserveRatio(true);
        marioView1.setFitHeight(130);
//        marioView1.setX(1200);
        marioView1.setY(520);
        marioView1.setVisible(false);
        
        Image marioImg2 = new Image(getClass().getResourceAsStream("/img/mario-sitting.png"));
        
        marioView2 = new ImageView(marioImg2);
        marioView2.setSmooth(true);
        marioView2.setCache(true);
        marioView2.setPreserveRatio(true);
        marioView2.setFitHeight(115);
//        marioView2.setX(700);
        marioView2.setY(520);
        marioView2.setVisible(false);
        
        Image marioImg3 = new Image(getClass().getResourceAsStream("/img/mario-without-mustache.png"));
        
        marioView3 = new ImageView(marioImg3);
        marioView3.setSmooth(true);
        marioView3.setCache(true);
        marioView3.setPreserveRatio(true);
        marioView3.setFitHeight(100);
//        marioView3.setX(380);
        marioView3.setY(550);
        marioView3.setVisible(false);
        
        Image luigiImg1 = new Image(getClass().getResourceAsStream("/img/luigi.png"));
        
        luigiView1 = new ImageView(luigiImg1);
        luigiView1.setCache(true);
        luigiView1.setSmooth(true);
        luigiView1.setPreserveRatio(true);
        luigiView1.setFitHeight(110);
//        luigiView1.setX(1200);
        luigiView1.setY(530);
        luigiView1.setVisible(false);
        
        Image luigiImg2 = new Image(getClass().getResourceAsStream("/img/luigi-sitting.png"));
        
        luigiView2 = new ImageView(luigiImg2);
        luigiView2.setCache(true);
        luigiView2.setSmooth(true);
        luigiView2.setPreserveRatio(true);
        luigiView2.setFitHeight(110);
//        luigiView2.setX(780);
        luigiView2.setY(530);
        luigiView2.setVisible(false);
        
        Image luigiImg3 = new Image(getClass().getResourceAsStream("/img/luigi-without-mustache.png"));
        
        luigiView3 = new ImageView(luigiImg3);
        luigiView3.setCache(true);
        luigiView3.setSmooth(true);
        luigiView3.setPreserveRatio(true);
        luigiView3.setFitHeight(125);
//        luigiView3.setX(380);
        luigiView3.setY(525);
        luigiView3.setVisible(false);
        
        Image toadImg1 = new Image(getClass().getResourceAsStream("/img/toad.png"));
        
        toadView1 = new ImageView(toadImg1);
        toadView1.setCache(true);
        toadView1.setSmooth(true);
        toadView1.setPreserveRatio(true);
        toadView1.setFitHeight(100);
//        toadView1.setX(1200);
        toadView1.setY(540);
        toadView1.setVisible(false);
        
        Image toadImg2 = new Image(getClass().getResourceAsStream("/img/toad-sitting.png"));
        
        toadView2 = new ImageView(toadImg2);
        toadView2.setCache(true);
        toadView2.setSmooth(true);
        toadView2.setPreserveRatio(true);
        toadView2.setFitHeight(100);
//        toadView2.setX(830);
        toadView2.setY(540);
        toadView2.setVisible(false);
        
        Image toadImg3 = new Image(getClass().getResourceAsStream("/img/bald-toad.png"));
        
        toadView3 = new ImageView(toadImg3);
        toadView3.setCache(true);
        toadView3.setSmooth(true);
        toadView3.setPreserveRatio(true);
        toadView3.setFitHeight(100);
//        toadView3.setX(380);
        toadView3.setY(540);
        toadView3.setVisible(false);
        
        Image koopaImg1 = new Image(getClass().getResourceAsStream("/img/yoshi.png"));
        
        yoshiView1 = new ImageView(koopaImg1);
        yoshiView1.setCache(true);
        yoshiView1.setSmooth(true);
        yoshiView1.setPreserveRatio(true);
        yoshiView1.setFitHeight(100);
//        koopaView1.setX(1200);
        yoshiView1.setY(540);
        yoshiView1.setVisible(false);
        
        Image koopaImg2 = new Image(getClass().getResourceAsStream("/img/yoshi-sitting.png"));
        
        yoshiView2 = new ImageView(koopaImg2);
        yoshiView2.setCache(true);
        yoshiView2.setSmooth(true);
        yoshiView2.setPreserveRatio(true);
        yoshiView2.setFitHeight(90);
//        koopaView2.setX(890);
        yoshiView2.setY(550);
        yoshiView2.setVisible(false);
        
        Image koopaImg3 = new Image(getClass().getResourceAsStream("/img/yoshi-flying.png"));
        
        yoshiView3 = new ImageView(koopaImg3);
        yoshiView3.setCache(true);
        yoshiView3.setSmooth(true);
        yoshiView3.setPreserveRatio(true);
        yoshiView3.setFitWidth(115);
//        yoshiView3.setX(320);
        yoshiView3.setY(530);
        yoshiView3.setVisible(false);
        
        Image goombaImg1 = new Image(getClass().getResourceAsStream("/img/goomba.png"));
        
        goombaView1 = new ImageView(goombaImg1);
        goombaView1.setCache(true);
        goombaView1.setSmooth(true);
        goombaView1.setPreserveRatio(true);
        goombaView1.setFitHeight(70);
//        goombaView1.setX(1200);
        goombaView1.setY(560);
        goombaView1.setVisible(false);
        
        Image goombaImg2 = new Image(getClass().getResourceAsStream("/img/goomba-sitting.png"));
        
        goombaView2 = new ImageView(goombaImg2);
        goombaView2.setCache(true);
        goombaView2.setSmooth(true);
        goombaView2.setPreserveRatio(true);
        goombaView2.setFitHeight(70);
//        goombaView2.setX(950);
        goombaView2.setY(570);
        goombaView2.setVisible(false);
        
        Image goombaImg3 = new Image(getClass().getResourceAsStream("/img/gold-goomba.png"));
        
        goombaView3 = new ImageView(goombaImg3);
        goombaView3.setCache(true);
        goombaView3.setSmooth(true);
        goombaView3.setPreserveRatio(true);
        goombaView3.setFitHeight(70);
//        goombaView3.setX(320);
        goombaView3.setY(560);
        goombaView3.setVisible(false);
        
        Image castle = new Image(getClass().getResourceAsStream("/img/castle.png"));
        
        castleView = new ImageView(castle);
        castleView.setSmooth(true);
        castleView.setCache(true);
        castleView.setPreserveRatio(true);
        castleView.setFitHeight(250);
        castleView.setX(350);
        castleView.setY(410);
        castleView.setVisible(false);
        
        Image destroyedCastle = new Image(getClass().getResourceAsStream("/img/destroyed-castle.png"));
        
        destroyedCastleView = new ImageView(destroyedCastle);
        destroyedCastleView.setSmooth(true);
        destroyedCastleView.setCache(true);
        destroyedCastleView.setPreserveRatio(true);
        destroyedCastleView.setFitWidth(240);
//        destroyedCastleView.setFitHeight(125);
        destroyedCastleView.setX(350);
        destroyedCastleView.setY(535);
        
        canvas.getChildren().addAll(startBtn, pausePlayBtn, speedCustomerTxt, timeCustomerTxt, timeBarberTxt,
                speedCustomerSld, timeCustomerSld, timeBarberSld,
                castleView, destroyedCastleView,
                marioView1, marioView2, marioView3,
                luigiView1, luigiView2, luigiView3,
                toadView1, toadView2, toadView3,
                yoshiView1, yoshiView2, yoshiView3,
                goombaView1, goombaView2, goombaView3);
        
        return canvas;
    }
}
