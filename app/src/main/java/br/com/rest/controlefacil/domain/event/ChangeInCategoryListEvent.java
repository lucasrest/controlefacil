package br.com.rest.controlefacil.domain.event;

/**
 * Created by PROGRAMAÇÃO on 04/12/2017.
 */

public class ChangeInCategoryListEvent {

    public static int ADD = 1;
    public static int UPDATE = 2;
    public static int REMOVE = 3;
    private int operation;

    public ChangeInCategoryListEvent(int operation){
        this.operation = operation;
    }

    public int getOperation() {
        return operation;
    }
}
