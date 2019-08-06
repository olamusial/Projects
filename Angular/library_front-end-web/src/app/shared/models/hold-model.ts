export class HoldModel {

    constructor(
    public id: number,
    public bookName: string,
    public placeInQueue: number,
    public holdDate: Date ) {}
}
