package top20music

class Artist {

	String firstName
	String lastName

	static hasMany = [songs: Song]

	static constraints = {
		firstName blank: false, nullable: false
		lastName blank: true, nullable: true
	}
}
