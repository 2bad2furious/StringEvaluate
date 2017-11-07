package com.company.operationExpressions;

import com.company.Expression;

public class DivisionExpression extends OperationExpression {
    @Override
    protected int operate(int value1, int value2) {
        return value1 / value2;
    }
}
