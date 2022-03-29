package tests;

import org.junit.jupiter.api.Test;

import bibliography.BibFileReader;

class BibFileReaderTest {
	@Test
	void fileReadTest() {
		BibFileReader bibFileReader = new BibFileReader() {
			@Override
			public void readFromFile(String filename) {
				System.out.println("Read from file test success.  File: "+filename);
			}
		};
		bibFileReader.readFromFile("abc");
	}
}
