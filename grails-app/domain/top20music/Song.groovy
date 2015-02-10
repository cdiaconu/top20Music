package top20music

class Song {
	String name

	static hasMany = [votes : VoteS]
	static belongsTo = [artist : Artist]

	static constraints = {
	}
}
