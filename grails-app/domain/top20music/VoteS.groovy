package top20music


class VoteS {
	int id
	long votes
	Date firstDayOfTheWeek
	
	static belongsTo = Song
	
    static constraints = {
    }
}
