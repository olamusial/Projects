export class Filter {

    constructor(
    public title: string,
    public isbn: string,
    public publisherName: string,
    public authorName: string,
    public authorSurname: string,
    public genre: Array<string>) {}
}
