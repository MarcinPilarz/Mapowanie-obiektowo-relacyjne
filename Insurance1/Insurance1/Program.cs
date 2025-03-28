
using Insurance1;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
//builder.Services.AddRazorPages();
builder.Services.AddControllers();

builder.Services.AddDbContext<UbezpieczeniaContext>();
  //  (options => options.UseSqlServer(builder.Configuration.GetConnectionString("LOCAL_SQL_CONNECTION")));

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();


// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    // app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    //  app.UseHsts();
    app.UseSwagger();
    app.UseSwaggerUI();
}

//app.UseHttpsRedirection();
//app.UseStaticFiles();

//app.UseRouting();


app.UseAuthorization();

//app.MapRazorPages();
app.MapControllers();

app.Run();
