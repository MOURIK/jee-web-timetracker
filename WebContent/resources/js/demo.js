/**
 * Conselti s.r.l.
 */



/**
 * @author onofr
 */
function getMessage() {
	var myDate = new Date();
	var greet = new String();
	
	if(myDate.getHours() < 12) {
		this.greet = "Buongiorno";
		document.write(this.greet);
		greet = new String();
	}
	
	else if(myDate.getHours() >= 12 && myDate.getHours() <= 17) {
		this.greet = "Buon Pomeriggio"
		document.write(this.greet);
		greet = new String();
	}
	
	else if(myDate.getHours() > 17 && myDate.getHours() <= 24) {
		this.greet = "Buonasera";
		document.write(this.greet);
		greet = new String();
	}
	else {
		this.greet = "Buonanotte";
		document.write(this.greet);
		greet = new String();
    }
}

