package song;

public class SongVO {

	private String songCode;
	private String songTitle;
	private String vocal;
	private String composer;
	private String genre;

	public String getSongCode() {
		return songCode;
	}

	public void setSongCode(String songCode) {
		this.songCode = songCode;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getVocal() {
		return vocal;
	}

	public void setVocal(String vocal) {
		this.vocal = vocal;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "SongVO [songCode=" + songCode + ", songTitle=" + songTitle + ", vocal=" + vocal + ", composer="
				+ composer + ", genre=" + genre + "]";
	}

}