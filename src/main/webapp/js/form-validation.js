// Wait for the DOM to be ready
$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='registration']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are definedn
      // on the right side
      firstName: "required",
      lastName: "required",
      emailId: {
        required: true,
        // Specify that email should be validated
        // by the built-in "email" rule
        email: true
      },
      phoneNo:"required"
     
    },
    // Specify validation error messages
    messages: {
      firstName: "Please enter your firstname",
      lastName: "Please enter your lastname",
     
      emailId: "Please enter a valid email address",
      phoneNo:"Please enter MobileNo"
    },
    
    // Make sure the form is submitted to the destination defined
 // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});