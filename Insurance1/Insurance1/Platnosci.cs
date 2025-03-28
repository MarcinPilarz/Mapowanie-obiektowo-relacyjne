using System;
using System.Collections.Generic;

namespace Insurance1;

public partial class Platnosci
{
    public int Id { get; set; }

    public string Nazwa { get; set; } = null!;

    public int StatusPlatnosciId { get; set; }

    public double Kwota { get; set; }

    public DateTime Data { get; set; }

    public int? KontrahentId { get; set; }

    public int ZewnetrzneId { get; set; }

    public virtual StatusyPlatnosci StatusPlatnosci { get; set; } = null!;
}
