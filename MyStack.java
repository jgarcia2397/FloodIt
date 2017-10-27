import java.util.Arrays;

public class MyStack<E> implements Stack<E> {
	private E[] stackArray;
    private int top;

    @SuppressWarnings("unchecked")
    public MyStack(int size) {
        stackArray = (E[]) new Object[size];
        top = 0;
    }

    public void push(E info) {
        stackArray[top] = info;
        top++;
    }

    public E pop() {
        top--;
        E result = stackArray[top];
        stackArray[top] = null; 
        return result;
    }

    public E peek() {
        return stackArray[top-1];
    }

    public boolean isEmpty() {
        return top == 0;
    }
}