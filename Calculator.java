/*
 * Calculator - take an infix string, change to postfix notation and evaluate.
 * Iuliia Buniak
 * 03/26/2019
 */
 
public class Calculator
{
  // Take an infix arithmetic expression and return a postfix arithmetic expression.
  public String infixToPostfix(String infixNotation)
  { 
    String postfix = "";
    GenericList<String> stack = new GenericList<String>();
    String token;
    Parse infix = new Parse(infixNotation); 
    
	// 1. Read a token from the infix expression.
    while (true) {
      token = infix.getNextToken(); 
      if (token.equals("")) { // end of infix string
        break;
      }
      
      // Debugging code 
      //System.out.println("postfix: " + postfix);
      //System.out.println("stack: " + stack); 
      //System.out.println("token:" + token);  
      
	  // 2. If the token is a number, then add it to the postfix expression.
      if (!infix.isOperator(token)) {
        postfix = postfix + token + " ";
        continue;
      }
      
	  // 3. If the stack is empty or contains a left parenthesis on top,
	  //    push the token onto the stack
      else  if (stack.size() == 0 || stack.peek().equals("(")) {
        stack.push(token);
        continue;
      }

	  // 4. If the token is a left parenthesis, push it on the stack.
      else if (token.equals("(")) {
        stack.push(token);
        continue;
      } 

	  // 5. If the token is a right parenthesis, pop the stack and add value
	  //    to the postfix expression. Repeat until you see a left parenthesis.
	  //    Discard the left and right parenthesis.
      else if (token.equals(")")) {
        while (stack.size() !=0 && !stack.peek().equals("(")) {
          postfix = postfix + stack.pop() + " "; 
        }
        if (stack.size() != 0){
          stack.pop();
        }   
        continue;
      }

	  // 6. If the token is ^ (which is right associative) and
	  //    has equal precedence as top of stack
	  //    ( the only operation of equal precedence is ^ ), push it onto the stack.
	  //    If the token has higher precedence as top of stack,
	  //    push it on the stack.
    
      else if (token.equals("^") && stack.peek().equals("^")){
        stack.push(token);
        continue; 
      }
        
      else if(!infix.lessThanEqualTo (token, stack.peek())){
        stack.push(token);
        continue;
      }

	  // 7. If the token has precedence equal to or less than the top of stack,
	  //    pop the stack and add value to the postfix expression.
	  //    Then test the token against the new top of stack and repeat.
	  //    Push the token onto the stack.
      else if (stack.size() != 0 && infix.lessThanEqualTo(token, stack.peek())){
        do {
          postfix = postfix + stack.pop() + " ";  
        } while (stack.size() != 0 && infix.lessThanEqualTo(token, stack.peek()));           
        stack.push(token);
        continue;  
      }    
       
    } // end while for infix notation
   
    // 8. At the end of the expression,
	//    pop all values from the stack and add to the output queue.
    while (stack.size() != 0) {
        postfix = postfix + stack.pop() + " ";
    }
    return postfix;  
  }

  public int evaluatePostfix(String postfixNotation)
  {
	// 1. Push operands onto the stack.
    GenericList<Integer> stack = new GenericList<Integer>();
    Parse p = new Parse(postfixNotation);
    
    for (int i=0; i<p.size(); i++) {
      String token = p.getNextToken();
      
      if (!p.isOperator(token)) {
        stack.push(Integer.parseInt(token));
      }  

    // 2. When you see an operator, pop the first operand off the stack
	//    and place to the right of the operator. Pop the next operand
	//    off the stack and place to the left of the operator.
      else{
        int valRight = stack.pop();
        int valLeft = stack.pop(); 

	// 3. Perform the operation and push the result onto the stack.
        char c = token.charAt(0);
        
        switch (c) {
          case '+':
          stack.push(valLeft+valRight);
          break;
          
          case '-':
          stack.push(valLeft-valRight);
          break;
          
          case '/':
          stack.push(valLeft/valRight);
          break;
          
          case '*':
          stack.push(valLeft*valRight);
          break;
          
          case '^':
          stack.push((int)Math.pow (valLeft, valRight));
          break;
        }
      }

	// 4. Repeat from step 1 until expression is completed.

	// 5. The value at the top of the stack is the result.
    }
    return stack.pop();
  }

  /**public static void main(String[] args)
  {
    String infixNotation;
    String postfixNotation;
    int calculatedValue;

    // Take the first argument from the command line as a string with
    // infix notation.
    if (args.length == 0)
    {
      System.out.println("You need to enter an infix notation string");
      System.exit(1);
    }

    infixNotation = args[0];
    System.out.println("You entered: " + infixNotation);
    postfixNotation = new Calculator().infixToPostfix(infixNotation);
    System.out.println("Postfix notation: " + postfixNotation);
    calculatedValue = new Calculator().evaluatePostfix(postfixNotation);
    System.out.println("Result of evaluation: " + calculatedValue);
  }*/
}
