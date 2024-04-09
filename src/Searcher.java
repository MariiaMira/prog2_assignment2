import java.util.*;

public class Searcher implements SearchOperations{
	private Set<String> artists;
	private Set<String> genres;
	private Set<String> titles;


	public Searcher(Collection<Recording> data) {
		
		Collection<Recording> recordings = data;
		artists = new HashSet<>();
		genres = new HashSet<>();
		titles = new HashSet<>();

		for (Recording recording : recordings) {
			artists.add(recording.getArtist());
			titles.add(recording.getTitle());
		}

	}

	@Override
	public long numberOfArtists(){
		return artists.size();
	}

	@Override
	public long numberOfTitles(){
		return titles.size();
	}
}

