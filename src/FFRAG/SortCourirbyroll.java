package FFRAG;
import java.util.Comparator;

public class SortCourirbyroll implements Comparator<Courir>{
	public int compare(Courir a,Courir b) {
		if(a.getTempsCorrige() > b.getTempsCorrige()) {
			return 1;
		}
		return -1;
	}
}
