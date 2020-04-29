// Generated from /home/rishi/Documents/UCSC-Courses/CSE210A/Project/jlint/src/main/java/Interpreter.g4 by ANTLR 4.8
package com.rrajasek.jlint.interpreter;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link InterpreterVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class InterpreterBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements InterpreterVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitStart(InterpreterParser.StartContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitOperation(InterpreterParser.OperationContext ctx) { return visitChildren(ctx); }
}