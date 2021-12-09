package com.liuhuachao.datastructuresandalgorithms.sort;

import java.util.Stack;

/**
 * 栈相关算法
 * 利用栈的先进后出特性
 * @author liuhuachao
 * @date 2021/12/9
 */
public class MyStack {

	/**
	 * 括号是否有效
	 * 判定给定一个的只包含'('，')'，'{'，'}'，'['，']' 的字符串 s 是否有效
	 * 有效字符串需满足：
	 * 1.左括号必须用相同类型的右括号闭合。
	 * 2.左括号必须以正确的顺序闭合。
	 * @param s 待校验的字符串
	 * @return 是否有效
	 */
	public boolean isValid(String s){
		Stack<Character> stack = new Stack<Character>();
		for(Character a : s.toCharArray()){
			// 遇到左括号，将相应的右括号入栈
			if(a == '(') {
				stack.push(')');
			}
			else if(a == '['){
				stack.push(']');
			}
			else if(a == '{'){
				stack.push('}');
			}

			// 遇到右括号且和栈顶元素匹配，则出栈
			else if(!stack.isEmpty() && stack.peek().equals(a)){
				stack.pop();
			}
			// 遇到右括号多余或者右括号不匹配
			else {
				return false;
			}
		}

		// 如果字符串遍历结束，栈中仍有字符，说明有左括号多余
		return stack.isEmpty();
	}
}
