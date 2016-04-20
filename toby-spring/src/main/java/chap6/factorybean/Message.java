package chap6.factorybean;

/** 생성자를 제공하지 않는 클래스 */
public class Message {
	String text;

	/** private 생성자로 외부에서 생성자를 통해 객체생성 불가능 */
	private Message(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	/** static factory method */
	public static Message newMessage(String text) {
		return new Message(text);
	}

}
