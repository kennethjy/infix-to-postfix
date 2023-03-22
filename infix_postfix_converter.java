import java.util.Scanner;
import java.util.Stack;

public class infix_postfix_converter {

    public static void main(String[] args) {
        Scanner 输入 = new Scanner(System.in);
        System.out.print("Enter an infix expression: ");
        String 方程 = 输入.nextLine();
        System.out.print(转变(方程));
    }
    public static String 转变(String 方程 /* infix equation */){
        Stack<Character> 操作 /* operations */ = new Stack<>();
        StringBuilder 后缀 /* postfix equation */ = new StringBuilder();

        // loop through the equation string
        for (char 子: 方程.toCharArray()){
            // if the character is not an operator (+, -, *, /, ^, (, ))
            if(优先权(子) == -1){
                // append to postfix string
                后缀.append(子);
            }
            // if the character is a right bracket, pop operators from stack and append it to the postfix equation
            // until left bracket is encountered, the pop it without appending it to the postfix equation
            else if (子 == ')') {
                while (操作.peek() != '('){
                    后缀.append(操作.pop());
                }
                操作.pop();
            }
            // if the character is a left bracket, immediately push it on the stack
            else if (子 == '('){
                操作.push('(');
            }
            // if the character is an operator, pop operators and append it to the postfix equation until an operator
            // with lower precedence than the character and then push the character into the stack
            else {
                while (!操作.isEmpty() && 优先权(操作.peek()) >= 优先权(子)){
                    后缀.append(操作.pop());
                }
                操作.push(子);
            }
        }

        // append all remaining operators onto postfix equation
        while (!操作.isEmpty()) {
            后缀.append(操作.pop());
        }

        // return the postfix equation
        return String.valueOf(后缀);

    }

    public static int 优先权/* precedence */(char 子){
        return switch (子){
            case '(', ')' -> 0;
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }
}
