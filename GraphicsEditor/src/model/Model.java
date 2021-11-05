package model;

import java.util.ArrayList;

public class Model {
    public int pointCount;
    public ArrayList<Point>p;

    public Model(){
    this.pointCount = pointCount;
    this.p = new ArrayList<Point>();
    }
    public int getPointCount() {
        return p.size();//возвращает размер листа с точками
    }
    public void addPoint(Point point) {
        p.add(point); // добавляет точку в лист}
//        for(int i = 0; i < 10; i++){
//        System.out.println(p.get(i));
//        }
    }
    public void removePoint(Point point) {
        p.remove(point); // удаляет точку}
    }
    public Point getPoint(int i){
        return this.p.get(i);//вернет элемент по индексу
    }
    public  void  deleteArray()
    {
        p.clear();///полностью очишает массив с точками
    }


    public int serchPoint(int x, int y){
        int index = -1;//ищем точку по координатм
        for (int i = 0; i < this.p.size(); i++){
            if (this.p.get(i).x == x && this.p.get(i).y == y) index = i;
        }
        return index;
    }

}
