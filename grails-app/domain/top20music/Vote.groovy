package top20music

class Vote {

	int voteNo
	Date voteDate

	static belongsTo = [song: Song]

	static mapping = { voteNo defaultValue: "'0'" }

	static constraints = {
	}
}
