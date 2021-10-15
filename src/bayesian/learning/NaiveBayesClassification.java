/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayesian.learning;

import java.util.ArrayList;
import java.util.Arrays;
import model.Dataset;
import model.LineOfData;

/**
 *
 * @author asus
 */
public class NaiveBayesClassification {
    private Dataset dataset;
    
    public NaiveBayesClassification(Dataset dataset) {
        this.dataset = dataset;
    }
    
    public String askClassification(String[] inputAttributes){
        String[] classLabels = getUniqueClassLabel();
        float[] classLabelsProbability = new float[classLabels.length];
        float[] classifierResults = new float[classLabels.length];
        
        Arrays.fill(classifierResults, 1);
        for(int i = 0; i < classLabels.length; i++){
            classLabelsProbability[i] = calculateLabelProbability(classLabels[i]);
            for(float attributeProbability : calculateAttributesProbability(inputAttributes, classLabels[i]))
                classifierResults[i] *= attributeProbability;
            classifierResults[i] *= classLabelsProbability[i];
        }
        
        return printResult(classLabels, inputAttributes, classifierResults);
    }
    
    private String printResult(String[] classLabels, String[] inputAttributes, float[] classifierResults){
        String result = "Hasil Perhitungan Naive Bayes Classifier : \n";
        float maxProbability = -1;
        int maxIndeks = 0;
        
        for(int i = 0; i < classLabels.length; i++){
            result += "P(";
            for(int j = 0; j < inputAttributes.length; j++)
                if(inputAttributes[j] != null)
                    result += "X" + (j + 1) + "=" + inputAttributes[j] + " ";
            result += "| Y=" + classLabels[i] + ") = " + classifierResults[i] + "\n";
            if(maxProbability < classifierResults[i]){
                maxProbability = classifierResults[i];
                maxIndeks = i;
            }    
        }
        return result +  "Keputusan adalah " 
                + (maxProbability > 0 ? classLabels[maxIndeks]:"tidak dapat ditentukan");
    }
    
    private float calculateLabelProbability(String label){
        float sameLabelAmount = 0;
        int labelAmount = dataset.getDatas().size();
                
        for(int i = 0; i < labelAmount; i++){
            //if checked label equal label
            if(label.equals(dataset.getDatas().get(i).getClassLabel()))
                sameLabelAmount++;
        }
        return sameLabelAmount / labelAmount;
    }
    
    private float[] calculateAttributesProbability(String[] attributes, String label){
        float sameAttributeAndLabelAmount;
        float sameLabelAmount;
        String[] checkedAttributesLine;
        float attributesProbability[] = new float[attributes.length];
        
        for(int i = 0; i < attributes.length; i++){
            if(attributes[i] == null){
                attributesProbability[i] = 1;
                continue;
            }
            sameAttributeAndLabelAmount = 0;
            sameLabelAmount = 0;
            for(int j = 0; j < dataset.getDatas().size(); j++){
                checkedAttributesLine = dataset.getDatas().get(j).getAttributes();
                if(label.equals(dataset.getDatas().get(j).getClassLabel())){
                    sameLabelAmount++;
                    if(attributes[i].equals(checkedAttributesLine[i]))
                        sameAttributeAndLabelAmount++;
                }
            }
            attributesProbability[i] = sameAttributeAndLabelAmount / sameLabelAmount;
        }
        return attributesProbability;
    }

    private String[] getUniqueClassLabel() {
        ArrayList<String> classLabelList = new ArrayList<String>();
        String checkedLabel;
        boolean isUnique;
        Object[] labelObjectList;
        
        for(int i = 0; i < dataset.getDatas().size(); i++){
            isUnique = true;
            checkedLabel = dataset.getDatas().get(i).getClassLabel();
            for(int j = 0; j < classLabelList.size(); j++)
                if(classLabelList.get(j).equals(checkedLabel)){
                    isUnique = false;
                    break;
                }
            if(isUnique)
                classLabelList.add(checkedLabel);
        }
        labelObjectList = classLabelList.toArray();
        return Arrays.copyOf(labelObjectList, labelObjectList.length, String[].class);
    }
}
