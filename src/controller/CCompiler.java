package controller;

import java.util.Stack;

public class CCompiler {
	private double preWitdh = 100;
	private double preHeight = 100;
	private double startX = 0;
	private double startY =0;
	private Stack<Character> op = new Stack<Character>();
	public CCompiler(){
		op.add('$');
	}
	//fenxi
	public void setCode(String code){
		for(int i = 0;i<code.length();i++){
			op.push(code.charAt(i));
			doSomething();
		}
	}
	public void doSomething(){
		if(op.peek() == '}'){
			String temp ="";
			while(op.peek()!='$'){
				pop();
				temp = temp + op.peek();
			}
			System.out.println(temp);
		}
	}
	public void convert(){

	}
	public void pop(){
		if(op.size()!=1){
			op.pop();
		}
	}
}
