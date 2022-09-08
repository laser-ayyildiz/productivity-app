export class Profile {
    name: string;
    surname: string;
    username: string;
    email: string;

    constructor(name: string, surname: string, username: string, email: string) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
    }
}