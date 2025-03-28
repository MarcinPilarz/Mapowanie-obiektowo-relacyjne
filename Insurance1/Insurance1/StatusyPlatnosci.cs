using System;
using System.Collections.Generic;

namespace Insurance1;

public partial class StatusyPlatnosci
{
    public int Id { get; set; }

    public string? Symbol { get; set; }

    public string Nazwa { get; set; } = null!;

    public virtual ICollection<Platnosci> Platnoscis { get; set; } = new List<Platnosci>();
}
