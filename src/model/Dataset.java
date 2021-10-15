package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.LineOfData;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author asus
 */
public class Dataset {
    private ArrayList<String> attributeHeaders;
    private String targetHeaders;
    private ArrayList<LineOfData> datas;
    
    public Dataset(String dataPath){
        attributeHeaders = new ArrayList<String>();
        datas = new ArrayList<LineOfData>();
        try {
            readFile(dataPath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dataset.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void readFile(String dataPath)throws FileNotFoundException{
        Scanner data = new Scanner(new File(dataPath));
        
        data = new Scanner(new File(dataPath));
        assignHeader(data);
        assignDatas(data);
    }
    
    private void assignHeader(Scanner data){
        String[] rawData = data.next().split(",");
        String[] attributesData = new String[rawData.length - 1];
        
        for(int i = 0; i < attributesData.length; i++){
            attributesData[i] = rawData[i];
        }
        attributeHeaders.addAll(Arrays.asList(attributesData));
        targetHeaders = rawData[rawData.length - 1];
    }
    
    private void assignDatas(Scanner data){
        String rawData[];
        String[] inputAttributes;
        int i;
        
        while(data.hasNext()){
            rawData = data.next().split(",");
            inputAttributes = new String[rawData.length - 1];
            for(i = 0; i < inputAttributes.length; i++){
                inputAttributes[i] = rawData[i];
            }
            datas.add(new LineOfData(inputAttributes, rawData[i]));
        }
    }

    public ArrayList<String> getAttributeHeaders() {
        return attributeHeaders;
    }

    public String getTargetHeaders() {
        return targetHeaders;
    }

    public ArrayList<LineOfData> getDatas() {
        return datas;
    }
}
