
export class User {

    constructor(
        public login: string,
        public name: string,
        public surname: string,
        public dateOfBirth: Date,
        public email: string,
        public street: string,
        public houseNumber: string,
        public city: string,
        public PersonalIdentityNumber?: number) { }
}
