import java.util.*;

class Solution {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        
        // Iterate over the expression
        for (char c : expression.toCharArray()) {
            if (c == ',' || c == '(') {
                continue; // Skip commas and opening parentheses
            } else if (c == 't' || c == 'f' || c == '!' || c == '&' || c == '|') {
                stack.push(c); // Push booleans and operators onto the stack
            } else if (c == ')') {
                // Process when we encounter a closing parenthesis
                List<Character> operands = new ArrayList<>();
                while (stack.peek() == 't' || stack.peek() == 'f') {
                    operands.add(stack.pop()); // Collect the operands
                }
                
                // Get the operator (!, &, |)
                char operator = stack.pop();
                
                // Evaluate the expression inside the parentheses
                char result = 'f';
                if (operator == '!') {
                    result = operands.get(0) == 't' ? 'f' : 't';
                } else if (operator == '&') {
                    result = 't';
                    for (char op : operands) {
                        if (op == 'f') {
                            result = 'f';
                            break;
                        }
                    }
                } else if (operator == '|') {
                    result = 'f';
                    for (char op : operands) {
                        if (op == 't') {
                            result = 't';
                            break;
                        }
                    }
                }
                
                // Push the result back onto the stack
                stack.push(result);
            }
        }
        
        // The final result will be on the stack
        return stack.pop() == 't';
    }
}
