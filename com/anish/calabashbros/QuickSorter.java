package com.anish.calabashbros;


import java.util.ArrayList;
// import java.util.Arrays;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {
    // private T[][] a;

    private Calabash[] caline;
    private ArrayList<T> atmp;

    @Override
    public void load(T[][] a) {
        // this.a = a;
        atmp = new ArrayList<>(a.length * a[0].length);
        caline = new Calabash[a.length * a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                atmp.add(a[i][j]);
            }
        }
        atmp.toArray(caline);
    }

    private void swap(int i, int j) {
        Calabash temp;
        temp = caline[i];
        caline[i] = caline[j];
        caline[j] = temp;
        plan += "" + caline[i] + "<->" + caline[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {

        // for (int i = 1; i < caline.length; i++) {
        //     boolean sorted = true;
        //     for (int j = 0; j < caline.length - 1; j++) {
        //         if (caline[j].compareTo(caline[j + 1]) > 0) {
        //             swap(j + 1, j);
        //             sorted = false;
        //         }
        //     }
        //     if (sorted)
        //         break;
        // }
        quicksort(0,caline.length-1);

    }

    private int partition(int begin,int end){
        int pivot=begin;
        int index=begin+1;
        for(int i=index;i<=end;i++){
            if(caline[i].compareTo(caline[pivot])>0){
                swap(i, index);
                index++;
            }
        }
        swap(pivot, index-1);
        return index-1;
    }
    private void quicksort(int begin,int end){
        if(begin<end){

            int pivot=partition(begin,end);
            quicksort(begin,pivot-1);
            quicksort(pivot+1, end);
        }
    }
    
    @Override
    public String getPlan() {
        return this.plan;
    }

}
