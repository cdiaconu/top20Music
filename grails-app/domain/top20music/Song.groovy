package top20music

class Song {
	String name
	int votes

	static belongsTo = [artist : Artist]

	static constraints = {
	}
}
