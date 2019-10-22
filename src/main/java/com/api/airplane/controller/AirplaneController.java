/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.airplane.controller;

import com.api.airplane.inisialis.InisalisSeat;
import com.api.airplane.logic.LogicAirplane;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dickyadriansyah
 */
@RestController
@RequestMapping(value = "/api/airmiliary")
public class AirplaneController {
    
    LogicAirplane logicAirplane;

    public AirplaneController() {
        logicAirplane=new LogicAirplane();
    }
    
    
    
    @GetMapping
    public ResponseEntity<String> getSeatAirplane(){
	
        String code = "";
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        int colSize = 4;
        int rowSize = 4;
        int MAX = 30; //quantity airplane passanger

        //position seat in airplane
        int mat[][] = {
            {2, 3},
            {3, 4},
            {3, 2},
            {4, 3}
        };
        
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {

                if (code == "") {
                    list1.add(mat[j][i]);
                }

                if (code == "Oke") {
                    list2.add(mat[j][i]);
                }
            }
            code = "Oke";
        }
        
        List<String[][]> getDataWAS = logicAirplane.getDataWMA(list1, list2);
        
        
        int counter = 1;
        
        int counter2 = logicAirplane.getReplaceNumber(InisalisSeat.AISLE_SEAT, counter, getDataWAS, colSize, rowSize, MAX);
        
        int counter3 = logicAirplane.getReplaceNumber(InisalisSeat.WINDOW_SEAT, counter2 , getDataWAS , colSize, rowSize, MAX);
        
        
        logicAirplane.getReplaceNumber(InisalisSeat.MIDDLE_SEAT, counter3, getDataWAS, colSize, rowSize, MAX);
        
        String stringJ = "";
        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                try{
                    if (getDataWAS.get(j) == null || getDataWAS.get(j)[i] == null) {

                        stringJ += "- ";
                        continue;
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    stringJ += "- ";
                    continue;
                }
                
                
                for (int k = 0; k < getDataWAS.get(j)[i].length; k++) {
                    stringJ += (getDataWAS.get(j)[i][k] + " ");
                }
                stringJ += ",";
            }
            stringJ += "\n";
        }
        
        return new ResponseEntity<>(stringJ, HttpStatus.OK);
    }
    
}
