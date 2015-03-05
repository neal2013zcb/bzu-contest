package bzu



import org.junit.*
import grails.test.mixin.*

@TestFor(SpecialtyController)
@Mock(Specialty)
class SpecialtyControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/specialty/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.specialtyInstanceList.size() == 0
        assert model.specialtyInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.specialtyInstance != null
    }

    void testSave() {
        controller.save()

        assert model.specialtyInstance != null
        assert view == '/specialty/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/specialty/show/1'
        assert controller.flash.message != null
        assert Specialty.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/specialty/list'

        populateValidParams(params)
        def specialty = new Specialty(params)

        assert specialty.save() != null

        params.id = specialty.id

        def model = controller.show()

        assert model.specialtyInstance == specialty
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/specialty/list'

        populateValidParams(params)
        def specialty = new Specialty(params)

        assert specialty.save() != null

        params.id = specialty.id

        def model = controller.edit()

        assert model.specialtyInstance == specialty
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/specialty/list'

        response.reset()

        populateValidParams(params)
        def specialty = new Specialty(params)

        assert specialty.save() != null

        // test invalid parameters in update
        params.id = specialty.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/specialty/edit"
        assert model.specialtyInstance != null

        specialty.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/specialty/show/$specialty.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        specialty.clearErrors()

        populateValidParams(params)
        params.id = specialty.id
        params.version = -1
        controller.update()

        assert view == "/specialty/edit"
        assert model.specialtyInstance != null
        assert model.specialtyInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/specialty/list'

        response.reset()

        populateValidParams(params)
        def specialty = new Specialty(params)

        assert specialty.save() != null
        assert Specialty.count() == 1

        params.id = specialty.id

        controller.delete()

        assert Specialty.count() == 0
        assert Specialty.get(specialty.id) == null
        assert response.redirectedUrl == '/specialty/list'
    }
}
