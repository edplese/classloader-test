package classloadertest;

public class A {
	private String value;

	public A(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "<A value=\"" + value + "\">";
	}
}
