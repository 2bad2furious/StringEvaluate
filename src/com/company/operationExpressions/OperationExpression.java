package com.company.operationExpressions;

import com.company.Expression;

public abstract class OperationExpression implements Expression {
    private Expression expression1;
    private Expression expression2;

    public OperationExpression setExpression1(Expression expression1) {
        if (this.expression1 != null) throw new IllegalStateException("Expresssion already set");
        this.expression1 = expression1;
        return this;
    }

    public OperationExpression setExpression2(Expression expression2) {
        if (this.expression2 != null) throw new IllegalStateException("Expresssion already set");
        this.expression2 = expression2;
        return this;
    }

    @Override
    public final int count() {
        return operate(expression1.count(), expression2.count());
    }


    /**
     * does the operation specific for the current class
     *
     * @param value1
     * @param value2
     * @return result of the expression
     */
    protected abstract int operate(int value1, int value2);
}
