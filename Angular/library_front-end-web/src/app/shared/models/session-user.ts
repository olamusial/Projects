export class SessionUser {

    constructor(public id: number,
                public login: string,
                public name: string,
                public surname: string,
                public token: string) { }
}
