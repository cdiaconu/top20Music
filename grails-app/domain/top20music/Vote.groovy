package top20music

class Vote {

	int voteNo
	Date firstDayOfTheWeek

	static belongsTo = [song: Song]

	static mapping = { voteNo defaultValue: "'0'" }

	static constraints = {
	}
}
