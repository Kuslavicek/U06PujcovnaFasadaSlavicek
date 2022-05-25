/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package data;

/**
 *
 * @author HP
 */
public enum TypProstredku {
    VRTULNIK("Vrtulnik"),
    DOPRAVNILETADLO("Letadlo"),
    STIHACILETOUN("Stihac");
    
    String name;

    private TypProstredku(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
