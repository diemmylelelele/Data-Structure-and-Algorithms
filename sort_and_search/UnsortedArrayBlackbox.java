//package com.gradescope.cs201;

public class UnsortedArrayBlackbox {
    private int[] internal_array;
    private int comparison_num;
    
    public UnsortedArrayBlackbox (int[] arr) {
        this.comparison_num = 0;
        this.internal_array = arr;
    }

    public int compare (int i, int j) {
        comparison_num++;
        return (internal_array[i] < internal_array[j])? 1:((internal_array[i] > internal_array[j])? -1:0);
    }

    public int get_length() {
        return internal_array.length;
    }

    public int get_comparison_num() {
        return comparison_num;
    }        
}