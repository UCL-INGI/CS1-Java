package student;

public interface StackIF
{
	public void push (State c);
	
	public State pop() throws EmptyStackException;
	
	public int size();
	
	public boolean isEmpty();
	
	public State peek (int n);
}