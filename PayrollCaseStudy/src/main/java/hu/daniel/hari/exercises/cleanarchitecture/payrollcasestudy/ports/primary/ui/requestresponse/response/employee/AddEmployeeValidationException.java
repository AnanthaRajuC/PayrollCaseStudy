package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee;

import java.util.List;

public class AddEmployeeValidationException extends RuntimeException {
	public final List<AddEmployeeValidationError> addEmployeeValidationErrors;
	public AddEmployeeValidationException(List<AddEmployeeValidationError> addEmployeeValidationErrors) {
		this.addEmployeeValidationErrors = addEmployeeValidationErrors;
	}
	
	public interface AddEmployeeValidationError {
		
		public abstract <T> T accept(AddEmployeeValidationErrorVisitor<T> visitor);
		
		public interface AddEmployeeValidationErrorVisitor<T> {
			T visit(RequiredFieldValidationError requiredFieldValidationError);
			T visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError);
			T visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError);
		}
	}
	
	public static class RequiredFieldValidationError implements AddEmployeeValidationError {
		public String fieldName;
		public RequiredFieldValidationError(String fieldName) {
			this.fieldName = fieldName;
		}

		@Override
		public <T> T accept(AddEmployeeValidationErrorVisitor<T> visitor) {
			return visitor.visit(this);
		}
	}

	public static class IdAlreadyExistsValidationError implements AddEmployeeValidationError {
		public String nameOfExistingUser;
		public IdAlreadyExistsValidationError(String nameOfExistingUser) {
			this.nameOfExistingUser = nameOfExistingUser;
		}
		@Override
		public <T> T accept(AddEmployeeValidationErrorVisitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	
	public static class NameAlreadyExistsValidationError implements AddEmployeeValidationError {
		public int idOfExistingUser;
		public NameAlreadyExistsValidationError(int idOfExistingUser) {
			this.idOfExistingUser = idOfExistingUser;
		}
		@Override
		public <T> T accept(AddEmployeeValidationErrorVisitor<T> visitor) {
			return visitor.visit(this);
		}
	}

}