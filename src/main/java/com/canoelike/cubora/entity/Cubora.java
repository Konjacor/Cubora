package com.canoelike.cubora.entity;

import com.canoelike.cubora.entity.facelets.Colors;
import com.canoelike.cubora.entity.pieces.*;

/**
 * @author Konjacer
 * @create 2025-02-21 19:51
 */
public class Cubora {

    // 1. 使用 volatile 关键字确保 instance 在多线程环境下的可见性
    private static volatile Cubora instance = null;

    // 2. 私有构造函数，防止外部实例化
    private Cubora(){
        initialize();//初始化逻辑
    }

    // 3. 提供全局访问点，使用双重检查锁定
    public static Cubora getInstance() {
        if (instance == null) { // 第一次检查
            synchronized (Cubora.class) {
                if (instance == null) { // 第二次检查
                    instance = new Cubora(); // 创建实例
                }
            }
        }
        return instance;//返回实例
    }

    private Piece[][][] wholeCube;//魔方,三维数组中每个元素是魔方的一个块，建立的直角坐标系中，原点位于左下角，x轴正方向朝右，y轴正方向朝上，z轴正方向从屏幕内朝向屏幕外（标准右手系）

    private CenterPiece faceNow;//当前面对的中心块，中心块一共有六种，分别是魔方六个面上最中间的块

    private void initialize(){
        wholeCube = new Piece[3][3][3];
        initCornerPieces(wholeCube);//初始化角块
        initEdgePieces(wholeCube);//初始化边块
        initCenterPieces(wholeCube);//初始化中心块
        initCorePieces(wholeCube);//初始化核心块
        faceNow = (CenterPiece) wholeCube[1][1][2];
    }

    private void initCornerPieces(Piece[][][] wholeCube){//初始化8个角块
        //z轴相同的为一组进行初始化，默认顺序是从下到上，从左到右这个顺序进行初始化
        wholeCube[0][0][0] = new CornerPiece(0, 0, 0).setFacelet('L',Colors.RED).setFacelet('D',Colors.YELLOW).setFacelet('B',Colors.GREEN);
        wholeCube[0][2][0] = new CornerPiece(0, 2, 0).setFacelet('L',Colors.RED).setFacelet('U',Colors.WHITE).setFacelet('B',Colors.GREEN);
        wholeCube[2][0][0] = new CornerPiece(2, 0, 0).setFacelet('R',Colors.ORANGE).setFacelet('D',Colors.YELLOW).setFacelet('B',Colors.GREEN);
        wholeCube[2][2][0] = new CornerPiece(2, 2, 0).setFacelet('R',Colors.ORANGE).setFacelet('U',Colors.WHITE).setFacelet('B',Colors.GREEN);

        wholeCube[0][0][2] = new CornerPiece(0, 0, 2).setFacelet('L',Colors.RED).setFacelet('D',Colors.YELLOW).setFacelet('F',Colors.BLUE);
        wholeCube[0][2][2] = new CornerPiece(0, 2, 2).setFacelet('L',Colors.RED).setFacelet('U',Colors.WHITE).setFacelet('F',Colors.BLUE);
        wholeCube[2][0][2] = new CornerPiece(2, 0, 2).setFacelet('R',Colors.ORANGE).setFacelet('D',Colors.YELLOW).setFacelet('F',Colors.BLUE);
        wholeCube[2][2][2] = new CornerPiece(2, 2, 2).setFacelet('R',Colors.ORANGE).setFacelet('U',Colors.WHITE).setFacelet('F',Colors.BLUE);
    }

    private void initEdgePieces(Piece[][][] wholeCube){//初始化12个边块
        //z轴相同的为一组进行初始化，默认顺序是从下到上，从左到右这个顺序进行初始化
        wholeCube[0][1][0] = new EdgePiece(0, 1, 0);
        wholeCube[1][0][0] = new EdgePiece(1, 0, 0);
        wholeCube[1][2][0] = new EdgePiece(1, 2, 0);
        wholeCube[2][1][0] = new EdgePiece(2, 1, 0);

        wholeCube[0][0][1] = new EdgePiece(0, 0, 1);
        wholeCube[0][2][1] = new EdgePiece(0, 2, 1);
        wholeCube[2][0][1] = new EdgePiece(2, 0, 1);
        wholeCube[2][2][1] = new EdgePiece(2, 2, 1);

        wholeCube[0][1][2] = new EdgePiece(0, 1, 2);
        wholeCube[1][0][2] = new EdgePiece(1, 0, 2);
        wholeCube[1][2][2] = new EdgePiece(1, 2, 2);
        wholeCube[2][1][2] = new EdgePiece(2, 1, 2);
    }

    private void initCenterPieces(Piece[][][] wholeCube){//初始化6个中心块
        //z轴相同的为一组进行初始化，默认顺序是从下到上，从左到右这个顺序进行初始化
        wholeCube[1][1][0] = new CenterPiece(1, 1, 0);

        wholeCube[0][1][1] = new CenterPiece(0, 1, 1);
        wholeCube[1][0][1] = new CenterPiece(1, 0, 1);
        wholeCube[1][2][1] = new CenterPiece(1, 2, 1);
        wholeCube[2][1][1] = new CenterPiece(2, 1, 1);

        wholeCube[1][1][2] = new CenterPiece(1, 1, 2);
    }

    private void initCorePieces(Piece[][][] wholeCube){//初始化1个核心块
        wholeCube[1][1][1] = new CorePiece(1, 1, 1);
    }

    public void rotate(char type,int angle){//参数一是旋转的类型（F、B、U、D、L、R、M、E、S、f、b、u、d、l、r、x、y、z），参数二是旋转的度数（1-顺时针90度，-1-逆时针90度，2-180度），两个参数排列组合共有54种旋转方式
        switch (type){
            case 'F':
            case 'B':
            case 'U':
            case 'D':
            case 'L':
            case 'R':
            case 'M':
            case 'E':
            case 'S':
            case 'f':
            case 'b':
            case 'u':
            case 'd':
            case 'l':
            case 'r':
            case 'x':
            case 'y':
            case 'z':
        }
    }

    private void rotateF(int angle){//负责F类型的旋转
        switch (angle){
            case 1:
            case -1:
            case 2:
        }
    }


}
