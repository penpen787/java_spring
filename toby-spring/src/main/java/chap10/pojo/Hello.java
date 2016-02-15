package chap10.pojo;

import chap10.printer.Printer;

public class Hello {

	String name;
	Printer printer;
	
	
	public String sayHello(){
		return "Hello " + name;
	}
	
	public void print(){
		this.printer.print(this.sayHello());
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
}
