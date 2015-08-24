package java.chap10.printer;

public class StringPrinter implements Printer {

	private StringBuffer buffer = new StringBuffer();
	
	@Override
	public void print(String message) {
		this.buffer.append(message);
	}
	
	@Override
	public String toString() {
		return this.buffer.toString();
	}
}
