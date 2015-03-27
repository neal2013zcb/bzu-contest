import grails.util.Environment;
import bzu.ClassGrade;
import bzu.Department;
import bzu.Specialty;
import bzu.Staff;
import bzu.Student;
import bzu.security.Role;
import bzu.security.User;

class BootStrap {
	
	def grailsApplication
	
    def init = { servletContext ->
		// 初始化权限
		grailsApplication.config.app.roles.each {
			if(Role.findByAuthority(it)==null) {
				new Role(authority: it).save(true)
			}
		}
		
		if(Environment.current==Environment.DEVELOPMENT) {
			// 开发状态下添加测试数据
			println "==== DEVELOPMENT MODE ===="
			// Department
			Department d1 = new Department(no:'11', name:'信息工程系', shortName:'信息', category:bzu.Constants.Department.Category.SCHOOL)
			Department d2 = new Department(no:'02', name:'中文系', shortName:'中文', category:bzu.Constants.Department.Category.SCHOOL)
			d1.save()
			d2.save()
			Specialty s1 = new Specialty(department:d1, no:'CS-001', name:'计算机科学与技术', shortName:'计算机', level:bzu.Constants.Specialty.Level.BACHELOR)
			Specialty s2 = new Specialty(department:d1, no:'CE-002', name:'通信工程', shortName:'通信', level:bzu.Constants.Specialty.Level.BACHELOR)
			s1.save()
			s2.save()
			ClassGrade c1 = new ClassGrade(name:'13计本1', grade:2013, classNo:1, specialty:s1)
			ClassGrade c2 = new ClassGrade(name:'14计本1', grade:2014, classNo:1, specialty:s1)
			ClassGrade c3 = new ClassGrade(name:'13通信本1', grade:2013, classNo:1, specialty:s2)
			ClassGrade c4 = new ClassGrade(name:'13通信本2', grade:2013, classNo:2, specialty:s2)
			ClassGrade c5 = new ClassGrade(name:'14通信本1', grade:2014, classNo:1, specialty:s2)
			ClassGrade c6 = new ClassGrade(name:'14通信本2', grade:2014, classNo:2, specialty:s2)
			c1.save()
			c2.save()
			c3.save()
			c4.save()
			c5.save()
			c6.save()
			['1111':'丁老师','2222':'孙老师','3333':'张老师','4444':'李老师','5555':'王老师','6666':'赵老师'].each { no, name ->
				new Staff(no:no, name:name, department:d1, account:new User(username:no, password:no, enabled:true)).save()
			}
			['1001':'丁一','1002':'孙二','1003':'张三','1004':'李四','1005':'王五','1006':'赵六'].each { no, name ->
				new Student(no:no, name:name, classGrade:c1, account:new User(username:no, password:no, enabled:true)).save()
			}
		}
    }
    def destroy = {
    }
}
