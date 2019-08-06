import { PenaltyModel } from './penalty-model';

export class PastOrderModel {

    constructor(
        orderDate: Date,
        checkOutDate: Date,
        copyId: number,
        userId: number,
        penalty: PenaltyModel,
        bookId: number,
        id: number,
        bookName: string) { }
}
