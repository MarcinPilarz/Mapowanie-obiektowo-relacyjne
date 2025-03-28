using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace Insurance1;

public partial class UbezpieczeniaContext : DbContext
{
    public UbezpieczeniaContext()
    {
    }

    public UbezpieczeniaContext(DbContextOptions<UbezpieczeniaContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Platnosci> Platnoscis { get; set; }

    public virtual DbSet<StatusyPlatnosci> StatusyPlatnoscis { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see https://go.microsoft.com/fwlink/?LinkId=723263.
        => optionsBuilder.UseSqlServer("Server=LAPTOP-CUB1DJ9H; Database=ubezpieczenia;Integrated Security=SSPI;TrustServerCertificate=TRUE");

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Platnosci>(entity =>
        {
            entity.ToTable("platnosci");

            entity.HasIndex(e => e.KontrahentId, "IX_platnosci_KontrahentId");

            entity.HasIndex(e => e.StatusPlatnosciId, "IX_platnosci_StatusPlatnosciId");

            entity.HasOne(d => d.StatusPlatnosci).WithMany(p => p.Platnoscis).HasForeignKey(d => d.StatusPlatnosciId);
        });

        modelBuilder.Entity<StatusyPlatnosci>(entity =>
        {
            entity.ToTable("statusy_platnosci");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
