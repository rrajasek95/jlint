package com.rrajasek.jlint.interpreter;

public class InterpreterVisitorImpl extends InterpreterBaseVisitor<Double> {
    @Override
    public Double visitOperation(InterpreterParser.OperationContext ctx) {
        if (ctx.operator == null) {
            throw new UnsupportedOperationException("Operator is required to perform this operation");
        }

        String operator = ctx.operator.getText();
        double left = Double.parseDouble( ctx.left.getText() );
        double right = Double.parseDouble( ctx.right.getText() );

        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            default:
                throw new UnsupportedOperationException("How did you get here!?");
        }
    }
}
