Cyber Security Base - Course Project I
======================================

Security flaw project
---------------------

This is a simple event signup application. You can sign up to the event by filling the form on the front page. You may also view a list of participants (and search among them).

### Flaw 1
A9 - Using Components with Known Vulnerabilities

The project uses old jQuery 1.6.2 that has at least two security vulnerabilities listed at https://www.cvedetails.com/vulnerability-list/vendor_id-6538/Jquery.html . The flaw can be identified by using the Dependency-Check plugin from Part III or just by going through the list of dependies and checking their versions. The jQuery has been included via the pom.xml dependency management and just needs to be changed into a newer version.


### Flaw 2
A6 - Sensitive Data Exposure

Page at /participants loads all participant info via an AJAX call and displays only the name of the participant. Address and credit card info is also loaded and can be seen by doing a POST-request directly into the rest controller "not GET request to do some obfuscation". Web-browsers' F12 debug function shows easily the contents of any response, including the sensitive data. I don't know if a software can determine that a response contains sensitive data. This can be fixed by specifying a @JsonIgnore annotation of Jackson on the address and credit card getter methods of the Signup model.


### Flaw 3
A8 - Cross-Site Request Forgery (CSRF)

CSRF has been disabled "because the /participants POST request won't work otherwise". This allows CSRF attacks. The flaw can be indentified when examining the HTML code and noticing the lack of an unpredictable CSRF token. This can be fixed by removing the http.csrf().disable(); -line from SecurityConfiguration.java and for example using a GET request in the AJAX request.


### Flaw 4
A3 - Cross-Site Scripting (XSS)

The listing at /participants allows XSS scripting. OWASP ZAP found this flaw as Format String Error. The line `$('#participants').append('<p>' + d.name + '</p>');` should be escaped for example like `$( '#participants' ).append( $( '<p />' ).text( d.name ) );`


### Flaw 5
A10 - Unvalidated Redirects and Forwards

The signup form includes an unvalidated redirect - "tried to do a nice implementation of Post/Redirect/Get -design pattern". OWASP ZAP found this flaw as External Redirect. Fix by removing the redirect -field from the signup form and in SignupController hard code the redirect to redirect:/done


