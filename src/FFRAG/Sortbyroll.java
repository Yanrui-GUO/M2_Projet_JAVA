package FFRAG;
import java.util.Comparator;

public class Sortbyroll implements Comparator<Participant>{
	public int compare(Participant a,Participant b) {
		if(a.getTempsFinal() > b.getTempsFinal()) {
			return 1;
		}
		return -1;
	
	}
}
