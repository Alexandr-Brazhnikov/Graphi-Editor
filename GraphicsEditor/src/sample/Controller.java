package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import model.Model;
import model.Point;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public Canvas CanvasD;
    public ColorPicker colorP;
    public Button lineS;
    public String flag;
    public Slider sliders;
    public Button lastick;

    Model model;
    Point point;
    private GraphicsContext gr;
    Image bgImage;
    double bgX, bgY, bgW = 100.0, bgH = 100.0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model=new Model();
        gr = CanvasD.getGraphicsContext2D();
        SliderTol();
    }
    private void SliderTol() {//толщина линии
        sliders.setMin(1);
        sliders.setMax(10);
        sliders.setValue(1);
        colorP.setValue(Color.BLACK);
        flag =lastick.getId();

    }
    public void update(Model model) {
        gr.clearRect(0, 0, 500, 500);//очищаем канву

        for (int i = 0; i < model.getPointCount(); i++) {
            gr.setFill(model.getPoint(i).getColor());
            gr.fillOval(model.getPoint(i).getX(),model.getPoint(i).getY(),model.getPoint(i).getwP() ,model.getPoint(i).gethP());
        }
    }


    public void loading(GraphicsContext gc, File file){
        String str=file.getPath();//переменная для пути к картинке

        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        bgImage = new Image(file.toURI().toString());
        bgX = canvasWidth/2;
        bgY = canvasHeight/2 ;
        gc.drawImage(bgImage, bgX, bgY, bgW, bgH);//set image on Canvas

        PixelReader pixelReader = bgImage.getPixelReader();
        double yr=CanvasD.getHeight()/bgImage.getHeight();
        double xr=CanvasD.getWidth()/bgImage.getWidth();
        //попиксельная отрисовка изображения на канву
        for (int y = 0; y < bgImage.getHeight(); y++) {
            for (int x = 0; x < bgImage.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                Point point =new Point(x,y);
                point.setColor(color);
                point.setSizePoint(xr,yr);
                model.addPoint(point);
            }
        }

    }
    public void print(MouseEvent mouseEvent) {//для непрерывной линии
        //update();
        Point point = new Point((int) mouseEvent.getX(), (int) mouseEvent.getY());
        if (flag == lastick.getId()) {
            point.setColor(colorP.getValue());
            point.setSizePoint(sliders.getValue(), sliders.getValue());
            model.addPoint(point);

        } else {

            point.setSizePoint(sliders.getValue(), sliders.getValue());
            model.removePoint(point);
        }
        update(model);

    }

    public void priiin(MouseEvent mouseEvent) {
        Point point = new Point((int) mouseEvent.getX(), (int) mouseEvent.getY());
        if (flag == lastick.getId()) {
            point.setColor(colorP.getValue());
            point.setSizePoint(sliders.getValue(), sliders.getValue());
            model.addPoint(point);

        } else {

            point.setSizePoint(sliders.getValue(), sliders.getValue());
            model.removePoint(point);
        }
        update(model);
    }

    public Image Loading(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображениe...");
        fileChooser.setInitialFileName("new_image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Изображение", "*.JPG"));
        File loadImageFile = fileChooser.showOpenDialog(CanvasD.getScene().getWindow());
        Image image = new Image(loadImageFile.toURI().toString());
        CanvasD.getGraphicsContext2D(image);
        //return image;

    }

    //


}

