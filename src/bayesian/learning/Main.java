/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayesian.learning;

import java.util.Arrays;
import model.Dataset;
import java.util.jar.Attributes;

/**
 *
 * @author asus
 */
public class Main {
    public static void main(String args[]){
        Dataset dataBuah = new Dataset("resources/DataBuah.csv");
        Dataset dataHipertensi = new Dataset("resources/DataPenyakitHipertensi.csv");
        Dataset dataDiabetes = new Dataset("resources/diabetes.csv");
        String[] inputBuah = {"sedang", "sedang"};
        String[] inputHipertensi = {"tua", "sangat-gemuk"};
        String[] inputDiabetes = {"paruh-baya", "rendah"};
        
        System.out.println("Input buah : " + Arrays.toString(inputBuah));
        System.out.println(new NaiveBayesClassification(dataBuah).askClassification(inputBuah));
        System.out.println("\nInput Hipertensi : " + Arrays.toString(inputHipertensi));
        System.out.println(new NaiveBayesClassification(dataHipertensi).askClassification(inputHipertensi));
        System.out.println("\nInput Diabetes : " + Arrays.toString(inputDiabetes));
        System.out.println(new NaiveBayesClassification(dataDiabetes).askClassification(inputDiabetes));
    }
}
