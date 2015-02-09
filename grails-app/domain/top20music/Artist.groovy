package top20music

class Artist {
	String firstName
	String lastName

	static hasMany = [songs : Song]

	static constraints = {
		lastName blank: true, nullable: true
	}
}
