package model;

import java.util.ArrayList;

public class State {
    
    private String name;
    private boolean visited;
    private int block;
    private int prevBlock;
    private char[] responses;
    private ArrayList<State> successorStates; //Cambiar a arreglo de dos posiciones
    
    public State(String name, char[] responses){
        this.name = name;
        visited = false;
        block = 0;
        prevBlock = 0;
        this.responses = responses;
        this.successorStates = new ArrayList<>();
    }

    public State(String name){
        this.name = name;
        visited = false;
        this.successorStates = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public boolean getVisited(){
        return visited;
    }

    public char[] getResponses(){
        return responses;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public ArrayList<State> getSuccessorStates(){
        return successorStates;
    }

    public void addSuccessor(State s){
        successorStates.add(s);
    }

    public int getBlock(){
        return block;
    }

    public int getPrevBlock(){
        return prevBlock;
    }

    public void changeBlocks(){
        prevBlock = block;
    }

    public void setBlock(int block){
        this.block = block;
    }

}
