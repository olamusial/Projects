export class Page {

    constructor(
        public pageSize: number,
        public pageNumber: number,
        public orderBy?: string,
        public descending?: boolean) { }
}
