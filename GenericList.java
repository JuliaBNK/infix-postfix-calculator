/*
 * Name:     Iuliia Buniak
 *
 * Course:   CS-13, Spring 2019
 *
 * Date:     03/21/19
 *
 * Filename: GenericList.java
 *
 * Purpose:  To create Generic List class to implement MyStack interface 
 */
 
 
 public class GenericList <T> implements MyStack<T>{
    // Use an array to create the list
    private T[] arr;
    private int size;

    /*
    * Constructor for objects of class GSL
    */
    public GenericList(){
        this.newArray();
    }
    
    
    /*
    * newArray - method to create a new array
    * will be used just within this class
    */ 
    private void newArray(){
        arr = (T[])new Object[10];
        size = 0;
    }

    /*
    * size - returns the size of the stack
    * 
    * return - the size of the stack as an integer
    */
    public int size(){
        return size;
    }
    
    /*
    * push - push a value on the stack
    *  
    * param - value to add to the top of the stack
    */
    public void push(T value){
        add(value);
    }

    /*
    * pop - return the value at the top of stack
    * 
    * returns - value at top of stack
    */
    public T pop() throws ArrayIndexOutOfBoundsException {
        if(!isEmpty()){
            T data;
            data = get(size-1);
            size--;
            return data;

        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    /*
    * peek - return value at top of stack but do not remove it.
    * 
    */
    public T peek() throws ArrayIndexOutOfBoundsException{
        if (!isEmpty()) {
            return get(size - 1);
        }
        else {
            throw new ArrayIndexOutOfBoundsException();        
        }
    }

    /*
    * isEmpty - returns true if stack is empty. Otherwise false.
    * 
    */
    public boolean isEmpty(){
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }     

    /*
    * add - add one value to the list in the next available position
    *  
    * param - String to add to the list
    */
    public void add(T value){
        if (size == arr.length) { // Is arr full? Then expand by 20%
            this.expandArray ();
        }
        arr[size] = value;
        size++;
    }

    /*
    * get - return the value at the specified location in the list
    * 
    * param - index into the list for the value to return
    * return - String value
    */
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        // Is index within bounds?  If not, throw an exception
        if (index < 0 || index >= size) { 
            throw new ArrayIndexOutOfBoundsException(); 
        }   
        else {
            return arr[index]; 
        }             
    }

    /*
    * clear - empty the stack
    */
    public void clear(){
        this.newArray();
    }

    /*
    * insert - insert new element at indicated index
    * 
    * param - index to insert new element
    * param - String value of new element
    */
    public void insert(int index, T value) throws ArrayIndexOutOfBoundsException {
        // If the index points to an empty element, add it.
        //if ( index >= size ){
            //add(value);
        //}
        if (index < 0 || index >= size) { 
            throw new ArrayIndexOutOfBoundsException(); 
        }    
        else {
            if (size == arr.length){ // Is arr full? Then expand by 20%
                this.expandArray ();
            }    
            // Open a hole to insert the value
            for (int i = size; i > index; i--)
                arr[i] = arr[i - 1];
            arr[index] = value;
            size++;
        }
    }

    /*
    * toString - return a string value that represents the stack
    *
    * return - String
    */
    public String toString(){
        if (size == 0) {
            return "top, bottom";
        }
        else {
            String returnValue = String.valueOf(arr[0]);
            returnValue = "top";
            for (int i = size-1; i >= 0; i--){
                returnValue = returnValue + ", " + arr[i];
            } 
            returnValue = returnValue + ", bottom";   
            return returnValue;
        }
    }

    /*
    * display - display the list
    */
    public void display(){
        for (int i = 0; i < size; i++){
            System.out.println(i + ": " + arr[i]);
        }    
        if ( arr.length == size){
            System.out.println("List is full.\n");
        }        
        else {
            System.out.println("List has " + (arr.length - size) + " spaces left\n.");
        } 
    }
    
    /*
    * set - to set an element of the list at specified index to the provided value
    *
    * param - int index of element to set a value
    * param - String value of new element
    */
    public void set(int index, T value) throws ArrayIndexOutOfBoundsException {
        // Is index within bounds?  If not, throw an exeption
        if (index < 0 || index >= size) { 
            throw new ArrayIndexOutOfBoundsException();
        }   
        else {
            arr[index] = value; 
        }    
    } 
    
    /*
    * remove - to remove an element at specified index
    * the rest of the elements are shifted down to fill the hole
    *
    * param - int index of element to remove 
    */
    public void remove(int index) throws ArrayIndexOutOfBoundsException{
        // Is index within bounds?  If not, throw an exeption
        if (index < 0 || index >= size) { 
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
        // Move all elements from the index to the end by one position down  
            for (int i = index; i < arr.length - 1; i++){
                arr[i] = arr[i + 1];   
            }
            size--;
        }
    }
    
    /*
    * expandArray  - method to increase the size of an array
    * will be used just within a class
    */
    private void expandArray () {
        T[] arr2;
        arr2 = (T[])new Object[(int)(arr.length * 1.2)];
        // Copy elems from arr to arr2
        for (int i = 0; i < arr.length; i++)
        arr2[i] = arr[i];
        // Have arr point to new array
        arr = arr2;
        // Old array will be Garbage Collected
    } 
    
   /*
   * swap - swap date between the two index values
   *
   * param - index 1
   * param - index 2
   *
   * The data at index1 is saved to index2 and the data at index2
   * is saved to index1.
   */
  public void swap(int index1, int index2) throws ArrayIndexOutOfBoundsException {
      if ((index1 < 0 || index1 >= size)|| (index2 < 0 || index2 >= size)) {
          throw new ArrayIndexOutOfBoundsException();
      }
      T temp1, temp2;
      temp1 = arr[index1];
      temp2 = arr[index2];
      arr[index1] = temp2;
      arr[index2] = temp1;
  }
  
   
} // end of the class