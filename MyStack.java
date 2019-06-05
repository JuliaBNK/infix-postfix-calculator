/**
 * MyStack interface
 * 
 * author Tim McGowen
 * version version 1.0
 */
public interface MyStack<T>
{
  /*
   * size - returns the size of the stack
   * 
   * return - the size of the stack as an integer
   */
  public int size();

  /*
   * push - push a value on the stack
   * 
   * param - value to add to the top of stack
   */
  public void push(T value);

  /*
   * pop - return the value at the top of stack
   * 
   * returns - value at top of stack
   */
  public T pop();

  /*
   * clear - empty the stack
   */
  public void clear();

  /*
   * toString - return a string value that represents the stack
   *
   * return - String
   */
  public String toString();

  /*
   * peek - return value at top of stack but do not remove it.
   * 
   */
  public T peek();

  /*
   * isEmpty - returns true if stack is empty. Otherwise false.
   * 
   */
  public boolean isEmpty();
}
