package com.company.operationExpressions;

import com.company.Expression;

public class SubstractExpression extends OperationExpression {

    @Override
    protected int operate(int value1, int value2) {
        return value1 - value2;
    }
}
