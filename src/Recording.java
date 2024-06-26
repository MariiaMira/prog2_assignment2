import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class Recording implements Comparable<Recording> {
    private final int year;
    private final String artist;
    private final String title;
    private final String type;
    private final Set<String> genre;

    public Recording(String title, String artist, int year, String type, Set<String> genre) {
        this.title = title;
        this.year = year;
        this.artist = artist;
        this.type = type;
        this.genre = genre;
    }

	@Override
	public int compareTo(Recording other) {
		return Integer.compare(year, other.year);
	}

	public String getArtist() {
        return artist;
    }

    public Collection<String> getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Recording recording)) return false;
        return year == recording.year &&
                Objects.equals(title, recording.title) &&
                Objects.equals(artist, recording.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, year);
    }

    @Override
    public String toString() {
        return String.format("{ %s | %s | %s | %d | %s }", artist, title, genre, year, type);
    }
}
