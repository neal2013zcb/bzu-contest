package bzu.contest

import bzu.Staff;
import bzu.Student;

class Team {
	Project project
	String name
	Student pincipal
	List<Staff> advisors
	List<Student> members
	Prize prize
	
	static hasOne = [prize : Prize]
	
	static belongsTo = [project : Project]
	
	static hasMany = [advisors : Staff, members : Student]

    static constraints = {
    }
}
