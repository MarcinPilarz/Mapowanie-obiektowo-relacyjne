namespace Insurance1.Dtos
{
    public class PaymentRequestDto
    {
        public long VisitId { get; set; }
        public double Amount { get; set; }
        public string Description { get; set; }
    }
}
