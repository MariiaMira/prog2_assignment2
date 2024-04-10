import java.util.*;

public class Searcher implements SearchOperations{
	private Set<String> artists = new HashSet<>();
	private Set<String> genres = new HashSet<>();
	private Set<String> titles = new HashSet<>();
	private Map<String, Recording> recordingByTitle = new HashMap<>();
	private Map<String, Set<Recording>> recordingsByGenre = new HashMap<>();


	public Searcher(Collection<Recording> data) {
		
		Collection<Recording> recordings = data;

		for (Recording recording : recordings) {
			artists.add(recording.getArtist());
			titles.add(recording.getTitle());
            genres.addAll(recording.getGenre());
			recordingByTitle.put(recording.getTitle(), recording);
			for (String genre : recording.getGenre()) {
				if (!recordingsByGenre.containsKey(genre)) {
					recordingsByGenre.put(genre, new HashSet<>());
				}
				recordingsByGenre.get(genre).add(recording);
			}
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
	@Override
	public long numberOfGenres(){
		return genres.size();
	}
	@Override
	public boolean doesArtistExist(String name){
		return artists.contains(name);
	}
	@Override
	public Collection<String> getGenres(){
		//System.out.println(genres);
		return Collections.unmodifiableSet(genres);
	}

	@Override
	public Recording getRecordingByName(String title) {
		return recordingByTitle.get(title);
	}

	@Override
	public Collection<Recording> getRecordingsAfter(int year) {
		return null;
	}

	@Override
	public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
		return null;
	}

	@Override
	public Collection<Recording> getRecordingsByGenre(String genre) {
		return Collections.unmodifiableSet(recordingsByGenre.get(genre));
	}

	@Override
	public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
		return null;
	}

	@Override
	public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
		return null;
	}

}

