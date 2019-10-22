/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.airplane.logic;

import com.api.airplane.inisialis.InisalisSeat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dickyadriansyah
 */
public class LogicAirplane {
    
    public List<String[][]> getDataWMA(List<Integer> list1, List<Integer> list2){
        
        List<String[][]> addddata = new ArrayList<>();
        List<String[][]> addddata2 = new ArrayList<>();
        List<String[][]> addddata3 = new ArrayList<>();

        int index = 0;
        for (int data : list1) {

            int data2 = list2.get(index);

            String[] array1d = new String[data2];
            String[][] array2d = new String[data][data2];

            for (int u = 0; u < array2d.length; u++) {
                for (int k = 0; k < array1d.length; k++) {
                    array2d[u][k] = InisalisSeat.MIDDLE_SEAT;
                }
            }

            addddata.add(array2d);
            index += 1;
        }

        String[][] row;
        for (int i = 0; i < addddata.size(); i++) {
            row = addddata.get(i);
            for (int j = 0; j < row.length; j++) {
                for (int k = 0; k < row[j].length; k++) {
                    row[j][0] = InisalisSeat.AISLE_SEAT;
                    int o = row[j].length - 1;
                    row[j][o] = InisalisSeat.AISLE_SEAT;
                }
            }
            addddata2.add(row);
        }

        String[][] row3;
        for (int i = 0; i < addddata2.size(); i++) {
            row3 = addddata2.get(i);

            if (i == 0) {
                for (int j = 0; j < row3[0].length; j++) {
                    if (j < 2) {
                        row3[j][0] = InisalisSeat.WINDOW_SEAT;
                    }
                }
            }
            if (i == 3) {

                for (int j = 0; j < row3.length; j++) {
                    for (int k = 0; k < row3[j].length; k++) {
                        int o = row3[j].length - 1;
                        row3[j][o] = InisalisSeat.WINDOW_SEAT;
                    }
                }

            }
            addddata3.add(row3);
        }

        return addddata3;
    }
    
    public int getReplaceNumber(String alias_seat, int no, List<String[][]> seatreplace, int colSize, int rowSize, int quantity_passanger){
        int next_number = 0;
        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                
                try {
                    if (seatreplace.get(j) == null || seatreplace.get(j)[i] == null) {
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
                
                for (int k = 0; k < seatreplace.get(j)[i].length; k++) {
                    if (seatreplace.get(j) != null && seatreplace.get(j)[i] != null && seatreplace.get(j)[i][k] == alias_seat) {
                        
                        if(no > quantity_passanger){
                            seatreplace.get(j)[i][k] = "XX";
                        }
                        else{
                            seatreplace.get(j)[i][k] = String.valueOf(no);
                            no++;
                        }
                    }
                }
            }

        }
        
        next_number = no;
        
        return next_number;
    }
    
}
