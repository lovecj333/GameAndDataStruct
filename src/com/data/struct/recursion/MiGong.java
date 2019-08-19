package com.data.struct.recursion;

public class MiGong {

    public static void main(String[] args) {
        //创建一个8*7的二维数组地图
        int[][] map = new int[8][7];

        //1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay2(map, 1, 1);
        System.out.println("--------------------------------------");

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //i,j表示从地图的哪个位置开始出发(1,1)
    //能到 map[6][5] 位置则说明通路找到
    //当map[i][j]为0表示该点没有走过 当为 1 表示墙 2表示通路可以走 3 表示该点已经走过但是走不通
    //在走迷宫时需要确定一个策略(方法) 下->右->上->左 如果该点走不通 再回溯
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){
            return true;
        }
        if(map[i][j] == 0){ //当前这个点还没有走过
            map[i][j] = 2;  //先设置这个点可以走通
            if(setWay(map, i+1, j)){    //向下
                return true;
            }else if(setWay(map, i, j+1)){  //向右
                return true;
            }else if(setWay(map, i-1, j)){  //向上
                return true;
            }else if(setWay(map, i, j-1)){    //向左
                return true;
            }else{  //走不通的点
                map[i][j] = 3;
                return false;
            }
        }
        //map[i][j] != 0 可能为 1(墙不能走) 2(已经走过的点也不能走) 3(走不通的点)
        return false;
    }

    //修改找路的策略改成 上->右->下->左
    public static boolean setWay2(int[][] map, int i, int j){
        if(map[6][5] == 2){
            return true;
        }
        if(map[i][j] == 0){ //当前这个点还没有走过
            map[i][j] = 2;  //先设置这个点可以走通
            if(setWay2(map, i-1, j)){    //向上
                return true;
            }else if(setWay2(map, i, j+1)){  //向右
                return true;
            }else if(setWay2(map, i+1, j)){  //向下
                return true;
            }else if(setWay2(map, i, j-1)){    //向左
                return true;
            }else{  //走不通的点
                map[i][j] = 3;
                return false;
            }
        }
        //map[i][j] != 0 可能为 1(墙不能走) 2(已经走过的点也不能走) 3(走不通的点)
        return false;
    }
}
