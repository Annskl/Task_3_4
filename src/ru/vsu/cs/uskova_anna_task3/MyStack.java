package ru.vsu.cs.uskova_anna_task3;

public class MyStack<E> {
    private java.util.LinkedList<E> stack = new java.util.LinkedList<>();

    public void push(E element){
        stack.push(element);
    }

    public E pop(){
        return stack.pop();
    }

    public E peek(){
        return stack.peek();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
