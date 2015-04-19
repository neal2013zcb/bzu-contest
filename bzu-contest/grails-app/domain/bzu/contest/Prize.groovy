package bzu.contest

class Prize {
	Team team
	
	static belongsTo = [team : Team]

    static constraints = {
    }
}
