package bzu.contest

class Honor {
	Project project
	
	static belongsTo = [project : Project]

    static constraints = {
    }
}
