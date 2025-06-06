package com.canoelike.cubora.entity.pieces;

import com.canoelike.cubora.entity.facelets.Colors;

import java.util.HashMap;

/**
 * @author Konjacer
 * @create 2025-02-21 20:24
 */
abstract public class Piece {
    private int x,y,z;//当前块的坐标
    private HashMap<Character, Colors> facelets;//每个小块的小面,key是小面的方向，value是小面的类型
    public Piece(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
        facelets = new HashMap<>();
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getZ(){
        return this.z;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setZ(int z){
        this.z = z;
    }
    public Colors getFacelet(char c){
        return this.facelets.getOrDefault(c,null);
    }
    public Piece setFacelet(char c, Colors facelet){
        this.facelets.put(c, facelet);
        return this;//方便初始化时链式编程
    }
    public void deleteFacelet(char c){
        this.facelets.remove(c);
    }
}
