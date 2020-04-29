// Generated from /home/rishi/Documents/UCSC-Courses/CSE210A/Project/jlint/src/main/java/Interpreter.g4 by ANTLR 4.8
package com.rrajasek.jlint.interpreter;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InterpreterParser}.
 */
public interface InterpreterListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InterpreterParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(InterpreterParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterpreterParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(InterpreterParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterpreterParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(InterpreterParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterpreterParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(InterpreterParser.OperationContext ctx);
}