package main;

import javax.swing.UIManager;

public class Test {
	static {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e1) {
			
		}
	}
	
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
