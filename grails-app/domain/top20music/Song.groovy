package top20music

class Song {

	String name

	static hasMany = [vots: Vote]
	static belongsTo = [artist: Artist]

	static constraints = {  
		 name blank: false, nullable: false
	}
}
