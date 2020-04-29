// Generated from /home/rishi/Documents/UCSC-Courses/CSE210A/Project/jlint/src/main/java/Interpreter.g4 by ANTLR 4.8
package com.rrajasek.jlint.interpreter;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link InterpreterParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface InterpreterVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link InterpreterParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(InterpreterParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterpreterParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(InterpreterParser.OperationContext ctx);
}