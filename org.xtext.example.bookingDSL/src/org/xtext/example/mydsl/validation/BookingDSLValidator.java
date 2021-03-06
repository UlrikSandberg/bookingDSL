/*
 * generated by Xtext 2.24.0
 */
package org.xtext.example.mydsl.validation;

import org.eclipse.xtext.validation.Check;
import org.xtext.example.mydsl.bookingDSL.Attribute;
import org.xtext.example.mydsl.bookingDSL.Booking;
import org.xtext.example.mydsl.bookingDSL.BookingDSLPackage;
import org.xtext.example.mydsl.bookingDSL.Declaration;

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class BookingDSLValidator extends AbstractBookingDSLValidator {
	
	
	
	@Check
	public void warnIfNoDisplayName(Declaration declaration) {
		
		if(declaration instanceof Booking) {
			return;
		}
		
		var hasName = false;
		
		var members = declaration.getMembers();
		for(int i = 0; i < members.size(); i++) {
			var member = members.get(i);
			if(member instanceof Attribute) {
				var attriName = ((Attribute) member).getName();
				if(attriName.equals("name")) {
					hasName = true;
					
				}
			}
		}
		
		if(!hasName) {
			// Return warning that there are no name attribute
			warning("This declaration has no name", BookingDSLPackage.eINSTANCE.getBaseDeclaration_Name());
			return;
		}	
	}
	
	@Check
	public void errorIfDisplayNameIsNotString(Attribute attri) {
		
		var attriName = attri.getName();
		if(attriName.equals(("name"))) {
			var attriType = attri.getType().getLiteral();
			if(!attriType.equals("string")) {
				error("Attribute of type name can only be of type string", BookingDSLPackage.eINSTANCE.getAttribute_Name());
				return;
			}
		}
	}
}
