package com.zensar.exception;

public class ServiceException extends Exception {

		private String errorMessage;

		public ServiceException() {
			
		}
		public ServiceException(String errorMessage) {
			super();
			this.errorMessage = errorMessage;
		}
		
}
