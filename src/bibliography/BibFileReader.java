package bibliography;
/**
 * interface with a required readFromFile method
 * @author Bronagh
 *
 */
public interface BibFileReader {
	/**
	 * interface method that should read from a file
	 * @param filename the filename to read  
	 */
    public void readFromFile(String filename);
}