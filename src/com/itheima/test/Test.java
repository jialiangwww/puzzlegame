package com.itheima.test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] indexOfPictureOne = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        // 打乱一维数组顺序
        Random r = new Random();
        for (int i = 0; i < 16; i++) {
            int iRandom = r.nextInt(indexOfPictureOne.length);

            int temp = indexOfPictureOne[i];
            indexOfPictureOne[i] = indexOfPictureOne[iRandom];
            indexOfPictureOne[iRandom] = temp;
        }
        
        // 生成二维数组
        int[][] indexOfPictureTwo = new int[4][4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                indexOfPictureTwo[i][j] = indexOfPictureOne[index++];
            }
        }
    }
}
