package top20music

class TopSongDTO {
	long id;
	String songName
	String artistFirstName
	String artistLastName
	Long voteNo;

	static constraints = {
	}

	public Long getVoteNo() {
		return voteNo == null ? 0l : voteNo;
	}

	public String getArtistLastName() {
		return artistLastName == null ? "" : artistLastName;
	}
}
