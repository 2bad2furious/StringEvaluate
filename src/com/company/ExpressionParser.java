package com.company;

import com.company.operationExpressions.*;
import com.company.stringUtils.StringUtils;

import java.util.regex.Pattern;

public class ExpressionParser {

    public Expression getExpression(String s) throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        this.checkExpression(s);
        return process(s);
    }

    private Expression process(String expression) throws IllegalAccessException, InstantiationException {
        //case ()
        //case a*b+c*d
        expression = unwrap(expression);
        int operatorPosition = getOperatorPosition(expression);
        if (operatorPosition == -1) {
            int a = 5;
            return new NumberExpression(Integer.parseInt(expression));
            //throw new IllegalArgumentException();
        }
        OperationExpression e = resolveOperator(expression.charAt(operatorPosition));
        return e.setExpression1(process(expression.substring(0, operatorPosition))).setExpression2(process(expression.substring(operatorPosition + 1)));
    }

    private int getOperatorPosition(String expression) {
        int position = getCharNotInsideBrackets(expression, '+');
        if (position == -1) {
            position = getCharNotInsideBrackets(expression, '-');
            if (position == -1) {
                position = getCharNotInsideBrackets(expression, '*');
                if (position == -1)
                    position = getCharNotInsideBrackets(expression, '/');
            }
        }
        return position;
    }

    private String unwrap(String expression) {
        if (getOperatorPosition(expression) != -1) return expression;
        String result = StringUtils.unwrap(expression, '(', ')');
        return result.equals(expression) ? result : unwrap(result);
    }

    private int getCharNotInsideBrackets(String expression, char c) {
        return StringUtils.getIndexOfCharNotInsidePairOfTwoChars(expression, c, '(', ')');
    }

    private void checkExpression(String expression) {

        int countLeftBrackets = StringUtils.countOccurance('(', expression);
        int countRightBrackets = StringUtils.countOccurance(')', expression);
        if (countLeftBrackets != countRightBrackets)
            throw new IllegalArgumentException("The number of left and right brackets isn't te same.");

        if (expression.matches(Pattern.quote("[^0-9*/+-()]")))
            throw new IllegalArgumentException("Invalid characters found in your expression");
    }

    private Integer getInteger(char curChar) {
        try {
            return Integer.parseInt(curChar + "");
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private OperationExpression resolveOperator(char operator) throws IllegalAccessException, InstantiationException {
        Class<? extends OperationExpression> className = null;
        switch (operator) {
            case '+':
                className = AddExpression.class;
                break;
            case '-':
                className = SubstractExpression.class;
                break;
            case '*':
                className = MultificationExpression.class;
                break;
            case '/':
                className = DivisionExpression.class;
                break;
            default:
                throw new IllegalArgumentException("Operator not anything we can resolve xD.");
        }
        return className.newInstance();
    }
}
