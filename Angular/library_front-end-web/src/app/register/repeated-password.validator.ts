import {AbstractControl} from '@angular/forms';

export class RepeatedPasswordValidator {

    static MatchPassword(control: AbstractControl) {
       const password = control.get('password').value;

       const repeatedPassword = control.get('repeatedPassword').value;

       if (password !== repeatedPassword) {
            control.get('repeatedPassword').setErrors( {repeatedPassword: true} );
        } else {
            return null;
        }
    }
}
