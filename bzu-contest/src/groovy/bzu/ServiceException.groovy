package bzu

/**
 * 服务执行过程中发生异常
 * 
 * @author zhbo
 *
 */
class ServiceException extends RuntimeException {
	
	ServiceException(String msg) {
		super(msg)
	}
}
