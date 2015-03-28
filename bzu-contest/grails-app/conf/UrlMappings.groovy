class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		
		// 错误处理页面
		"500"(controller:'errors', action:'serverError')
		"403"(controller:'errors', action:'forbidden')
		"405"(controller:'errors', action:'forbidden')
		"400"(controller:'errors', action:'notFound')
	}
}
