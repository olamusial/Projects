export class BookTable {

    public tableColumnsKeys: Array<string>;

    constructor(
    public name: string,
    public tableColumns: Map<string, boolean>,
    public data: Array<any>,
    public isSelected: boolean ) {
        this.tableColumnsKeys = Array.from(this.tableColumns.keys());
    }
}
