package bzu.contest



import org.junit.*
import grails.test.mixin.*

@TestFor(ProjectApplicationController)
@Mock(ProjectApplication)
class ProjectApplicationControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/projectApplication/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.projectApplicationInstanceList.size() == 0
        assert model.projectApplicationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.projectApplicationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.projectApplicationInstance != null
        assert view == '/projectApplication/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/projectApplication/show/1'
        assert controller.flash.message != null
        assert ProjectApplication.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/projectApplication/list'

        populateValidParams(params)
        def projectApplication = new ProjectApplication(params)

        assert projectApplication.save() != null

        params.id = projectApplication.id

        def model = controller.show()

        assert model.projectApplicationInstance == projectApplication
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/projectApplication/list'

        populateValidParams(params)
        def projectApplication = new ProjectApplication(params)

        assert projectApplication.save() != null

        params.id = projectApplication.id

        def model = controller.edit()

        assert model.projectApplicationInstance == projectApplication
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/projectApplication/list'

        response.reset()

        populateValidParams(params)
        def projectApplication = new ProjectApplication(params)

        assert projectApplication.save() != null

        // test invalid parameters in update
        params.id = projectApplication.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/projectApplication/edit"
        assert model.projectApplicationInstance != null

        projectApplication.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/projectApplication/show/$projectApplication.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        projectApplication.clearErrors()

        populateValidParams(params)
        params.id = projectApplication.id
        params.version = -1
        controller.update()

        assert view == "/projectApplication/edit"
        assert model.projectApplicationInstance != null
        assert model.projectApplicationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/projectApplication/list'

        response.reset()

        populateValidParams(params)
        def projectApplication = new ProjectApplication(params)

        assert projectApplication.save() != null
        assert ProjectApplication.count() == 1

        params.id = projectApplication.id

        controller.delete()

        assert ProjectApplication.count() == 0
        assert ProjectApplication.get(projectApplication.id) == null
        assert response.redirectedUrl == '/projectApplication/list'
    }
}
