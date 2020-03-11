package com.android.jungledjumble.Models;

public class Sizes {
    private double [][] mat;

    public Sizes(){
        double[][] multi = new double[][]{
                { 1.689,1.7625,1.9095,1.983,1.837,1.9105,2.0575,2.131},
                { 1.749,1.8225,1.9695,2.043,1.897,1.9705,2.1175,2.191},
                { 1.799,1.8725,2.0195,2.093,1.947,2.0205,2.1675,2.241},
                { 1.839,1.9125,2.0595,2.133,1.987,2.0605,2.2075,2.281},
                { 1.889,1.9625,2.1095,2.183,2.037,2.1105,2.2575,2.331},
                { 1.939,2.0125,2.1595,2.233,2.087,2.1605,2.3075,2.381},
                { 1.989,2.0625,2.2095,2.283,2.137,2.2105,2.3575,2.431},
                { 2.039,2.1125,2.2595,2.333,2.187,2.2605,2.4075,2.481},
                {2.089,2.1625,2.3095,2.383,2.237,2.3105,2.4575,2.531},
                {2.139,2.2125,2.3595,2.433,2.287,2.3605,2.5075,2.581}
        };
        this.mat = multi;
    }
    public double[][] getMat() {
        return mat;
    }

    public void setUsername(double [][] mat) {
        this.mat = mat;
    }

}
