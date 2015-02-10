package top20music

class Artist {
	String firstName
	String lastName

	static hasMany = [songs : Song, votes : VoteA]

	static constraints = {
		lastName blank: true, nullable: true
	}
}
