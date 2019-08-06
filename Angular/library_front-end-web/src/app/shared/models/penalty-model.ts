export class PenaltyModel {
    passedOrderId: number;
    isPaid: boolean;
    id: number;
    constructor(
        id: number,
        amount: number,
        isPaid: boolean,
        isConfirmed: false,
        paymentTitle: boolean,
        bookTitle: string,
        passedOrderId: number) { this.passedOrderId = passedOrderId; this.isPaid = isPaid; this.id = id; }
}
