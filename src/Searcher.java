import java.util.*;
import java.util.stream.Collectors;

public class Searcher implements SearchOperations{
	private Set<String> artists = new HashSet<>();
	private Set<String> genres = new HashSet<>();
	private Set<String> titles = new HashSet<>();
	private Map<String, Recording> recordingByTitle = new HashMap<>();
	private Map<String, Set<Recording>> recordingsByGenre = new HashMap<>();
	private SortedMap<Integer, Set<Recording>> recordingByYear = new TreeMap<>();
	private Map<String, SortedSet<Recording>> recordingsByArtist = new HashMap<>();

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
			if(!recordingByYear.containsKey(recording.getYear())){
				recordingByYear.put(recording.getYear(), new HashSet<>());
			}
			recordingByYear.get(recording.getYear()).add(recording);

			if(!recordingsByArtist.containsKey(recording.getArtist())){ //recordingByArtist
				recordingsByArtist.put(recording.getArtist(), new TreeSet<>());
			}
			recordingsByArtist.get(recording.getArtist()).add(recording);
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
		return Collections.unmodifiableSet(genres);
	}

	@Override
	public Recording getRecordingByName(String title) {
		return recordingByTitle.get(title);
	}

	@Override
	public Collection<Recording> getRecordingsAfter(int year) {
		Collection<Recording> allRecordings = new HashSet<>(); //för att undvika dubbletter
        Map<Integer, Set<Recording>> subMap = recordingByYear.tailMap(year);

		for (Set<Recording> recordings : subMap.values()) {
			allRecordings.addAll(recordings);
		}
        return Collections.unmodifiableCollection(allRecordings);
	}

	@Override
	public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
		return Collections.unmodifiableSortedSet(recordingsByArtist.get(artist));
	}

	@Override
	public Collection<Recording> getRecordingsByGenre(String genre) {
		return Collections.unmodifiableSet(recordingsByGenre.getOrDefault(genre, new HashSet<>()));
	}

	@Override
	public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
		Set<Recording> genreRecordings = recordingsByGenre.getOrDefault(genre, Collections.emptySet());
		Set<Recording> filteredList = new HashSet<>();

		for (Recording recording : genreRecordings){
			if(recording.getYear() >= yearFrom && recording.getYear() <= yearTo) {
				filteredList.add(recording);
			}
		}

		return Collections.unmodifiableCollection(filteredList);
	}

	@Override
	public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
		Set<Recording> newRecordings = new HashSet<>();

		for (Recording recording : offered) {
			if (!recordingByTitle.containsKey(recording.getTitle())) {
				newRecordings.add(recording);
			}
		}
		return Collections.unmodifiableSet(newRecordings);
	}

	public Collection<Recording> optionalGetRecordingsBefore(int year){
		SortedMap<Integer, Set<Recording>> recBefore = recordingByYear.headMap(year);
		Collection<Recording> rec = new HashSet<>();

		for(Map.Entry<Integer, Set<Recording>> r: recBefore.entrySet()){
			rec.addAll(r.getValue());
		}
		return Collections.unmodifiableCollection(rec);
	}



}

