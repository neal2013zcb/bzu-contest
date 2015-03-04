
package bzu




import grails.test.mixin.*

import org.junit.*

import bzu.Person;

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Person)
class PersonTests {

    void "test right office phone numbers"() {
	   mockForConstraintsTests Person
	   def person = new Person()
	   def right = [
		   '',
		   '12388888888',
		   '7777777',
		   '8888888',
		   '123-88888888',
		   '1234-88888888',
		   '8888888-1',
		   '7777777-22',
		   '123-88888888-333',
		   '1234-88888888-4444']
	   
	   right.each {
		   person.officePhone = it
		   assert person.validate(['officePhone'])
	   }	   
    }

	void "test wrong office phone numbers"() {
	   mockForConstraintsTests Person
	   def person = new Person()
	   def wrong = ['1237777777','123999999999',
		   '666666','55555','4444','333','22','1',
		   '44-88888888','55555-88888888']
	   
	   wrong.each {
		   person.officePhone = it
		   assert !person.validate(['officePhone'])
		   assert 'matches' == person.errors['officePhone']
	   }
	}
	
	void "test right cell phone numbers"() {
		mockForConstraintsTests Person
		def person = new Person()
		def right = [
			'',
			'12388888888',
			'7777777',
			'8888888',
			'123-88888888',
			'1234-88888888',
			'8888888-1',
			'7777777-22',
			'123-88888888-333',
			'1234-88888888-4444']
		
		right.each {
			person.cellPhone = it
			assert person.validate(['cellPhone'])
		}
	 }
 
	 void "test wrong cell phone numbers"() {
		mockForConstraintsTests Person
		def person = new Person()
		def wrong = ['1237777777','123999999999',
			'666666','55555','4444','333','22','1',
			'44-88888888','55555-88888888']
		
		wrong.each {
			person.cellPhone = it
			assert !person.validate(['cellPhone'])
			assert 'matches' == person.errors['cellPhone']
		}
	 }
 
	void "test right qq number"() {
		mockForConstraintsTests Person
		def person = new Person()
		def right = ['','666666','7777777','88888888','99999999','1010101010']
		
		right.each { qq->
			person.qq = qq
			assert person.validate(['qq'])
		}
	}
	
	void "test wrong qq number"() {
	   mockForConstraintsTests Person
	   def person = new Person()
	   def wrong = ['1','22','333','4444','55555','99999999911',
		   '066666','0777777','08888888','09999999','0010101010']
	   
	   wrong.each { qq->
		   person.qq = qq
		   assert !person.validate(['qq'])
		   assert 'matches' == person.errors['qq']
	   }
	}
	
	void "test right weixin number"() {
		mockForConstraintsTests Person
		def person = new Person()
		def right = ['','666666','aaaaaa','aB-123_45',
			'11111111112222222222']
		
		right.each { weixin->
			person.weixin = weixin
			assert person.validate(['weixin'])
		}

	}
	
	void "test wrong weixin number"() {
		mockForConstraintsTests Person
		def person = new Person()
		def wrong = ['1','22','333','4444','55555','aaaa@b']
		
		wrong.each { weixin->
			person.weixin = weixin
			assert !person.validate(['weixin'])
			assert 'matches' == person.errors['weixin']
		}
	}
	
}
