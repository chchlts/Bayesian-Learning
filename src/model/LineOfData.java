/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;

/**
 *
 * @author asus
 */
public class LineOfData {
    private String[] attributes;
    private String classLabel;

    public LineOfData(String[] attributes, String classLabel) {
        this.attributes = attributes;
        this.classLabel = classLabel;
    }

    public String[] getAttributes() {
        return Arrays.copyOf(attributes, attributes.length);
    }

    public String getClassLabel() {
        return classLabel;
    }
}
