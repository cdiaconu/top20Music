package top20music


class VoteA {
	int id
	long votes
	Date firstDayOfTheWeek

	static belongsTo = Artist

	static constraints = {
	}
}
